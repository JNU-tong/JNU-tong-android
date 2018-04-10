package kr.ac.jejunu.jnu_tong.main;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import kr.ac.jejunu.jnu_tong.R;

public class MainActivity extends AppCompatActivity {
    private LinearLayout busComeInLayout;
    private View topImage;
    private boolean expanded = false;
    private int topHeight;
    private RelativeLayout top;
    private LinearLayout cityBusLayout;
    private int cityBusHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_expand_anim);
        busComeInLayout = findViewById(R.id.bus_come_in);
        topImage = findViewById(R.id.main_back);
        topImage.startAnimation(animation);


        top = findViewById(R.id.top);
        ViewGroup.LayoutParams params = top.getLayoutParams();
        topHeight = params.height;

        cityBusLayout = findViewById(R.id.city_bus);
        ViewGroup.LayoutParams layoutParams = cityBusLayout.getLayoutParams();
        cityBusHeight = layoutParams.height;

        testBusComeIn();
    }

    private void testBusComeIn() {
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
                    ViewGroup.LayoutParams layoutParams = cityBusLayout.getLayoutParams();
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    cityBusLayout.setLayoutParams(layoutParams);
                });
            } else {
                expanded = false;
                runOnUiThread(() -> {
                    TransitionManager.beginDelayedTransition(top);
                    ViewGroup.LayoutParams params = top.getLayoutParams();
                    params.height = topHeight;
                    top.setLayoutParams(params);

                    TransitionManager.beginDelayedTransition(cityBusLayout);
                    ViewGroup.LayoutParams layoutParams = cityBusLayout.getLayoutParams();
                    layoutParams.height = cityBusHeight;
                    cityBusLayout.setLayoutParams(layoutParams);
                });
            }
        });

        busComeInLayout.addView(bus);
    }

}
