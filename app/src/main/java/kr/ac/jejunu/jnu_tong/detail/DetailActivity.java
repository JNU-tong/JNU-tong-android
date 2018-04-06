package kr.ac.jejunu.jnu_tong.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        actionBar.setCustomView(R.layout.actionbar_detail);
        actionBar.setElevation(0);

        return true;
    }

    private void initFragment(){
        BusListFragment busListFragment = BusListFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bus_list, busListFragment);
        fragmentTransaction.commit();
    }
}
