package kr.ac.jejunu.jnu_tong.expended_main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.brandongogetap.stickyheaders.StickyLayoutManager;

import java.util.ArrayList;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.expended_main.sticky_recycler.StickyRecyclerAdapter;

public class ExpendedMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StickyRecyclerAdapter adapter;
    private ExpandedMainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expended_main);

        initView();
        initCityBusRecycler();
    }

    private void initView() {
        findViewById(R.id.city_bus_top).setOnClickListener((v)-> supportFinishAfterTransition());
    }

    private void initCityBusRecycler() {
        recyclerView = findViewById(R.id.recycler_soon_bus);
        recyclerView.setItemAnimator(null);

        adapter = new StickyRecyclerAdapter(this, new ArrayList<>());
        adapter.setDetailClickListener((int position) -> presenter.onDetailClick(position));
        adapter.setOnHeartClickListener(position -> presenter.onHeartClick(position));
        StickyLayoutManager manager = new StickyLayoutManager(this, adapter);

        manager.elevateHeaders(false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            recyclerView.setClipToOutline(true);
        }
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
        super.onBackPressed();
    }
}
