package kr.ac.jejunu.jnu_tong.expended_main;

import kr.ac.jejunu.jnu_tong.main.MainAdapterContract;

public class ExpendedPresenter implements ExpandedMainContract.Presenter {
    private MainAdapterContract.Model adapterModel;

    @Override
    public void onDetailClick(int position) {
        adapterModel.goDetailActivity(position);
    }

    @Override
    public void onHeartClick(int position) {
        adapterModel.heartClick(position);
    }
}
