package kr.ac.jejunu.jnu_tong.ui.unfolded_main;

import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.Item;

public interface UnfoldedMainContract {
    interface Presenter{
        void onCreate();
        void onDetailClick(int position);
        void onHeartClick(int position);
        void setAdapterView(AdapterContract.View<Item> adapterView);
        void setAdapterModel(AdapterContract.Model adapterModel);
        void refreshClick();

    }

    interface View{
        void setDepartureBusData(Integer[] imgIds, String[] busNos, String time);
    }
}
