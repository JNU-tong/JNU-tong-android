package kr.ac.jejunu.jnu_tong.ui.city_bus.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;
import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 4. 17..
 */
public abstract class RecyclerFragment extends DaggerFragment {
    private RecyclerView.Adapter adapter;
    private FragmentPresenter presenter;
    private String busId;
    private String busType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_bus_list, container, false);

        busId = getArguments().getString("busId");
        busType = getArguments().getString("busType");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = setPresenter();
        presenter.setBusId(busId);
        presenter.onViewCreated();
        adapter = presenter.getAdapter();

        initRecyclerView(view);
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

    protected abstract FragmentPresenter setPresenter();

    public void refresh(){
        presenter.refreshClick();
    }
}

