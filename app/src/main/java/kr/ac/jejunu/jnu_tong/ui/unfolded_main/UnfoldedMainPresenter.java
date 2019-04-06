package kr.ac.jejunu.jnu_tong.ui.unfolded_main;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.IDataManager;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.Item;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;

public class UnfoldedMainPresenter implements UnfoldedMainContract.Presenter {
    private AdapterContract.View<Item> adapterView;
    private AdapterContract.Model adapterModel;
    private UnfoldedMainContract.View view;
    private MainContract.Model mainModel;
    private IDataManager dataManager;

    private OnTaskResultListener<List<DepartureBusVO>> onTaskResultListener;
    private OnTaskResultListener<JNUEventVO> eventVOOnTaskResultListener;

    @Inject
    UnfoldedMainPresenter(UnfoldedMainContract.View view, IDataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
        mainModel = dataManager.getMainModel();
    }

    @Override
    public void onCreate() {
        onTaskResultListener = new OnTaskResultListener<List<DepartureBusVO>>() {
            @Override
            public void onTaskResult(List<DepartureBusVO> result) {
                if (result == null || result.size() == 0) {
                    Log.e(this.toString(), "결과가 없어.. ");
                    view.setDepartureBusData(new Integer[]{null, null}, new String[]{null, null}, null);
                } else {
                    adapterView.setItems(adapterModel.taskResultItems(result));
                    mainModel.setDepartureVOS(result);
                    view.setDepartureBusData(mainModel.getImgIds(), mainModel.getBusNos(), mainModel.getDepartTime());
                }
            }
        };
        eventVOOnTaskResultListener = result -> {
            mainModel.setJNUEvent(result);
//            mainView.setJNUEvent(mainModel.getToday(), mainModel.getEvent());
        };

        dataManager.executeDepartureBusTask(onTaskResultListener);
        dataManager.executeJNUEventTask(eventVOOnTaskResultListener);
    }

    @Override
    public void onDetailClick(int position) {
        adapterModel.goDetailActivity(position);
    }

    @Override
    public void onHeartClick(int position) {
        adapterModel.heartClick(position);
    }

    @Override
    public void setAdapterView(AdapterContract.View<Item> adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setAdapterModel(AdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void refreshClick() {
        dataManager.executeDepartureBusTask(onTaskResultListener);
        dataManager.executeJNUEventTask(eventVOOnTaskResultListener);
    }
}
