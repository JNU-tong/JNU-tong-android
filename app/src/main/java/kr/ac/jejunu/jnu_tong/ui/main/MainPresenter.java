package kr.ac.jejunu.jnu_tong.ui.main;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.IDataManager;
import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.task.get.GetShuttleMainTask;
import kr.ac.jejunu.jnu_tong.ui.bus.shuttle.route.ARoute;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.vo.ShuttleTimeVO;

/**
 * Created by seung-yeol on 2018. 4. 20..
 */

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mainView;
    private final MainContract.Model mainModel;
    private final IDataManager dataManager;
    private OnTaskResultListener<List<DepartureBusVO>> onTaskResultListener;
    private OnTaskResultListener<JNUEventVO> eventVOOnTaskResultListener;

    @Inject
    MainPresenter(MainContract.View mainView, IDataManager dataManager) {
        this.mainView = mainView;
        this.dataManager = dataManager;
        mainModel = dataManager.getMainModel();
    }

    @Override
    public void onCreate() {
        onTaskResultListener = new OnTaskResultListener<List<DepartureBusVO>>() {
            @Override
            public void onTaskResult(List<DepartureBusVO> result) {
                if (result == null || result.size() == 0) {
                    Log.e(this.toString(), "결과가 없어.. ");
                    mainView.setDepartureBusData(new Integer[]{null, null}, new String[]{null, null}, null);
                } else {
//                      adapterView.setItems(adapterModel.taskResultItems(result));
                    mainModel.setDepartureVOS(result);
                    mainView.setDepartureBusData(mainModel.getImgIds(), mainModel.getBusNos(), mainModel.getDepartTime());
                }
            }
        };
        eventVOOnTaskResultListener = result -> {
            mainModel.setJNUEvent(result);
            mainView.setJNUEvent(mainModel.getToday(), mainModel.getEvent());
        };

        dataManager.executeDepartureBusTask(onTaskResultListener);
        dataManager.executeJNUEventTask(eventVOOnTaskResultListener);
    }

    @Override
    public void setShuttleBookmarkId(int stationId) {
        GetShuttleMainTask shuttleMainTask = new GetShuttleMainTask(result -> mainView.setShuttleBusData(ARoute.values()[stationId - 1].getTitle(),
                ((ShuttleTimeVO) result).getAFirst(), ((ShuttleTimeVO) result).getBFirst())
        );

        shuttleMainTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                CommonApp.getShuttlePointUrl(stationId)
        );
    }

    @Override
    public void refreshClick() {
        dataManager.executeDepartureBusTask(onTaskResultListener);
        dataManager.executeJNUEventTask(eventVOOnTaskResultListener);
    }


    @Override
    public void clickCityBus() {
        mainView.onClickCityBus();
    }
}
