package kr.ac.jejunu.jnu_tong.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import kr.ac.jejunu.jnu_tong.R;

public class MainActivity extends AppCompatActivity {
    private LinearLayout busComeInLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        busComeInLayout = findViewById(R.id.bus_come_in);

        testBusComeIn();
    }

    private void testBusComeIn(){
        View bus = View.inflate(this, R.layout.item_bus, null);
        bus.setOnClickListener(v->{
            
        });
        busComeInLayout.addView(bus);
    }
}
