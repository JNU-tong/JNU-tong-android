package kr.ac.jejunu.jnu_tong.ui.unfolded_main;

import android.os.Bundle;
import android.widget.TextView;

import com.brandongogetap.stickyheaders.StickyLayoutManager;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerAppCompatActivity;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler.StickyRecyclerAdapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class UnfoldedMainActivity extends DaggerAppCompatActivity implements UnfoldedMainContract.View {
    @Inject
    StickyRecyclerAdapter adapter;

    @Inject
    UnfoldedMainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_unfolded_main);

        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);
        presenter.onCreate();

        initView();
        initCityBusRecycler();
    }

    private void initView() {
        findViewById(R.id.city_bus_top).setOnClickListener((v) -> supportFinishAfterTransition());
        findViewById(R.id.btn_refresh).setOnClickListener((v -> presenter.refreshClick()));
    }
    private void initCityBusRecycler() {
        adapter.setDetailClickListener((int position) -> presenter.onDetailClick(position));
        adapter.setOnHeartClickListener(position -> presenter.onHeartClick(position));
        StickyLayoutManager manager = new StickyLayoutManager(this, adapter);
        manager.elevateHeaders(false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        RecyclerView recyclerView = findViewById(R.id.recycler_soon_bus);
        recyclerView.setItemAnimator(null);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
        super.onBackPressed();
    }

    @Override
    public void setDepartureBusData(Integer[] imgIds, String[] busNos, String time) {
        TextView txtFirstNo = findViewById(R.id.txt_first_no);
        TextView txtSecondNo = findViewById(R.id.txt_second_no);
        TextView txtDepartTime = findViewById(R.id.txt_depart_time);

        if (imgIds[0] == null) txtFirstNo.setVisibility(GONE);
        else {
            txtFirstNo.setVisibility(VISIBLE);
            txtFirstNo.setBackground(getResources().getDrawable(imgIds[0]));
            txtFirstNo.setText(busNos[0]);
        }

        if (busNos[1] == null) txtSecondNo.setVisibility(GONE);
        else {
            txtSecondNo.setVisibility(VISIBLE);
            txtSecondNo.setBackground(getResources().getDrawable(imgIds[1]));
            txtSecondNo.setText(busNos[1]);
        }

        if (time == null) txtDepartTime.setText("버스없음");
        else {
            txtDepartTime.setText(time);
        }
    }
}
