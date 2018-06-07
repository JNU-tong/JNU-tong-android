package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.support.v4.view.PagerAdapter;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public interface ShuttleContract {
    interface ShuttleView {
        void routeTextChange(String leftText, String centerText,String rightText);
        void setPagerPosition(int position);
    }

    interface ShuttleModel {
    }
}
