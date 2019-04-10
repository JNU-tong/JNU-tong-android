package kr.ac.jejunu.jnu_tong.ui.shuttle_bus;

import java.util.List;

import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public interface ShuttleContract {
    interface ShuttleView {
        void routeTextChange(String leftText, String centerText,String rightText);
        void setPagerPosition(int position);
        void setBusPositionList(String... busStops);
        void setBookmark(int shuttleBookmarkPosition, String shuttleBookmarkTitle);
        void setHeartOn(boolean b);
        void setPositionByBookmarkId(int position);
    }

    interface ShuttleModel {
        void changeProvider(List<ShuttleVO> result);
        String[] getShuttleBusStops();
        void selectARoute();
        void selectBRoute();
        void setBookmarkId(int stopId);
        int getBookmarkId();
        Integer getPositionByBookmarkId();
    }
}
