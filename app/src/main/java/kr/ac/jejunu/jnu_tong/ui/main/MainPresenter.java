package kr.ac.jejunu.jnu_tong.ui.main;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.ui.bus.shuttle.route.ARoute;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.task.get.GetDepartureBusTask;
import kr.ac.jejunu.jnu_tong.task.get.GetJNUEventTask;
import kr.ac.jejunu.jnu_tong.task.get.GetShuttleMainTask;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.vo.ShuttleTimeVO;

/**
 * Created by seung-yeol on 2018. 4. 20..
 */

public class MainPresenter implements MainContract.Presenter, OnTaskResultListener<List<DepartureBusVO>> {
    private final MainContract.View mainView;
    private final MainContract.Model mainModel;

    @Inject
    MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        mainModel = new MainModel();
    }

    @Override
    public void onCreate() {
        executeTask();
    }

    @Override
    public void setShuttleBookmarkId(int stationId){
        GetShuttleMainTask shuttleMainTask = new GetShuttleMainTask(result -> {
            mainView.setShuttleBusData(ARoute.values()[stationId-1].getTitle(),
                    ((ShuttleTimeVO) result).getAFirst(), ((ShuttleTimeVO) result).getBFirst());
        });
        shuttleMainTask.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR,
                CommonApp.getShuttlePointUrl( stationId ));
    }

    private void executeTask() {
        GetDepartureBusTask getDepartureBusTask = new GetDepartureBusTask(this);
        getDepartureBusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        GetJNUEventTask eventTask = new GetJNUEventTask(result -> {
            JNUEventVO eventVO = (JNUEventVO) result;
            mainModel.setJNUEvent(eventVO);
            mainView.setJNUEvent(mainModel.getToday(), mainModel.getEvent());
        });
        eventTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void refreshClick() {
        executeTask();
    }

    @Override
    public void onTaskResult(List<DepartureBusVO> result) {
        if (result == null || result.size() == 0) {
            Log.e(this.toString(), "결과가 없어.. ");
            mainView.setDepartureBusData( new Integer[]{null, null} , new String[]{null, null}, null);
        } else {
//            adapterView.setItems(adapterModel.taskResultItems(result));
            mainModel.setDepartureVOS(result);

            mainView.setDepartureBusData(mainModel.getImgIds(), mainModel.getBusNos(), mainModel.getDepartTime());
        }
    }

    @Override
    public void clickCityBus() {
        mainView.onClickCityBus();
    }
}
