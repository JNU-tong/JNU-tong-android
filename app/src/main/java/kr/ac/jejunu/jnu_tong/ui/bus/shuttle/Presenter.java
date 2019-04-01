package kr.ac.jejunu.jnu_tong.ui.bus.shuttle;

import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;

import java.util.List;

import kr.ac.jejunu.jnu_tong.ActivityPresenter;
import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.task.get.GetShuttleDetailTask;
import kr.ac.jejunu.jnu_tong.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 4. 24..
 */

public class Presenter implements ActivityPresenter, OnTaskResultListener<List<ShuttleVO>>{
    private ShuttleContract.ShuttleView shuttleView;
    private ShuttleContract.ShuttleModel shuttleModel;
    private PagerAdapterContract.View adapterView;
    private PagerAdapterContract.Model adapterModel;

    Presenter(ShuttleContract.ShuttleView shuttleView){
        this.shuttleView = shuttleView;
        shuttleModel = new ShuttleModel();
        shuttleModel.selectARoute();
    }

    @Override
    public void onCreate() {
        selectARoute();
    }

    private void taskStart(String course) {
        GetShuttleDetailTask task = new GetShuttleDetailTask(this);
        task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, CommonApp.getShuttleListURL(course));
    }

    void setAdapterView(PagerAdapterContract.View view){
        this.adapterView = view;
    }

    void setAdapterModel(PagerAdapterContract.Model model){
        this.adapterModel = model;
    }

    void leftBtnClick(int position) {
        if (position > 0){
            shuttleView.setPagerPosition(position - 1);
        }
    }

    void rightBtnClick(int position) {
        if (position < ( (PagerAdapter)adapterModel ).getCount() -1){
            shuttleView.setPagerPosition(position + 1);
        }
    }

    void pageSelect(int position) {
        String[] busStopNames = adapterModel.getBusStopNames(position);
        shuttleView.routeTextChange(busStopNames[0], busStopNames[1], busStopNames[2]);

        if (adapterModel.getBusStopId(position) == shuttleModel.getBookmarkId())
            shuttleView.setHeartOn(true);
        else shuttleView.setHeartOn(false);
    }

    @Override
    public void onDestroy() {
        adapterView = null;
        adapterModel = null;
    }

    @Override
    public void onTaskResult(List<ShuttleVO> result) {
        adapterModel.changeProvider(result);
        adapterView.notifyDataChange();

        shuttleModel.changeProvider(result);
        String[] shuttleBusStops = shuttleModel.getShuttleBusStops();
        shuttleView.setBusPositionList(shuttleBusStops);
    }

    void selectARoute() {
        taskStart("A");
        adapterModel.selectARoute();
        shuttleModel.selectARoute();
    }

    void selectBRoute() {
        taskStart("B");
        adapterModel.selectBRoute();
        shuttleModel.selectBRoute();
    }

    void heartClick(int position) {
        Integer stopId = adapterModel.getBusStopId(position);
        setBookmarkId(stopId);
    }

    void setBookmarkId(int stopId) {
        shuttleModel.setBookmarkId(stopId);
        shuttleView.setBookmark(stopId, adapterModel.getBusStopNameById(stopId));
        shuttleView.setPositionByBookmarkId(shuttleModel.getPositionByBookmarkId());
    }
}
