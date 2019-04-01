package kr.ac.jejunu.jnu_tong.ui.main;

import java.util.List;

import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;

/**
 * Created by seung-yeol on 2018. 6. 10..
 */

public interface MainContract {
    interface Presenter{

        void onCreate();

        void refreshClick();

        void setShuttleBookmarkId(int shuttleBookmarkId);

        void clickCityBus();
    }
    interface View {
        void onClickCityBus();
        void setDepartureBusData(Integer[] imgIds, String[] busNos, String time);
        void setJNUEvent(String today, String event);
        void setShuttleBusData(String title, Integer aFirst, Integer bFirst);
    }

    interface Model {
        void setDepartureVOS(List<DepartureBusVO> result);
        Integer[] getImgIds();
        String[] getBusNos();
        String getDepartTime();
        void setJNUEvent(JNUEventVO eventVO);
        String getEvent();
        String getToday();
    }
}
