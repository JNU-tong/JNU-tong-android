package kr.ac.jejunu.jnu_tong.detail;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class DetailActivity extends AppCompatActivity {

    private BusStopListFragment busStopListFragment;
    private BusTimeListFragment busTimeListFragment;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

        actionBar.setCustomView(R.layout.actionbar_detail);
        actionBar.getCustomView().findViewById(R.id.back).setOnClickListener(view -> finish());
        actionBar.setElevation(0);

        return true;
    }

    private void initView(){

        ImageView imageView = findViewById(R.id.image_bus);
        imageView.setBackground(new ShapeDrawable(new OvalShape()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setClipToOutline(true);
        }
    }

    private void initViewPager(){
        TextView left = findViewById(R.id.left_pager);
        TextView right = findViewById(R.id.right_pager);

        busStopListFragment = BusStopListFragment.newInstance();
        busTimeListFragment = BusTimeListFragment.newInstance();

        viewPager = findViewById(R.id.time_line_pager);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    left.setBackground(getResources().getDrawable(R.drawable.trapeze_shape_right_sky));
                    right.setBackground(null);
                }
                else {
                    right.setBackground(getResources().getDrawable(R.drawable.trapeze_shape_left_sky));
                    left.setBackground(null);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        left.setOnClickListener(view -> viewPager.setCurrentItem(0));
        right.setOnClickListener(view -> viewPager.setCurrentItem(1));
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter{
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
