package kr.ac.jejunu.jnu_tong.ui.city_bus.stop;

import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.data.vo.BusStopVO;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.task.get.GetBusStopTask;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.BusAdapterContract;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.FragmentPresenter;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusStopFragmentPresenter implements FragmentPresenter, OnTaskResultListener<List<BusStopVO>> {
    private BusAdapterContract.View<BusStopVO> view;
    private String busId;

    @Inject
    BusStopFragmentPresenter(BusAdapterContract.View<BusStopVO> view) {
        this.view = view;
    }


    private void executeTask(){
        GetBusStopTask getBusStopTask = new GetBusStopTask(this);
        getBusStopTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, CommonApp.getBusStopListURL(busId));
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
    public void setBusId(String busId) {
        this.busId = busId;
    }

    @Override
    public void refreshClick() {
        executeTask();
    }
}
