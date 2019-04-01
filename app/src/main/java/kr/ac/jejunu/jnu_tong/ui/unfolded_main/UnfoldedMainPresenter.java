package kr.ac.jejunu.jnu_tong.ui.unfolded_main;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.Item;

public class UnfoldedMainPresenter implements UnfoldedMainContract.Presenter {
    private AdapterContract.View<Item> adapterView;
    private AdapterContract.Model adapterModel;

    @Inject
    UnfoldedMainPresenter(AdapterContract.View<Item> adapterView) {
        this.adapterView = adapterView;
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
}
