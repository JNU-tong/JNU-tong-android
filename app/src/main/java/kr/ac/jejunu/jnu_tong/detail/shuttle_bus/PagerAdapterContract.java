package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import java.util.ArrayList;

/**
 * Created by seung-yeol on 2018. 4. 24..
 */

public interface PagerAdapterContract {
    interface View{
        void setProviders(ArrayList<PagerProvider> providers);
    }
    interface Model{
        String[] getBusStopNames(int position);
    }
}
