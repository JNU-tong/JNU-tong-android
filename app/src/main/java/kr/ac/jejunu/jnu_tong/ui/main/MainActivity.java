package kr.ac.jejunu.jnu_tong.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import dagger.android.support.DaggerAppCompatActivity;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.ui.bus.shuttle.ShuttleBusDetailActivity;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.UnfoldedMainActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {
    @Inject
    MainContract.Presenter presenter;

    private LinearLayout cityBusLayout;
    private LinearLayout shuttleBusLayout;
    private TextView txtFirstNo;
    private TextView txtSecondNo;
    private TextView txtDepartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initBusLayout();

//        presenter.setShuttleBookmarkId(((CommonApp) getApplication()).getShuttleBookmarkId());
        presenter.onCreate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

//        presenter.setShuttleBookmarkId(((CommonApp) getApplication()).getShuttleBookmarkId());
        presenter.refreshClick();
    }

    private void initView() {
        ImageButton btnRefresh = findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(view -> presenter.refreshClick());

        View topImage = findViewById(R.id.main_back);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_expand_anim);
        topImage.startAnimation(animation);
    }

    private void initBusLayout() {
        txtFirstNo = findViewById(R.id.txt_first_no);
        txtSecondNo = findViewById(R.id.txt_second_no);
        txtDepartTime = findViewById(R.id.txt_depart_time);

        cityBusLayout = findViewById(R.id.city_bus);
        cityBusLayout.setOnClickListener(v -> presenter.clickCityBus());

        shuttleBusLayout = findViewById(R.id.shuttle_bus);
        shuttleBusLayout.setOnClickListener(view -> startActivity(new Intent(this, ShuttleBusDetailActivity.class)));
    }

    @Override
    public void onClickCityBus() {
        Intent intent = new Intent(this, UnfoldedMainActivity.class);
        Pair<View, String> sharedCityLayout = Pair.create(cityBusLayout, "city_bus");
        Pair<View, String> sharedShuttleLayout = Pair.create(shuttleBusLayout, "shuttle_bus");
        Pair<View, String> sharedTop = Pair.create((ViewGroup) findViewById(R.id.top), "top");

        @SuppressWarnings("unchecked")
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedCityLayout, sharedShuttleLayout, sharedTop);

        startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    public void setDepartureBusData(Integer[] imgIds, String[] busNos, String time) {
        if (imgIds[0] == null) txtFirstNo.setVisibility(GONE);
        else {
            txtFirstNo.setVisibility(VISIBLE);
            txtFirstNo.setBackground(getResources().getDrawable(imgIds[0]));
            txtFirstNo.setText(busNos[0]);
        }

        if (busNos[1] == null) txtSecondNo.setVisibility(GONE);
        else {
            txtSecondNo.setVisibility(VISIBLE);
            txtSecondNo.setBackground(getResources().getDrawable(imgIds[1]));
            txtSecondNo.setText(busNos[1]);
        }

        if (time == null) txtDepartTime.setText("버스없음");
        else {
            txtDepartTime.setText(time);
        }
    }

    @Override
    public void setJNUEvent(String today, String event) {
        ((TextView) findViewById(R.id.today)).setText(today);
        ((TextView) findViewById(R.id.d_day)).setText(event);
    }

    @Override
    public void setShuttleBusData(String title, Integer aFirst, Integer bFirst) {
        ((TextView) findViewById(R.id.txt_shuttle_bookmark)).setText(title);

        if (aFirst != null) ((TextView) findViewById(R.id.txt_a_route)).setText(aFirst + "분전");
        else ((TextView) findViewById(R.id.txt_a_route)).setText("없음");

        if (aFirst != null) ((TextView) findViewById(R.id.txt_b_route)).setText(bFirst + "분전");
        else ((TextView) findViewById(R.id.txt_b_route)).setText("없음");
    }
}