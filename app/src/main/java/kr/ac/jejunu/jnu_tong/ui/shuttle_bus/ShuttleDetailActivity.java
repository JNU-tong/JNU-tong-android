package kr.ac.jejunu.jnu_tong.ui.shuttle_bus;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tmall.ultraviewpager.UltraViewPager;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.ViewPager;
import dagger.android.support.DaggerAppCompatActivity;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.data.IDataManager;

/**
 * Created by seung-yeol on 2018. 4. 22..
 */

public class ShuttleDetailActivity extends DaggerAppCompatActivity implements ShuttleContract.View {
    @Inject
    IDataManager dataManager;
    @Inject
    ShuttleContract.Presenter shuttlePresenter;
    private TextView leftRouteText;
    private TextView rightRouteText;
    private TextView aRouteSelectText;
    private TextView bRouteSelectText;
    private ImageButton btnHeart;
    private UltraViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shuttle);

        initView();
        initViewPager();

        shuttlePresenter.onCreate();
    }

    private void initView() {
        aRouteSelectText = findViewById(R.id.txt_a_route);
        bRouteSelectText = findViewById(R.id.txt_b_route);
        leftRouteText = findViewById(R.id.left_stop);
        rightRouteText = findViewById(R.id.right_stop);

        aRouteSelectText.setOnClickListener(view -> {
            aRouteSelectText.setTextColor(getResources().getColor(R.color.navy));
            bRouteSelectText.setTextColor(getResources().getColor(R.color.very_light_gray));
            findViewById(R.id.a_bar).setVisibility(android.view.View.VISIBLE);
            findViewById(R.id.b_bar).setVisibility(android.view.View.GONE);

            viewPager.setCurrentItem(0, false);
            shuttlePresenter.selectARoute();
        });
        bRouteSelectText.setOnClickListener(view -> {
            aRouteSelectText.setTextColor(getResources().getColor(R.color.very_light_gray));
            bRouteSelectText.setTextColor(getResources().getColor(R.color.navy));
            findViewById(R.id.a_bar).setVisibility(android.view.View.GONE);
            findViewById(R.id.b_bar).setVisibility(android.view.View.VISIBLE);

            viewPager.setCurrentItem(0, false);
            shuttlePresenter.selectBRoute();
        });
        leftRouteText.setOnClickListener(view -> shuttlePresenter.leftBtnClick(viewPager.getCurrentItem()));
        rightRouteText.setOnClickListener(view -> shuttlePresenter.rightBtnClick(viewPager.getCurrentItem()));

        btnHeart = findViewById(R.id.btn_heart);
        btnHeart.setOnClickListener(view -> {
            view.setBackground(getResources().getDrawable(R.mipmap.ic_heart_on));
            shuttlePresenter.heartClick(viewPager.getCurrentItem());
        });
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.ultra_view_pager);
        viewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        viewPager.setMultiScreen(0.85f);
        viewPager.setInfiniteLoop(false);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                shuttlePresenter.pageSelect(position);
            }
        });
        viewPager.setAdapter(shuttlePresenter.getPagerAdapter());
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

        for (int i = busStops.length - 1; i < 4 && i > 0; i++) {
            ((TextView) busStopLayout.findViewWithTag("stop_" + i)).setText("");
            Log.e("ccc", "나머지: " + busStops[i]);
        }
    }

    @Override
    public void setHeartOn(boolean b) {
        if (b)
            btnHeart.setBackground(getResources().getDrawable(R.mipmap.ic_heart_on));
        else
            btnHeart.setBackground(getResources().getDrawable(R.mipmap.ic_heart_off));
    }

    @Override
    public void setPositionByBookmarkId(int position) {
        viewPager.setCurrentItem(position, true);
    }
}
