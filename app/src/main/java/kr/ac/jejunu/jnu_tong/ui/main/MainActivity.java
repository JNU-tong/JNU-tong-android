package kr.ac.jejunu.jnu_tong.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.ui.bus.shuttle.ShuttleBusDetailActivity;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.UnfoldedMainActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {
    private LinearLayout cityBusLayout;
    private LinearLayout shuttleBusLayout;

    @Inject
    MainContract.Presenter presenter;

    private ImageButton btnRefresh;
    private TextView txtFirstNo;
    private TextView txtSecondNo;
    private TextView txtDepartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTopImage();
        initRefreshBtn();
        initCityBusLayout();
        initShuttleBusLayout();

        presenter = new MainPresenter(this);
        presenter.setShuttleBookmarkId(((CommonApp) getApplication()).getShuttleBookmarkId());
        presenter.onCreate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        presenter.setShuttleBookmarkId(((CommonApp) getApplication()).getShuttleBookmarkId());
        presenter.refreshClick();
    }

    private void initTopImage() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_expand_anim);
        android.view.View topImage = findViewById(R.id.main_back);
        topImage.startAnimation(animation);
    }

    private void initRefreshBtn() {
        btnRefresh = findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(view -> presenter.refreshClick());
    }

    private void initCityBusLayout() {
        cityBusLayout = findViewById(R.id.city_bus);
        cityBusLayout.setOnClickListener(v -> presenter.clickCityBus());

        txtFirstNo = findViewById(R.id.txt_first_no);
        txtSecondNo = findViewById(R.id.txt_second_no);
        txtDepartTime = findViewById(R.id.txt_depart_time);
    }

    private void initShuttleBusLayout() {
        shuttleBusLayout = findViewById(R.id.shuttle_bus);
        shuttleBusLayout.setOnClickListener(view -> startActivity(new Intent(this, ShuttleBusDetailActivity.class)));
    }

    /*@Override
    public void onClickCityBus() {
        if (!expanded) {
            expanded = true;

            TransitionManager.beginDelayedTransition(cityBusLayout);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cityBusLayout.getLayoutParams();
            layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
            layoutParams.bottomMargin += cityBusHeight / 2;
            cityBusLayout.setLayoutParams(layoutParams);

            TransitionManager.beginDelayedTransition(shuttleBusLayout);
            LinearLayout.LayoutParams shuttleBusParams = (LinearLayout.LayoutParams) shuttleBusLayout.getLayoutParams();
            shuttleBusParams.topMargin -= cityBusHeight / 2;
            shuttleBusLayout.setLayoutParams(shuttleBusParams);

            TransitionManager.beginDelayedTransition(top);
            ViewGroup.LayoutParams params = top.getLayoutParams();
            params.height = 0;
            top.setLayoutParams(params);

            btnRefresh.setBackground(getResources().getDrawable(R.drawable.ic_refresh_navy));
        } else {
            expanded = false;
//            recyclerView.setVisibility(GONE);

            TransitionManager.beginDelayedTransition(top);
            ViewGroup.LayoutParams params = top.getLayoutParams();
            params.height = topHeight;
            top.setLayoutParams(params);

            TransitionManager.beginDelayedTransition(shuttleBusLayout);
            LinearLayout.LayoutParams shuttleBusParams = (LinearLayout.LayoutParams) shuttleBusLayout.getLayoutParams();
            shuttleBusParams.topMargin = 0;
            shuttleBusLayout.setLayoutParams(shuttleBusParams);

            TransitionManager.beginDelayedTransition(cityBusLayout);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cityBusLayout.getLayoutParams();
            layoutParams.height = cityBusHeight;
            layoutParams.bottomMargin = cityBusBottomMargin;
            cityBusLayout.setLayoutParams(layoutParams);

            btnRefresh.setBackground(getResources().getDrawable(R.drawable.ic_refresh_white));

            // 야매로 했어요..   recyclerView가 없어지는 모션때문에 cityBusLayout이 늦게 반응해서 클릭할때 애초에 GONE시켰다가
            // 끝나고 0.5초후에 다시 VISIBLE했습니다. 클릭했을때 VISIBLE을 실행하면 recyclerView가 만들어지는 부분에서 또
            // 딜레이가 생겨서 미리 VISIBLE했습니다.
//            Handler handler = new Handler();
//            handler.postDelayed(() -> recyclerView.setVisibility(VISIBLE), 500);
        }
    }*/

    @Override
    public void onClickCityBus() {
        Intent intent = new Intent(this, UnfoldedMainActivity.class);
        Pair<View, String> sharedCityLayout = Pair.create(cityBusLayout, "city_bus");
        Pair<View, String> sharedShuttleLayout = Pair.create(shuttleBusLayout, "shuttle_bus");

        @SuppressWarnings("unchecked")
        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedCityLayout, sharedShuttleLayout);

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