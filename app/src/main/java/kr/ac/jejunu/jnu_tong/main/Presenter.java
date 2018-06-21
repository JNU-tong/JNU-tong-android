package kr.ac.jejunu.jnu_tong.main;

import android.os.AsyncTask;
import android.util.Log;

import junit.framework.Assert;

import java.util.List;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.bus.shuttle.route.ARoute;
import kr.ac.jejunu.jnu_tong.task.get.GetJNUEventTask;
import kr.ac.jejunu.jnu_tong.task.get.GetShuttleMainTask;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.Item;
import kr.ac.jejunu.jnu_tong.task.get.GetDepartureBusTask;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.vo.ShuttleTimeVO;

/**
 * Created by seung-yeol on 2018. 4. 20..
 */

public class Presenter implements OnTaskResultListener<List<DepartureBusVO>> {
    private final MainContract.View mainView;
    private final MainContract.Model mainModel;

    private MainAdapterContract.View<Item> adapterView;
    private MainAdapterContract.Model adapterModel;

    Presenter(MainContract.View mainView) {
        this.mainView = mainView;
        mainModel = new MainModel();
    }

    void onCreate() {
        Assert.assertNotNull(adapterView);
        executeTask();
    }

    void setShuttleBookmarkId(int stationId){
        GetShuttleMainTask shuttleMainTask = new GetShuttleMainTask(result -> {
            mainView.setShuttleBusData(ARoute.values()[stationId-1].getTitle(),
                    ((ShuttleTimeVO) result).getAFirst(), ((ShuttleTimeVO) result).getBFirst());
        });
        shuttleMainTask.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR,
                CommonData.getShuttlePointUrl( stationId ));
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

    void setAdapterView(MainAdapterContract.View<Item> adapterView) {
        this.adapterView = adapterView;
    }

    void setAdapterModel(MainAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    void refreshClick() {
        executeTask();
    }

    @Override
    public void onTaskResult(List<DepartureBusVO> result) {
        if (result == null || result.size() == 0) {
            Log.e(this.toString(), "결과가 없어.. ");
            mainView.setDepartureBusData( new Integer[]{null, null} , new String[]{null, null}, null);
        } else {
            adapterView.setItems(adapterModel.taskResultItems(result));
            mainModel.setDepartureVOS(result);

            mainView.setDepartureBusData(mainModel.getImgIds(), mainModel.getBusNos(), mainModel.getDepartTime());
        }
    }

    void onDetailClick(int position) {
        adapterModel.goDetailActivity(position);
    }

    void onHeartClick(int position) {
        adapterModel.heartClick(position);
    }

    void clickCityBus() {
        mainView.onClickCityBus();
    }
}
