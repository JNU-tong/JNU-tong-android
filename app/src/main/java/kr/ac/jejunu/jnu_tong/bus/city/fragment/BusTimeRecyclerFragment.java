package kr.ac.jejunu.jnu_tong.bus.city.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import kr.ac.jejunu.jnu_tong.bus.city.adapter.AdapterContract;
import kr.ac.jejunu.jnu_tong.bus.city.adapter.BusTimeRecyclerAdapter;
import kr.ac.jejunu.jnu_tong.bus.city.presenter.BusTimeFragmentPresenter;
import kr.ac.jejunu.jnu_tong.bus.city.presenter.FragmentPresenter;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusTimeRecyclerFragment extends RecyclerFragment {

    public static BusTimeRecyclerFragment newInstance(String busID, String busType) {
        Bundle args = new Bundle();
        args.putString("busID", busID);
        args.putString("busType", busType);

        BusTimeRecyclerFragment fragment = new BusTimeRecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.Adapter setAdapter() {
        return new BusTimeRecyclerAdapter(new ArrayList<>());
    }

    @Override
    protected FragmentPresenter setPresenter(AdapterContract.View view, String busID) {
        return new BusTimeFragmentPresenter(view, busID);
    }
}
