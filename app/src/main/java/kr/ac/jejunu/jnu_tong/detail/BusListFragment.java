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

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class BusListFragment extends Fragment {
    public static BusListFragment newInstance() {
        Bundle args = new Bundle();

        BusListFragment fragment = new BusListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_bus_list, container, false);
        testVOSCreate();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView busRecyclerView = view.findViewById(R.id.recycler_view_bus);
        BusRecyclerAdapter adapter = new BusRecyclerAdapter( testVOSCreate());

        busRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        busRecyclerView.setAdapter(adapter);
    }

    private ArrayList<BusStopVO> testVOSCreate(){
        ArrayList<BusStopVO> testVOS = new ArrayList<>();
        testVOS.add(new BusStopVO("내집"));
        testVOS.add(new BusStopVO("우리집"));
        testVOS.add(new BusStopVO("너네집"));
        testVOS.add(new BusStopVO("니네집"));
        testVOS.add(new BusStopVO("걔네집"));
        testVOS.add(new BusStopVO("쟤네집"));
        testVOS.add(new BusStopVO("자이네집"));

        return testVOS;
    }
}
