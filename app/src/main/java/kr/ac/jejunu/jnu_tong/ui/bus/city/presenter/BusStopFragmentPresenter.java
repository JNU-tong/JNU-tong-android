package kr.ac.jejunu.jnu_tong.ui.bus.city.presenter;

import android.os.AsyncTask;

import java.util.List;

import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.ui.bus.city.adapter.AdapterContract;
import kr.ac.jejunu.jnu_tong.vo.BusStopVO;
import kr.ac.jejunu.jnu_tong.task.get.GetBusStopTask;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusStopFragmentPresenter implements FragmentPresenter, OnTaskResultListener<List<BusStopVO>> {
    private AdapterContract.View<BusStopVO> view;
    private String busID;

    public BusStopFragmentPresenter(AdapterContract.View view, String busID) {
        this.view = view;
        this.busID = busID;
    }

    private void executeTask(){
        GetBusStopTask getBusStopTask = new GetBusStopTask(this);
        getBusStopTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, CommonApp.getBusStopListURL(busID));
    }

    @Override
    public void onTaskResult(List<BusStopVO> result) {
        if (result != null){
            view.addItems(result);
        }
    }

    @Override
    public void onViewCreated() {
        executeTask();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void refreshClick() {
        executeTask();
    }
}
