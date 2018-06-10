package kr.ac.jejunu.jnu_tong.detail.city_bus.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.detail.city_bus.adapter.AdapterContract;
import kr.ac.jejunu.jnu_tong.detail.city_bus.presenter.FragmentPresenter;

/**
 * Created by seung-yeol on 2018. 4. 17..
 */

public abstract class RecyclerFragment extends Fragment {
    protected RecyclerView.Adapter adapter;
    protected String busID;
    protected String busType;

    private FragmentPresenter presenter;

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

        adapter = setAdapter();
        presenter = setPresenter((AdapterContract.View)adapter, busID);

        initRecyclerView(view);
        presenter.onViewCreated();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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

        busRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        busRecyclerView.setAdapter(adapter);
    }

    //어뎁터 설정
    protected abstract RecyclerView.Adapter setAdapter();
    //프레젠터 설정
    protected abstract FragmentPresenter setPresenter(AdapterContract.View view, String busID);

    public void refresh(){
        presenter.refreshClick();
    }
}

