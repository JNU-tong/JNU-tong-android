package kr.ac.jejunu.jnu_tong.ui.unfolded_main;

import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.Item;
import kr.ac.jejunu.jnu_tong.ui.main.MainAdapterContract;

public class UnfoldedPresenter implements UnfoldedMainContract.Presenter {
    private MainAdapterContract.View<Item> adapterView;
    private MainAdapterContract.Model adapterModel;

    @Override
    public void onDetailClick(int position) {
        adapterModel.goDetailActivity(position);
    }

    @Override
    public void onHeartClick(int position) {
        adapterModel.heartClick(position);
    }

    @Override
    public void setAdapterView(MainAdapterContract.View<Item> adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setAdapterModel(MainAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
