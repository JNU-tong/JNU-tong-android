package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 4. 22..
 */

public class ShuttleBusDetailActivity extends AppCompatActivity implements ShuttleView {
    private List<PagerProvider> aRouteProviders;
    private List<PagerProvider> bRouteProviders;
    private TextView leftRouteText;
    private TextView rightRouteText;
    private TextView aRouteSelectText;
    private TextView bRouteSelectText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shuttle);

        initView();
        initPagerProvider();
        initViewPager();
    }

    private void initView() {
        aRouteSelectText = findViewById(R.id.text_a_route);
        aRouteSelectText.setOnClickListener(view -> {
            aRouteSelectText.setTextColor(getResources().getColor(R.color.navy));
            bRouteSelectText.setTextColor(getResources().getColor(R.color.very_light_gray));
            findViewById(R.id.a_bar).setVisibility(View.VISIBLE);
            findViewById(R.id.b_bar).setVisibility(View.GONE);
        });
        bRouteSelectText = findViewById(R.id.text_b_route);
        bRouteSelectText.setOnClickListener(view -> {
            aRouteSelectText.setTextColor(getResources().getColor(R.color.very_light_gray));
            bRouteSelectText.setTextColor(getResources().getColor(R.color.navy));
            findViewById(R.id.a_bar).setVisibility(View.GONE);
            findViewById(R.id.b_bar).setVisibility(View.VISIBLE);
        });
        leftRouteText = findViewById(R.id.left_pager);
        rightRouteText = findViewById(R.id.right_pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View customView = getLayoutInflater().inflate(R.layout.actionbar_custom, new LinearLayout(this), false);
        ((TextView)customView.findViewById(R.id.text_title)).setText("셔틀버스");
        actionBar.setCustomView(customView);
        actionBar.getCustomView().findViewById(R.id.back).setOnClickListener(view -> finish());
        actionBar.setElevation(0);

        return true;
    }

    private void initViewPager() {
        UltraViewPager viewPager = findViewById(R.id.ultra_view_pager);
        viewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        viewPager.setMultiScreen(0.85f);

        PagerAdapter ultraPagerAdapter = new UltraPagerAdapter(this, aRouteProviders);
        viewPager.setAdapter(ultraPagerAdapter);

        viewPager.setInfiniteLoop(true);
    }

    private void initPagerProvider(){
        aRouteProviders = new ArrayList<>();

        aRouteProviders.add(new PagerProvider("route_jeongmoon(출발)",R.drawable.route_jeongmoon));
        aRouteProviders.add(new PagerProvider("route_second_library\n(해대 방면)",R.drawable.route_second_library));
        aRouteProviders.add(new PagerProvider("route_heayang_1ho\n(본관 방면)",R.drawable.route_heayang_1ho));
        aRouteProviders.add(new PagerProvider("본관",R.drawable.route_bon_gwan));
        aRouteProviders.add(new PagerProvider("route_sin_gwan 남쪽",R.drawable.route_sin_gwan));
        aRouteProviders.add(new PagerProvider("인문대학 서쪽",R.drawable.route_inmoondae_west));
        aRouteProviders.add(new PagerProvider("route_kisooksa",R.drawable.route_kisooksa));
        aRouteProviders.add(new PagerProvider("인문대학 동쪽",R.drawable.route_inmoondae_east));
        aRouteProviders.add(new PagerProvider("도서관",R.drawable.route_center_library));
        aRouteProviders.add(new PagerProvider("의학전문\n(route_jeongmoon 방면",R.drawable.route_medical_specialty));
        aRouteProviders.add(new PagerProvider("route_gongdae_4ho",R.drawable.route_gongdae_4ho));
        aRouteProviders.add(new PagerProvider("해대4호관",R.drawable.route_haeyang_4ho));
        aRouteProviders.add(new PagerProvider("route_kyoyangdong",R.drawable.route_kyoyangdong));
        aRouteProviders.add(new PagerProvider("route_heayang_1ho\n(route_jeongmoon 방면)",R.drawable.route_heayang_1ho));
        aRouteProviders.add(new PagerProvider("route_second_library\n(route_jeongmoon 방면)",R.drawable.route_second_library));
        aRouteProviders.add(new PagerProvider("route_jeongmoon(종점)",R.drawable.route_jeongmoon));

        bRouteProviders = new ArrayList<>();

        bRouteProviders.add(new PagerProvider("route_jeongmoon(출발)",R.drawable.route_jeongmoon));
        bRouteProviders.add(new PagerProvider("route_second_library\n(해대 방면)",R.drawable.route_second_library));
        bRouteProviders.add(new PagerProvider("route_heayang_1ho\n(본관 방면)",R.drawable.route_heayang_1ho));
        bRouteProviders.add(new PagerProvider("route_kyoyangdong",R.drawable.route_kyoyangdong));
        bRouteProviders.add(new PagerProvider("해대4호관",R.drawable.route_haeyang_4ho));
        bRouteProviders.add(new PagerProvider("route_gongdae_4ho",R.drawable.route_gongdae_4ho));
        bRouteProviders.add(new PagerProvider("의학전문\n(route_jeongmoon 방면",R.drawable.route_medical_specialty));
        bRouteProviders.add(new PagerProvider("도서관",R.drawable.route_center_library));
        bRouteProviders.add(new PagerProvider("인문대학 동쪽",R.drawable.route_inmoondae_east));
        bRouteProviders.add(new PagerProvider("route_kisooksa",R.drawable.route_kisooksa));
        bRouteProviders.add(new PagerProvider("인문대학 서쪽",R.drawable.route_inmoondae_west));
        bRouteProviders.add(new PagerProvider("route_sin_gwan 남쪽",R.drawable.route_sin_gwan));
        bRouteProviders.add(new PagerProvider("본관",R.drawable.route_bon_gwan));
        bRouteProviders.add(new PagerProvider("route_heayang_1ho\n(route_jeongmoon 방면)",R.drawable.route_heayang_1ho));
        bRouteProviders.add(new PagerProvider("route_second_library\n(route_jeongmoon 방면)",R.drawable.route_second_library));
        bRouteProviders.add(new PagerProvider("route_jeongmoon(종점)",R.drawable.route_jeongmoon));
    }

    @Override
    public void routeTextChange(String leftText, String rightText) {
        leftRouteText.setText(leftText);
        rightRouteText.setText(rightText);
    }
}
