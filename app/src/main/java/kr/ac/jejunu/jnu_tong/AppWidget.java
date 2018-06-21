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

import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.task.get.GetDepartureBusTask;
import kr.ac.jejunu.jnu_tong.task.get.GetShuttleMainTask;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.ShuttleTimeVO;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {
    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {
        Log.e("widget", "updateAppWidget: 위젯 업데이트");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

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
                CommonData.getShuttlePointUrl(((CommonData) context.getApplicationContext()).getShuttleBookmarkId()));

        GetDepartureBusTask getDepartureBusTask = new GetDepartureBusTask(result -> {
            ArrayList<DepartureBusVO> vos = new ArrayList<>((List<DepartureBusVO>) result);
            Collections.sort(vos);

            views.removeAllViews(R.id.normals);

            if (vos.get(0) != null) {
                int itemLayout;
                if (vos.get(0).getLineNo().length() == 4) {
                    itemLayout = R.layout.view_bus_num_yellow;
                } else if (vos.get(0).getLineNo().charAt(0) == '4') {
                    itemLayout = R.layout.view_bus_num_green;
                } else{
                    itemLayout = R.layout.view_bus_num_blue;
                }
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), itemLayout);
                remoteViews.setTextViewText(R.id.txt_bus_no, vos.get(0).getLineNo()+"번");

                views.addView(R.id.normals, remoteViews);
            }

            if (vos.get(1) != null) {
                int itemLayout;
                if (vos.get(1).getLineNo().length() == 4) {
                    itemLayout = R.layout.view_bus_num_yellow;
                } else if (vos.get(1).getLineNo().charAt(0) == '4') {
                    itemLayout = R.layout.view_bus_num_green;
                } else{
                    itemLayout = R.layout.view_bus_num_blue;
                }
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), itemLayout);
                remoteViews.setTextViewText(R.id.txt_bus_no, vos.get(1).getLineNo()+"번");

                views.addView(R.id.normals, remoteViews);
            }
            appWidgetManager.updateAppWidget(appWidgetId, views);
        });
        getDepartureBusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        String currentTime = getCurrentTime();

        views.setTextViewText(R.id.shuttle_title, ((CommonData) context.getApplicationContext()).getShuttleBookmarkTitle());
        views.setTextViewText(R.id.txt_time, "업데이트 " + currentTime);

        Intent intentSync = new Intent(context, AppWidget.class);
        intentSync.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        PendingIntent pendingSync =
                PendingIntent.getBroadcast(context, 0, intentSync, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.refresh, pendingSync);
    }

    //pendingSync 실행되면 호출되는 메서드
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(), AppWidget.class.getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

        onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private String getCurrentTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  hh:mm");
        return sdf.format(date);
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
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}