package kr.ac.jejunu.jnu_tong;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.task.get.GetShuttleMainTask;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.ShuttleTimeVO;

public class AppWidget extends AppWidgetProvider{
    @Inject
    IDataManager dataManager;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    //pendingSync 실행되면 호출되는 메서드
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AndroidInjection.inject(this, context);
        dataManager.getDepartureBusList();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(), AppWidget.class.getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

        onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget_main is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget_main is disabled
        compositeDisposable.dispose();
    }

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {
        Log.e("widget_main", "updateAppWidget: 위젯 업데이트");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_main);

        RemoteViews refreshVies = new RemoteViews(context.getPackageName(), R.layout.widget_refresh);
        appWidgetManager.updateAppWidget(appWidgetId, refreshVies);

        GetShuttleMainTask getShuttleMainTask = new GetShuttleMainTask(result -> {
            ShuttleTimeVO shuttleTimeVO = (ShuttleTimeVO) result;

            if (shuttleTimeVO.getAFirst() != null) {
                views.setTextViewText(R.id.shuttle_bus1, shuttleTimeVO.getAFirst() + "분전");
            } else
                views.setTextViewText(R.id.shuttle_bus1, "운행X");

            if (shuttleTimeVO.getBFirst() != null) {
                views.setTextViewText(R.id.shuttle_bus2, shuttleTimeVO.getBFirst() + "분전");
            } else
                views.setTextViewText(R.id.shuttle_bus2, "운행X");

            appWidgetManager.updateAppWidget(appWidgetId, views);
        });

        getShuttleMainTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                CommonApp.getShuttlePointUrl(((CommonApp) context.getApplicationContext()).getShuttleBookmarkId()));

        if (dataManager != null){
            Observable<List<DepartureBusVO>> observable = dataManager.getDepartureBusObservable();
            if (observable != null){
                compositeDisposable.add(observable.subscribe(departureBusVOS -> {
                    ArrayList<DepartureBusVO> vos = new ArrayList<>(departureBusVOS);
                    Collections.sort(vos);

                    views.removeAllViews(R.id.normals);
                    views.removeAllViews(R.id.oftens);

                    if (vos.size() > 0 && vos.get(0) != null) {
                        views.setViewVisibility(R.id.txt_no_bus1, View.GONE);
                        int itemLayout = getItemLayout(vos, 0);
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), itemLayout);
                        remoteViews.setTextViewText(R.id.txt_bus_no, vos.get(0).getCityBusLineInfo().getLineNo() + "번");

                        views.addView(R.id.normals, remoteViews);
                    } else {
                        views.setViewVisibility(R.id.txt_no_bus1, View.VISIBLE);
                    }

                    if (vos.size() > 1 && vos.get(1) != null) {
                        int itemLayout = getItemLayout(vos, 1);
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), itemLayout);
                        remoteViews.setTextViewText(R.id.txt_bus_no, vos.get(1).getCityBusLineInfo().getLineNo() + "번");

                        views.addView(R.id.normals, remoteViews);
                    }

                    if (vos.size() > 2 && vos.get(2) != null) {
                        int itemLayout = getItemLayout(vos, 2);
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), itemLayout);
                        remoteViews.setTextViewText(R.id.txt_bus_no, vos.get(2).getCityBusLineInfo().getLineNo() + "번");

                        views.addView(R.id.normals, remoteViews);
                    }

                    for (DepartureBusVO vo : vos) {
                        ArrayList<DepartureBusVO> oftenBus = new ArrayList<>();
                        if (vo.getRemainTime().getFirst() < 10) {
                            if (((CommonApp) context.getApplicationContext()).hasOftenBus(vo.getCityBusLineInfo().getLineId())) {
                                oftenBus.add(vo);
                            }
                        }

                        if (oftenBus.size() > 0 && oftenBus.get(0) != null) {
                            views.setViewVisibility(R.id.txt_no_bus2, View.GONE);

                            int itemLayout = getItemLayout(oftenBus, 0);
                            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), itemLayout);
                            remoteViews.setTextViewText(R.id.txt_bus_no, oftenBus.get(0).getCityBusLineInfo().getLineNo() + "번");

                            views.addView(R.id.oftens, remoteViews);
                        } else {
                            views.setViewVisibility(R.id.txt_no_bus2, View.VISIBLE);
                        }

                        if (oftenBus.size() > 1 && oftenBus.get(1) != null) {
                            int itemLayout = getItemLayout(oftenBus, 1);
                            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), itemLayout);
                            remoteViews.setTextViewText(R.id.txt_bus_no, oftenBus.get(1).getCityBusLineInfo().getLineNo() + "번");

                            views.addView(R.id.oftens, remoteViews);
                        }
                    }

                    appWidgetManager.updateAppWidget(appWidgetId, views);
                }));
            }
        }

        String currentTime = getCurrentTime();

        views.setTextViewText(R.id.shuttle_title, ((CommonApp) context.getApplicationContext()).getShuttleBookmarkTitle());
        views.setTextViewText(R.id.txt_time, "업데이트 " + currentTime);

        Intent intentSync = new Intent(context, AppWidget.class);
        intentSync.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        PendingIntent pendingSync =
                PendingIntent.getBroadcast(context, 0, intentSync, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.refresh, pendingSync);
    }

    private int getItemLayout(ArrayList<DepartureBusVO> vos, int position) {
        int itemLayout;
        if (vos.get(position).getCityBusLineInfo().getLineNo().length() == 4) {
            itemLayout = R.layout.view_bus_num_yellow;
        } else if (vos.get(position).getCityBusLineInfo().getLineNo().charAt(0) == '4') {
            itemLayout = R.layout.view_bus_num_green;
        } else {
            itemLayout = R.layout.view_bus_num_blue;
        }
        return itemLayout;
    }

    private String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  hh:mm");
        return sdf.format(date);
    }
}