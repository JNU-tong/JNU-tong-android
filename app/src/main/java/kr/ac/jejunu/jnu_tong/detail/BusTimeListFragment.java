package kr.ac.jejunu.jnu_tong.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.main.BusStopVO;
import kr.ac.jejunu.jnu_tong.main.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 10..
 */

public class BusTimeListFragment extends Fragment{

    public static BusTimeListFragment newInstance() {
        Bundle args = new Bundle();

        BusTimeListFragment fragment = new BusTimeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_bus_list, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView busRecyclerView = view.findViewById(R.id.recycler_view_bus);
        BusTimeRecyclerAdapter adapter = new BusTimeRecyclerAdapter( testVOSCreate());

        busRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        busRecyclerView.setAdapter(adapter);
    }

    private ArrayList<DepartureBusVO> testVOSCreate(){
        return new ArrayList<>();
    }
}
