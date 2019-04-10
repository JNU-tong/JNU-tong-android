package kr.ac.jejunu.jnu_tong.ui.bus.city.presenter;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.ui.bus.city.adapter.AdapterContract;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.task.get.GetBusTimeTask;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusTimeFragmentPresenter implements FragmentPresenter, OnTaskResultListener<List<BusTimeVO>> {
    private AdapterContract.View<BusTimeVO> view;
    private String busID;

    public BusTimeFragmentPresenter(AdapterContract.View view, String busID) {
        this.view = view;
        this.busID = busID;
    }

    private void executeTask(){
        GetBusTimeTask getBusStopTask = new GetBusTimeTask(this);
        getBusStopTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, CommonApp.getBusTimeListURL(busID));
    }

    @Override
    public void onTaskResult(List<BusTimeVO> result) {
        ArrayList<BusTimeVO> remainBusTime = getValidValue(result);
        view.addItems(remainBusTime);
    }

    @Override
    public void onViewCreated() {
        executeTask();
    }

    @Override
    public void onPause() {}

    @Override
    public void onResume() {}

    @Override
    public void onDestroy() {}

    @Override
    public void refreshClick() {
        executeTask();
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
