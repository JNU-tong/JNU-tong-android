package kr.ac.jejunu.jnu_tong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import kr.ac.jejunu.jnu_tong.ui.main.MainActivity;

/**
 * Created by seung-yeol on 2018. 4. 24..
 */

public class SplashActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);

        new Handler().postDelayed(() -> {
            startActivity(intent);
            finish();
        }, 300);
    }
}
