package kr.ac.jejunu.jnu_tong.main;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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

//        executeTask();

        initTopImage();
        initCityBusRecycler();
        initCityBusLayout();
        initShuttleBusLayout();

        departureBusSampleRead();
    }

    private void departureBusSampleRead() {
        AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open("departureBusSample.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer);

            LinkedList<DepartureBusVO> departureBusVOS = new LinkedList<>();

            try {
                JSONObject responseObject = new JSONObject(json);
                Iterator iterator = responseObject.keys();

                LinkedList<String> keys = new LinkedList<>();
                for (Iterator it = iterator; it.hasNext(); ) {
                    keys.add((String) it.next());
                }

                for (String key :
                        keys) {
                    JSONObject jsonObject = responseObject.getJSONObject(key);
                    JSONObject cityBusLineInfo = jsonObject.getJSONObject("cityBusLineInfo");
                    JSONObject remainTime = jsonObject.getJSONObject("remainTime");

                    DepartureBusVO vo = new DepartureBusVO();
                    vo.setLineID(cityBusLineInfo.getString("lineId"));
                    vo.setLineNo(cityBusLineInfo.getString("lineNo"));
                    vo.setDetailLineNo(cityBusLineInfo.getString("detailLineNo"));
                    vo.setDescription(cityBusLineInfo.getString("description"));
                    vo.setFirst(remainTime.isNull("first") ? -1 : remainTime.getInt("first"));
                    vo.setSecond(remainTime.isNull("second") ? -1 : remainTime.getInt("second"));

                    departureBusVOS.add(vo);
                }

                setTaskResult(departureBusVOS);

            } catch (JSONException e) {
                Log.e(this.toString(), "JSON 익셉션! : " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        bus.setOnClickListener(v -> {
            if (!expanded) {
                expanded = true;

                runOnUiThread(() -> {
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
                });

            } else {
                expanded = false;
                recyclerView.setVisibility(View.GONE);

                runOnUiThread(() -> {
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

                });

                //야매로 했어요..   recyclerView가 없어지는 모션때문에 cityBusLayout이 늦게 반응해서 클릭할때 애초에 GONE시켰다가
                // 끝나고 0.5초후에 다시 VISIBLE했습니다. 클릭했을때 VISIBLE을 실행하면 recyclerView가 만들어지는 부분에서 또
                // 딜레이가 생겨서 미리 VISIBLE했습니다.
                Handler handler = new Handler();
                handler.postDelayed(() -> recyclerView.setVisibility(View.VISIBLE), 500);
            }
        });

        busComeInLayout.addView(bus);
    }

    private void initShuttleBusLayout() {
        shuttleBusLayout = findViewById(R.id.shuttle_bus);
        /*shuttleBusLayout.setOnClickListener(view -> {
           Intent intent = new Intent(this, TestActivity.class);
           startActivity(intent);
        });*/
    }

    private void initCityBusRecycler() {
        recyclerView = findViewById(R.id.recycler_soon_bus);
        recyclerView.setItemAnimator(null);

        adapter = new StickyRecyclerAdapter(new ArrayList<>());
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

    private void executeTask() {
        GetDepartureBusTask getDepartureBusTask = new GetDepartureBusTask(this);
        getDepartureBusTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

    @Override
    public void setTaskResult(List<DepartureBusVO> result) {
        if (result == null || result.size() == 0) {
            Log.e(this.toString(), "결과가 없어.. ");
        } else {
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

            adapter.setData(items);
        }
    }
}