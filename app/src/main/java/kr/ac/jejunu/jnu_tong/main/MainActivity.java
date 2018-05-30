package kr.ac.jejunu.jnu_tong.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.brandongogetap.stickyheaders.StickyLayoutManager;

import java.util.ArrayList;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.detail.shuttle_bus.ShuttleBusDetailActivity;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.StickyRecyclerAdapter;

public class MainActivity extends AppCompatActivity implements MainView {
    private RelativeLayout top;
    private LinearLayout busComeInLayout;
    private LinearLayout cityBusLayout;
    private LinearLayout shuttleBusLayout;

    private int topHeight;
    private int cityBusHeight;
    private int cityBusBottomMargin;

    private boolean expanded = false;

    private StickyRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTopImage();
        initCityBusRecycler();
        initCityBusLayout();
        initShuttleBusLayout();

        presenter = new Presenter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);
        presenter.onCreate();
    }

    private void initTopImage() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_expand_anim);
        busComeInLayout = findViewById(R.id.bus_come_in);
        View topImage = findViewById(R.id.main_back);
        topImage.startAnimation(animation);

        top = findViewById(R.id.top);
        ViewGroup.LayoutParams params = top.getLayoutParams();
        topHeight = params.height;
    }

    private void initCityBusLayout() {
        cityBusLayout = findViewById(R.id.city_bus);
        LinearLayout.LayoutParams cityBusParams = (LinearLayout.LayoutParams) cityBusLayout.getLayoutParams();
        cityBusHeight = cityBusParams.height;
        cityBusBottomMargin = cityBusParams.bottomMargin;

        View bus = View.inflate(this, R.layout.view_bus_num, null);
        cityBusLayout.setOnClickListener(v -> presenter.clickCityBus(expanded));

        busComeInLayout.addView(bus);
    }

    private void initShuttleBusLayout() {
        shuttleBusLayout = findViewById(R.id.shuttle_bus);
        shuttleBusLayout.setOnClickListener(view -> startActivity(new Intent(this, ShuttleBusDetailActivity.class)));
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
    public void onClickCityBus() {
        if (!expanded) {
            expanded = true;

            TransitionManager.beginDelayedTransition(cityBusLayout);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cityBusLayout.getLayoutParams();
            layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
            layoutParams.bottomMargin += cityBusHeight / 2;
            cityBusLayout.setLayoutParams(layoutParams);

            TransitionManager.beginDelayedTransition(shuttleBusLayout);
            LinearLayout.LayoutParams shuttleBusParams = (LinearLayout.LayoutParams) shuttleBusLayout.getLayoutParams();
            shuttleBusParams.topMargin -= cityBusHeight / 2;
            shuttleBusLayout.setLayoutParams(shuttleBusParams);

            TransitionManager.beginDelayedTransition(top);
            ViewGroup.LayoutParams params = top.getLayoutParams();
            params.height = 0;
            top.setLayoutParams(params);

        } else {
            expanded = false;
            recyclerView.setVisibility(View.GONE);

            TransitionManager.beginDelayedTransition(top);
            ViewGroup.LayoutParams params = top.getLayoutParams();
            params.height = topHeight;
            top.setLayoutParams(params);

            TransitionManager.beginDelayedTransition(shuttleBusLayout);
            LinearLayout.LayoutParams shuttleBusParams = (LinearLayout.LayoutParams) shuttleBusLayout.getLayoutParams();
            shuttleBusParams.topMargin = 0;
            shuttleBusLayout.setLayoutParams(shuttleBusParams);

            TransitionManager.beginDelayedTransition(cityBusLayout);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cityBusLayout.getLayoutParams();
            layoutParams.height = cityBusHeight;
            layoutParams.bottomMargin = cityBusBottomMargin;
            cityBusLayout.setLayoutParams(layoutParams);

            //야매로 했어요..   recyclerView가 없어지는 모션때문에 cityBusLayout이 늦게 반응해서 클릭할때 애초에 GONE시켰다가
            // 끝나고 0.5초후에 다시 VISIBLE했습니다. 클릭했을때 VISIBLE을 실행하면 recyclerView가 만들어지는 부분에서 또
            // 딜레이가 생겨서 미리 VISIBLE했습니다.
            Handler handler = new Handler();
            handler.postDelayed(() -> recyclerView.setVisibility(View.VISIBLE), 500);
        }
    }
}