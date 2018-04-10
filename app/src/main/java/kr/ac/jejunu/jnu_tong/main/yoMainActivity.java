package kr.ac.jejunu.jnu_tong.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import kr.ac.jejunu.jnu_tong.R;

public class yoMainActivity extends AppCompatActivity {
    private LinearLayout busComeInLayout;
    private View topImage;
    private boolean expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_expand_anim);
        busComeInLayout = findViewById(R.id.bus_come_in);
        topImage = findViewById(R.id.main_back);
        topImage.startAnimation(animation);

        testBusComeIn();
    }

    private void testBusComeIn(){
        View bus = View.inflate(this, R.layout.vew_bus_num, null);
        bus.setOnClickListener(v->{
            Log.e(this.toString(), "클릭실행" );
            if (!expanded){
                Log.e(this.toString(), "닫혔다" );
                expanded = true;
                runOnUiThread(() -> {
                    RelativeLayout top = findViewById(R.id.top);

                    TransitionManager.beginDelayedTransition(top);
                    ViewGroup.LayoutParams params = top.getLayoutParams();
                    params.height = 0;
                    top.setLayoutParams(params);

//                LinearLayout linearLayout = findViewById(R.id.city_bus);
//
//                TransitionManager.beginDelayedTransition(linearLayout);
//                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
//                layoutParams.




//                linearLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.citybus_expand_anim));
//                linearLayout.setY(10);

                });
            }
            else {
                Log.e(this.toString(), "열렸다" );
                expanded = false;
                runOnUiThread(() -> {
                    RelativeLayout top = findViewById(R.id.top);

                    TransitionManager.beginDelayedTransition(top);
                    ViewGroup.LayoutParams params = top.getLayoutParams();
                    params.height = 296;
                    top.setLayoutParams(params);


//                findViewById(R.id.today).setVisibility(View.GONE);
//                findViewById(R.id.d_day).setVisibility(View.GONE);



//                LinearLayout linearLayout = findViewById(R.id.city_bus);
//
//                TransitionManager.beginDelayedTransition(linearLayout);
//                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
//                layoutParams.





//                linearLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.citybus_expand_anim));
//                linearLayout.setY(10);

                });
            }

        });

        busComeInLayout.addView(bus);
    }

}
