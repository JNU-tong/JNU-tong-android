package kr.ac.jejunu.jnu_tong.detail.presenter;

import android.os.AsyncTask;

import java.util.List;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.detail.adapter.AdapterContract;
import kr.ac.jejunu.jnu_tong.main.BusStopVO;
import kr.ac.jejunu.jnu_tong.task.GetBusStopTask;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListner;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusStopFragmentPresenter implements FragmentPresenter, OnTaskResultListner<BusStopVO> {
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
            view.addDatas(result);
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
}
