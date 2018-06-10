package kr.ac.jejunu.jnu_tong.detail.city_bus.presenter;

import android.os.AsyncTask;

import java.util.List;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.detail.city_bus.adapter.AdapterContract;
import kr.ac.jejunu.jnu_tong.vo.BusStopVO;
import kr.ac.jejunu.jnu_tong.task.get_data.GetBusStopTask;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusStopFragmentPresenter implements FragmentPresenter, OnTaskResultListener<BusStopVO> {
    private AdapterContract.View<BusStopVO> view;
    private String busID;

    public BusStopFragmentPresenter(AdapterContract.View view, String busID) {
        this.view = view;
        this.busID = busID;
    }

    private void executeTask(){
        GetBusStopTask getBusStopTask = new GetBusStopTask(this);
        getBusStopTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, CommonData.getBusStopListURL(busID));
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
