package kr.ac.jejunu.jnu_tong.ui.unfolded_main;

import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.Item;
import kr.ac.jejunu.jnu_tong.ui.main.MainAdapterContract;

public interface UnfoldedMainContract {
    interface Presenter{
        void onDetailClick(int position);
        void onHeartClick(int position);
        void setAdapterView(MainAdapterContract.View<Item> adapterView);
        void setAdapterModel(MainAdapterContract.Model adapterModel);

    }

    interface View{

    }

    interface Model{

    }
}
