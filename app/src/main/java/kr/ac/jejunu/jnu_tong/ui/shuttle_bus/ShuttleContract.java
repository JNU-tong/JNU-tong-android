package kr.ac.jejunu.jnu_tong.ui.shuttle_bus;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public interface ShuttleContract {
    interface View {
        void routeTextChange(String leftText, String centerText,String rightText);
        void setPagerPosition(int position);
        void setBusPositionList(String... busStops);
//        void setBookmark(int shuttleBookmarkPosition, String shuttleBookmarkTitle);
        void setHeartOn(boolean b);
        void setPositionByBookmarkId(int position);
    }

    interface Model {
        void changeProvider(List<ShuttleVO> result);
        String[] getShuttleBusStops();
        void selectARoute();
        void selectBRoute();
        void setBookmarkId(int stopId);
        int getBookmarkId();
        Integer getPositionByBookmarkId();
    }

    interface Presenter{
        void onCreate();
        void onDestroy();
        void taskStart(String course);

        PagerAdapter getPagerAdapter();

        void leftBtnClick(int position);
        void rightBtnClick(int position);
        void pageSelect(int position);
        void selectARoute();
        void selectBRoute();
        void heartClick(int position);
        void setBookmarkId(int stopId);
    }
}
