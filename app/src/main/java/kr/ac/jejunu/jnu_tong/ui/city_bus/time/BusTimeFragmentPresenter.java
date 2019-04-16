package kr.ac.jejunu.jnu_tong.ui.city_bus.time;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.task.get.GetBusTimeTask;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.BusAdapterContract;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.FragmentPresenter;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusTimeFragmentPresenter implements FragmentPresenter, OnTaskResultListener<List<BusTimeVO>> {
    private BusAdapterContract.View<BusTimeVO> view;
    private String busId;

    @Inject
    BusTimeFragmentPresenter(BusAdapterContract.View<BusTimeVO> view) {
        this.view = view;
    }

    private void executeTask(){
        GetBusTimeTask getBusStopTask = new GetBusTimeTask(this);
        getBusStopTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, CommonApp.getBusTimeListURL(busId));
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

    @Override
    public void onTaskResult(List<BusTimeVO> result) {
        ArrayList<BusTimeVO> remainBusTime = getValidValue(result);
        view.addItems(remainBusTime);
    }

    // 남은시간 양수인것만 가져옴
    private ArrayList<BusTimeVO> getValidValue(List<BusTimeVO> vos){
        ArrayList<BusTimeVO> busTimeVOArrayList = new ArrayList<>();

        if (vos != null){
            for (BusTimeVO vo :
                    vos) {
                if (vo.getRemainTime() >= 0) {
                    busTimeVOArrayList.add(vo);
                }
            }
        }

        return busTimeVOArrayList;
    }
}
