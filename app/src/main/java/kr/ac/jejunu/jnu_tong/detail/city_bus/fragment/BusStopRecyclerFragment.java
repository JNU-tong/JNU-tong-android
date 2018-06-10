package kr.ac.jejunu.jnu_tong.detail.city_bus.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import kr.ac.jejunu.jnu_tong.detail.city_bus.adapter.AdapterContract;
import kr.ac.jejunu.jnu_tong.detail.city_bus.adapter.BusStopRecyclerAdapter;
import kr.ac.jejunu.jnu_tong.detail.city_bus.presenter.BusStopFragmentPresenter;
import kr.ac.jejunu.jnu_tong.detail.city_bus.presenter.FragmentPresenter;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusStopRecyclerFragment extends RecyclerFragment {
    public static BusStopRecyclerFragment newInstance(String busID, String busType) {
        Bundle args = new Bundle();
        args.putString("busID", busID);
        args.putString("busType", busType);

        BusStopRecyclerFragment fragment = new BusStopRecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.Adapter setAdapter() {
        return new BusStopRecyclerAdapter(new ArrayList<>());
    }

    @Override
    protected FragmentPresenter setPresenter(AdapterContract.View view, String busID) {
        return new BusStopFragmentPresenter(view, busID);
    }
}
