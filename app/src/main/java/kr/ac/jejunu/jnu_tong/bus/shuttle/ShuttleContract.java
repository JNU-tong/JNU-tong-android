package kr.ac.jejunu.jnu_tong.bus.shuttle;

import java.util.List;

import kr.ac.jejunu.jnu_tong.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public interface ShuttleContract {
    interface ShuttleView {
        void routeTextChange(String leftText, String centerText,String rightText);
        void setPagerPosition(int position);
        void setBusPositionList(String... busStops);
        void setBookMarkId(int shuttleBookmarkPosition);
        void setHeartOn(boolean b);
    }

    interface ShuttleModel {
        void changeProvider(List<ShuttleVO> result);
        String[] getShuttleBusStops();
        void selectARoute();
        void selectBRoute();
        void setBookmarkId(int stopId);
        int getBookmarkkedId();
    }
}
