package kr.ac.jejunu.jnu_tong.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.brandongogetap.stickyheaders.StickyLayoutManager;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.StickyRecyclerAdapter;

public class MainActivity extends AppCompatActivity{
    private LinearLayout busComeInLayout;
    private View topImage;
    private boolean expanded = false;
    private int topHeight;
    private RelativeLayout top;
    private LinearLayout cityBusLayout;
    private int cityBusHeight;
    private int cityBusBottomMargin;
    private LinearLayout shuttleBusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTopImage();
        initCityBusLayout();
        initShuttleBusLayout();
        initCityBusRecycler();
    }

    private void initTopImage(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_expand_anim);
        busComeInLayout = findViewById(R.id.bus_come_in);
        topImage = findViewById(R.id.main_back);
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
                    layoutParams.bottomMargin += cityBusHeight/2;
                    cityBusLayout.setLayoutParams(layoutParams);

                    TransitionManager.beginDelayedTransition(shuttleBusLayout);
                    LinearLayout.LayoutParams shuttleBusParams = (LinearLayout.LayoutParams) shuttleBusLayout.getLayoutParams();
                    shuttleBusParams.topMargin -= cityBusHeight/2;
                });
            } else {
                expanded = false;
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
                });
            }
        });

        busComeInLayout.addView(bus);
    }

    private void initShuttleBusLayout(){
        shuttleBusLayout = findViewById(R.id.shuttle_bus);
    }

    private void initCityBusRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recycler_soon_bus);

        StickyRecyclerAdapter adapter = new StickyRecyclerAdapter();
        StickyLayoutManager manager = new StickyLayoutManager(this, adapter);
    }
}
