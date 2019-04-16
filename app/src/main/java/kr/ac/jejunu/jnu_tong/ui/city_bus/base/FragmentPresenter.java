package kr.ac.jejunu.jnu_tong.ui.city_bus.base;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public interface FragmentPresenter {
    void onViewCreated();
    void setBusId(String busId);
    void refreshClick();
    RecyclerView.Adapter getAdapter();
}
