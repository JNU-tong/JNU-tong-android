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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.main.BusStopVO;
import kr.ac.jejunu.jnu_tong.task.TaskResult;
import kr.ac.jejunu.jnu_tong.task.GetBusStopTask;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class BusStopListFragment extends Fragment implements TaskResult<BusStopVO>{
    private ArrayList<BusStopVO> busStopVOS;
    private BusStopRecyclerAdapter adapter;
    private String busID;
    private String busType;

    public static BusStopListFragment newInstance(String busID, String busType) {
        Bundle args = new Bundle();

        args.putString("busID", busID);
        args.putString("busType", busType);

        BusStopListFragment fragment = new BusStopListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_bus_list, container, false);
        busStopVOS = new ArrayList<>();

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

        adapter = new BusStopRecyclerAdapter( new ArrayList<>());

        busRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        busRecyclerView.setAdapter(adapter);
    }

    private void executeTask(){
        GetBusStopTask getBusStopTask = new GetBusStopTask(this);
        getBusStopTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, CommonData.getBusStopListURL(busID));
    }

    @Override
    public void setTaskResult(List<BusStopVO> result) {
        if (result != null){
            busStopVOS.addAll(result);
            adapter.add(busStopVOS);
        }
    }
}
