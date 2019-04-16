package kr.ac.jejunu.jnu_tong.ui.city_bus.time;

import android.os.Bundle;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.ui.city_bus.CityBusScope;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.FragmentPresenter;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.RecyclerFragment;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */
@CityBusScope
public class BusTimeRecyclerFragment extends RecyclerFragment {
    @Inject
    FragmentPresenter presenter;

    public static BusTimeRecyclerFragment newInstance(String busID, String busType) {
        Bundle args = new Bundle();
        args.putString("busId", busID);
        args.putString("busType", busType);

        BusTimeRecyclerFragment fragment = new BusTimeRecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentPresenter setPresenter() {
        return presenter;
    }
}
