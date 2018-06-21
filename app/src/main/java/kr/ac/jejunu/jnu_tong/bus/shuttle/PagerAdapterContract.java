package kr.ac.jejunu.jnu_tong.bus.shuttle;

import java.util.List;

import kr.ac.jejunu.jnu_tong.bus.shuttle.route.Route;
import kr.ac.jejunu.jnu_tong.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 4. 24..
 */

public interface PagerAdapterContract {
    interface View{
        void notifyDataChange();
    }
    interface Model{
        String[] getBusStopNames(int position);
        String getBusStopNameById(int id);
        void selectARoute();
        void selectBRoute();
        void changeProvider(List<ShuttleVO> provider);
        int getBusStopId(int position);
    }
}
