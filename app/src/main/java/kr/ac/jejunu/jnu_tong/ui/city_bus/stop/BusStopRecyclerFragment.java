package kr.ac.jejunu.jnu_tong.ui.city_bus.stop;

import android.os.Bundle;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.ui.city_bus.CityBusScope;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.FragmentPresenter;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.RecyclerFragment;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */
@CityBusScope
public class BusStopRecyclerFragment extends RecyclerFragment {
    @Inject
    FragmentPresenter presenter;

    public static BusStopRecyclerFragment newInstance(String busID, String busType) {
        Bundle args = new Bundle();
        args.putString("busId", busID);
        args.putString("busType", busType);

        BusStopRecyclerFragment fragment = new BusStopRecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FragmentPresenter setPresenter() {
        return presenter;
    }
}
