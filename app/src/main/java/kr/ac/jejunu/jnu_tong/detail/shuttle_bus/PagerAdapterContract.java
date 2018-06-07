package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import java.util.List;

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
        void selectARoute();
        void selectBRoute();
        void changeProvider(List<ShuttleVO> provider);
    }
}
