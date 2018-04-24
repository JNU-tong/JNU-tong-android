package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.support.v4.view.PagerAdapter;

/**
 * Created by seung-yeol on 2018. 4. 24..
 */

public interface ShuttleView {
    void routeTextChange(String leftText, String centerText,String rightText);
    void setAdapter(PagerAdapter adapter);
    void setPagerPosition(int position);
}
