package kr.ac.jejunu.jnu_tong.main;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import junit.framework.Assert;

import java.util.List;

import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.Item;
import kr.ac.jejunu.jnu_tong.task.GetDepartureBusTask;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListner;

/**
 * Created by seung-yeol on 2018. 4. 20..
 */

public class Presenter implements OnTaskResultListner<DepartureBusVO> {
    private final MainView mainView;

    private MainAdapterContract.View<Item> adapterView;
    private MainAdapterContract.Model adapterModel;

    Presenter(MainView mainView) {
        this.mainView = mainView;
    }

    void onCreate() {
        Assert.assertNotNull(adapterView);

//        executeTask();
    }

    private void executeTask() {
        GetDepartureBusTask getDepartureBusTask = new GetDepartureBusTask(this);
        getDepartureBusTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

    void setAdapterView(MainAdapterContract.View<Item> adapterView){
        this.adapterView = adapterView;
    }

    void setAdapterModel(MainAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onTaskResult(List<DepartureBusVO> result) {
        if (result == null || result.size() == 0) {
            Log.e(this.toString(), "결과가 없어.. ");
        }
        else {
            adapterView.setItems(adapterModel.taskResultItems(result));
        }
    }

    void onDetailClick(int position) {
        adapterModel.goDetailActivity(position);
    }

    void onHeartClick(int position) {
        adapterModel.heartClick(position);
    }

    void clickCityBus(boolean expanded) {
        if (!expanded){
            executeTask();
        }

        mainView.onClickCityBus();
    }
}
