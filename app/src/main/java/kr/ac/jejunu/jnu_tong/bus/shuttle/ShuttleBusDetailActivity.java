package kr.ac.jejunu.jnu_tong.bus.shuttle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tmall.ultraviewpager.UltraViewPager;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 4. 22..
 */

public class ShuttleBusDetailActivity extends AppCompatActivity implements ShuttleContract.ShuttleView {
    private TextView leftRouteText;
    private TextView rightRouteText;
    private TextView aRouteSelectText;
    private TextView bRouteSelectText;
    private Presenter presenter;
    private UltraViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shuttle);

        presenter = new Presenter(this);

        initView();
        initViewPager();

        presenter.onCreate();
    }

    private void initView() {
        aRouteSelectText = findViewById(R.id.text_a_route);
        bRouteSelectText = findViewById(R.id.text_b_route);
        leftRouteText = findViewById(R.id.left_stop);
        rightRouteText = findViewById(R.id.right_stop);

        aRouteSelectText.setOnClickListener(view -> {
            aRouteSelectText.setTextColor(getResources().getColor(R.color.navy));
            bRouteSelectText.setTextColor(getResources().getColor(R.color.very_light_gray));
            findViewById(R.id.a_bar).setVisibility(View.VISIBLE);
            findViewById(R.id.b_bar).setVisibility(View.GONE);

            viewPager.setCurrentItem(0, false);
            presenter.selectARoute();
        });
        bRouteSelectText.setOnClickListener(view -> {
            aRouteSelectText.setTextColor(getResources().getColor(R.color.very_light_gray));
            bRouteSelectText.setTextColor(getResources().getColor(R.color.navy));
            findViewById(R.id.a_bar).setVisibility(View.GONE);
            findViewById(R.id.b_bar).setVisibility(View.VISIBLE);

            viewPager.setCurrentItem(0, false);
            presenter.selectBRoute();
        });
        leftRouteText.setOnClickListener(view -> presenter.leftBtnClick(viewPager.getCurrentItem()));
        rightRouteText.setOnClickListener(view -> presenter.rightBtnClick(viewPager.getCurrentItem()));

        ImageButton btnHeart = findViewById(R.id.btn_heart);
        btnHeart.setOnClickListener(view -> {
            presenter.heartClick(viewPager.getCurrentItem());
        });
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.ultra_view_pager);
        viewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        viewPager.setMultiScreen(0.85f);
        viewPager.setInfiniteLoop(false);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                presenter.pageSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        setViewPagerAdapter();
    }

    private void setViewPagerAdapter() {
        UltraPagerAdapter ultraPagerAdapter = new UltraPagerAdapter(this);
        presenter.setAdapterView(ultraPagerAdapter);
        presenter.setAdapterModel(ultraPagerAdapter);
        viewPager.setAdapter(ultraPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View customView = getLayoutInflater().inflate(R.layout.actionbar_custom, new LinearLayout(this), false);
        ((TextView) customView.findViewById(R.id.text_title)).setText("셔틀버스");
        actionBar.setCustomView(customView);
        actionBar.getCustomView().findViewById(R.id.back).setOnClickListener(view -> finish());
        actionBar.setElevation(0);

        return true;
    }

    @Override
    public void routeTextChange(String leftText, String centerText, String rightText) {
        leftRouteText.setText(leftText);
        rightRouteText.setText(rightText);
        ((TextView) findViewById(R.id.center_stop)).setText(centerText);
    }

    @Override
    public void setPagerPosition(int position) {
        viewPager.setCurrentItem(position, true);
    }

    @Override
    public void setBusPositionList(String... busStops) {
        LinearLayout busStopLayout = findViewById(R.id.stops_layout);

        Log.e("cc", "busStops.length: " + busStops.length);
        for (String s : busStops) {
            Log.e("cc", "setBusPositionList: " + s);
        }

        for (int i = 0; i < busStops.length; i++) {
            ((TextView) busStopLayout.findViewWithTag("stop_" + i)).setText(busStops[i]);
            Log.e("ccc", "처음: " + busStops[i]);
        }

        for (int i = busStops.length - 1; i < 4; i++) {
            ((TextView) busStopLayout.findViewWithTag("stop_" + i)).setText("");
            Log.e("ccc", "나머지: " + busStops[i]);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        viewPager = null;
        presenter.onDestroy();
    }
}
