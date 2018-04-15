package kr.ac.jejunu.jnu_tong.detail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.main.BusStopVO;
import kr.ac.jejunu.jnu_tong.main.BusTimeVO;
import kr.ac.jejunu.jnu_tong.main.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.task.GetBusTimeTask;
import kr.ac.jejunu.jnu_tong.task.TaskResult;

/**
 * Created by seung-yeol on 2018. 4. 10..
 */

public class BusTimeListFragment extends Fragment implements TaskResult<BusTimeVO>{
    private BusTimeRecyclerAdapter adapter;
    private String busID;
    private String busType;

    public static BusTimeListFragment newInstance(String busID, String busType) {
        Bundle args = new Bundle();
        args.putString("busID", busID);
        args.putString("busType", busType);

        BusTimeListFragment fragment = new BusTimeListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_bus_list, container, false);

        busID = getArguments().getString("busID");
        busType = getArguments().getString("busType");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView(view);
        executeTask();
    }

    private void initRecyclerView(View view){
        RecyclerView busRecyclerView = view.findViewById(R.id.recycler_view_bus);

        switch (busType) {
            case "green":
                busRecyclerView.setBackgroundColor(getResources().getColor(R.color.clear_emerald));
                break;
            case "yellow":
                busRecyclerView.setBackgroundColor(getResources().getColor(R.color.clear_yellow));
                break;
            default:
                busRecyclerView.setBackgroundColor(getResources().getColor(R.color.clear_sky));
                break;
        }

        adapter = new BusTimeRecyclerAdapter( new ArrayList<>());

        busRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        busRecyclerView.setAdapter(adapter);

    }

    private void executeTask(){
        GetBusTimeTask getBusTimeTask = new GetBusTimeTask(this);
        getBusTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, CommonData.getBusTimeListURL(busID));
    }


    @Override
    public void setTaskResult(List<BusTimeVO> result) {
        ArrayList<BusTimeVO> remainBusTime = ((CommonData)getActivity().getApplication()).getRemainBusTime(result);
        adapter.add(remainBusTime);
    }
}
