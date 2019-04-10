package kr.ac.jejunu.jnu_tong.ui.bus.city.presenter;

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
