package kr.ac.jejunu.jnu_tong.detail.city_bus.presenter;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public interface FragmentPresenter {

    void onViewCreated();
    void onPause();
    void onResume();
    void onDestroy();

    void refreshClick();
}
