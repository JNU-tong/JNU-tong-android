package kr.ac.jejunu.jnu_tong.ui.unfolded_main;

import android.util.Log;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.AutoClearedDisposable;
import kr.ac.jejunu.jnu_tong.IDataManager;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.Item;

public class UnfoldedMainPresenter implements UnfoldedMainContract.Presenter {
    private AdapterContract.View<Item> adapterView;
    private AdapterContract.Model adapterModel;
    private UnfoldedMainContract.View view;
    private MainContract.Model mainModel;
    private IDataManager dataManager;
    private AutoClearedDisposable autoClearedDisposable;

    @Inject
    UnfoldedMainPresenter(AutoClearedDisposable autoClearedDisposable, UnfoldedMainContract.View view, IDataManager dataManager) {
        this.view = view;
        this.dataManager = dataManager;
        this.autoClearedDisposable = autoClearedDisposable;
        mainModel = dataManager.getMainModel();
    }

    @Override
    public void onCreate() {
        autoClearedDisposable.add(
                dataManager.getDepartureBusObservable().subscribe(departureBusVOS -> {
                    if (departureBusVOS == null || departureBusVOS.size() == 0) {
                        Log.e(this.toString(), "결과가 없어.. ");
                        view.setDepartureBusData(new Integer[]{null, null}, new String[]{null, null}, null);
                    } else {
                        adapterView.setItems(adapterModel.taskResultItems(departureBusVOS));
                        mainModel.setDepartureVOS(departureBusVOS);
                        view.setDepartureBusData(mainModel.getImgIds(), mainModel.getBusNos(), mainModel.getDepartTime());
                    }
                })
        );
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
        dataManager.getDepartureBusList();
    }
}
