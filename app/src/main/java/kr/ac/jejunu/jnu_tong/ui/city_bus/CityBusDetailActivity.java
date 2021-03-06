package kr.ac.jejunu.jnu_tong.ui.city_bus;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import dagger.android.support.DaggerAppCompatActivity;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.ui.city_bus.stop.BusStopRecyclerFragment;
import kr.ac.jejunu.jnu_tong.ui.city_bus.time.BusTimeRecyclerFragment;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class CityBusDetailActivity extends DaggerAppCompatActivity {
    private BusStopRecyclerFragment busStopListFragment;
    private BusTimeRecyclerFragment busTimeListFragment;
    private ViewPager viewPager;

    private TextView leftTab;
    private TextView rightTab;
    private String busID;
    private String busDescription;
    private String busNo;
    private String busType;
    private int leftTabId;
    private int rightTabId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        busType = getIntent().getStringExtra("busType");
        busID = getIntent().getStringExtra("busID");
        busNo = getIntent().getStringExtra("busNo");
        busDescription = getIntent().getStringExtra("busDescription");

        initView();
        initViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        actionBar.setCustomView(R.layout.actionbar_custom);
        actionBar.getCustomView().findViewById(R.id.back).setOnClickListener(view -> finish());
        actionBar.getCustomView().findViewById(R.id.refresh).setOnClickListener(view -> {

            Log.e("refresh", "refresh"  );
            busStopListFragment.refresh();
            busTimeListFragment.refresh();
        });
        actionBar.setElevation(0);

        return true;
    }

    private void initView(){
        View topBar = findViewById(R.id.top_bar);
        LinearLayout busBack = findViewById(R.id.bus_back);
        ImageView busImage = findViewById(R.id.image_bus);
        TextView busNoView = findViewById(R.id.text_bus_no);
        TextView busDescriptionView = findViewById(R.id.text_bus_description);
        leftTab = findViewById(R.id.left_pager);
        rightTab = findViewById(R.id.right_pager);

        busNoView.setText(busNo);
        busDescriptionView.setText(busDescription);

        switch (busType){
            case "sky" :
                busBack.setBackground(getResources().getDrawable(R.drawable.circle_sky));
                busImage.setImageResource(R.drawable.bus_blue);
                leftTabId = R.drawable.trapeze_shape_right_sky;
                rightTabId = R.drawable.trapeze_shape_left_sky;
                break;
            case "green" :
                topBar.setBackgroundColor(getResources().getColor(R.color.emerald));
                busNoView.setTextColor(getResources().getColor(R.color.emerald));
                busBack.setBackground(getResources().getDrawable(R.drawable.circle_green));
                busImage.setImageResource(R.drawable.bus_green);
                leftTabId = R.drawable.trapeze_shape_right_green;
                rightTabId = R.drawable.trapeze_shape_left_green;
                break;
            case "yellow" :
                topBar.setBackgroundColor(getResources().getColor(R.color.light_yellow));
                busNoView.setTextColor(getResources().getColor(R.color.light_yellow));
                busBack.setBackground(getResources().getDrawable(R.drawable.circle_yellow));
                busImage.setImageResource(R.drawable.bus_yellow);
                leftTabId = R.drawable.trapeze_shape_right_yellow;
                rightTabId = R.drawable.trapeze_shape_left_yellow;
                break;
        }
        leftTab.setBackground(getResources().getDrawable(leftTabId));

        busImage.setBackground(new ShapeDrawable(new OvalShape()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            busImage.setClipToOutline(true);
        }
    }

    private void initViewPager(){
        busStopListFragment = BusStopRecyclerFragment.newInstance(busID, busType);
        busTimeListFragment = BusTimeRecyclerFragment.newInstance(busID, busType);

        viewPager = findViewById(R.id.time_line_pager);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    leftTab.setBackground(getResources().getDrawable(leftTabId));
                    rightTab.setBackground(null);
                }
                else {
                    rightTab.setBackground(getResources().getDrawable(rightTabId));
                    leftTab.setBackground(null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        leftTab.setOnClickListener(view -> viewPager.setCurrentItem(0));
        rightTab.setOnClickListener(view -> viewPager.setCurrentItem(1));
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {
        Fragment curFragment = busTimeListFragment;

        MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    curFragment = busTimeListFragment;
                    break;
                case 1:
                    curFragment = busStopListFragment;
            }
            return curFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}