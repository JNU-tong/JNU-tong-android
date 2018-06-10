package kr.ac.jejunu.jnu_tong.main;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import junit.framework.Assert;

import java.util.List;

import kr.ac.jejunu.jnu_tong.task.get_data.GetJNUEventTask;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.Item;
import kr.ac.jejunu.jnu_tong.task.get_data.GetDepartureBusTask;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;

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
