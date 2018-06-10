package kr.ac.jejunu.jnu_tong.main;

import android.view.View;

import java.util.List;

import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 6. 10..
 */

public interface MainContract {
    interface View {
        void onClickCityBus();
        void setDepartureBusData(Integer[] imgIds, String[] busNos, String time);
    }

    interface Model {
        void setDepartureVOS(List<DepartureBusVO> result);
        Integer[] getImgIds();
        String[] getBusNos();
        String getDepartTime();
    }

}
