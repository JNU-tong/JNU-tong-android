package kr.ac.jejunu.jnu_tong.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.brandongogetap.stickyheaders.exposed.StickyHeaderListener;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.ChildItem;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.HeaderItem;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.Item;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.StickyRecyclerAdapter;
import kr.ac.jejunu.jnu_tong.task.GetDepartureBusTask;
import kr.ac.jejunu.jnu_tong.task.TaskResult;

public class MainActivity extends AppCompatActivity implements TaskResult<DepartureBusVO> {
    private RelativeLayout top;
    private LinearLayout busComeInLayout;
    private LinearLayout cityBusLayout;
    private LinearLayout shuttleBusLayout;

    private int topHeight;
    private int cityBusHeight;
    private int cityBusBottomMargin;

    private boolean expanded = false;
    private ArrayList<Item> items;
    private StickyRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executeTask();

        initTopImage();
        initCityBusRecycler();
        initCityBusLayout();
        initShuttleBusLayout();

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

        View bus = View.inflate(this, R.layout.vew_bus_num, null);
        bus.setOnClickListener(v -> {
            if (!expanded) {
                expanded = true;
                runOnUiThread(() -> {
                    TransitionManager.beginDelayedTransition(top);
                    ViewGroup.LayoutParams params = top.getLayoutParams();
                    params.height = 0;
                    top.setLayoutParams(params);

                    TransitionManager.beginDelayedTransition(cityBusLayout);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cityBusLayout.getLayoutParams();
                    layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
                    layoutParams.bottomMargin += cityBusHeight / 2;
                    cityBusLayout.setLayoutParams(layoutParams);

                    TransitionManager.beginDelayedTransition(shuttleBusLayout);
                    LinearLayout.LayoutParams shuttleBusParams = (LinearLayout.LayoutParams) shuttleBusLayout.getLayoutParams();
                    shuttleBusParams.topMargin -= cityBusHeight / 2;
                    shuttleBusLayout.setLayoutParams(shuttleBusParams);

                    adapter.setData(items);
                });
            } else {
                expanded = false;
                adapter.clear();
                runOnUiThread(() -> {
                    TransitionManager.beginDelayedTransition(top);
                    ViewGroup.LayoutParams params = top.getLayoutParams();
                    params.height = topHeight;
                    top.setLayoutParams(params);

                    TransitionManager.beginDelayedTransition(cityBusLayout);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cityBusLayout.getLayoutParams();
                    layoutParams.height = cityBusHeight;
                    layoutParams.bottomMargin = cityBusBottomMargin;
                    cityBusLayout.setLayoutParams(layoutParams);


                    TransitionManager.beginDelayedTransition(shuttleBusLayout);
                    LinearLayout.LayoutParams shuttleBusParams = (LinearLayout.LayoutParams) shuttleBusLayout.getLayoutParams();
                    shuttleBusParams.topMargin = 0;
                    shuttleBusLayout.setLayoutParams(shuttleBusParams);
                });
            }
        });

        busComeInLayout.addView(bus);
    }

    private void initShuttleBusLayout() {
        shuttleBusLayout = findViewById(R.id.shuttle_bus);
        shuttleBusLayout.setOnClickListener(view -> {
           Intent intent = new Intent(this, TestActivity.class);
           startActivity(intent);
        });
    }

    private void initCityBusRecycler() {
        recyclerView = findViewById(R.id.recycler_soon_bus);

        adapter = new StickyRecyclerAdapter(new ArrayList<>());
        StickyLayoutManager manager = new StickyLayoutManager(this, adapter);

        manager.elevateHeaders(true);
        manager.setStickyHeaderListener(new StickyHeaderListener() {
            @Override
            public void headerAttached(View headerView, int adapterPosition) {
                Log.e("Listener", "Attached with position: " + adapterPosition);
            }

            @Override
            public void headerDetached(View headerView, int adapterPosition) {
                Log.e("Listener", "Detached with position: " + adapterPosition);
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void executeTask() {
        GetDepartureBusTask getDepartureBusTask = new GetDepartureBusTask(this);
        getDepartureBusTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

    @Override
    public void setTaskResult(List<DepartureBusVO> result) {
        if (result == null || result.size() == 0){
            Log.e(this.toString(), "결과가 없어.. " );
        }
        else {
            ArrayList<DepartureBusVO> arrayList = new ArrayList<>(result);
            ArrayList<DepartureBusVO> oftenBus = new ArrayList<>();
            ArrayList<DepartureBusVO> normalBus = new ArrayList<>();

            for (DepartureBusVO vo :
                    arrayList) {
                if (((CommonData) getApplication()).hasOftenBus(vo.getLineID())) {
                    oftenBus.add(vo);
                } else {
                    normalBus.add(vo);
                }
            }

            items = new ArrayList<>();
            items.add(new HeaderItem("자주타는버스"));
            for (DepartureBusVO vo :
                    oftenBus) {
                items.add(new ChildItem(vo));
            }

            items.add(new HeaderItem("도착예정버스"));
            for (DepartureBusVO vo :
                    normalBus) {
                items.add(new ChildItem(vo));
            }

            ((CommonData)getApplication()).setTestData(items);
        }
    }
}