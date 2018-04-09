package kr.ac.jejunu.jnu_tong.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.detail.DetailActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout busComeInLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_anim);
        busComeInLayout = findViewById(R.id.bus_come_in);
        findViewById(R.id.main_back).startAnimation(animation);

        testBusComeIn();
    }

    private void testBusComeIn(){
        View bus = View.inflate(this, R.layout.item_bus, null);
        bus.setOnClickListener(v->{
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        });
        busComeInLayout.addView(bus);
    }
}
