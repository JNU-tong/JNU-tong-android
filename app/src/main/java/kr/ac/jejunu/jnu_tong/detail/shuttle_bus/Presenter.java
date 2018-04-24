package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

import kr.ac.jejunu.jnu_tong.ActivityPresenter;
import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 4. 24..
 */

public class Presenter implements ActivityPresenter {
    private final ShuttleView shuttleView;
    private ArrayList<PagerProvider> aRouteProviders;
    private ArrayList<PagerProvider> bRouteProviders;
    private PagerAdapterContract.View adapterView;
    private PagerAdapterContract.Model adapterModel;

    Presenter(ShuttleView shuttleView){
        this.shuttleView = shuttleView;
    }

    @Override
    public void onCreate() {
        initData();
    }

    private void initData() {
        aRouteProviders = new ArrayList<>();
        aRouteProviders.add(new PagerProvider("정문(출발)", R.drawable.route_jeongmoon));
        aRouteProviders.add(new PagerProvider("제2도서관\n(해대 방면)", R.drawable.route_second_library));
        aRouteProviders.add(new PagerProvider("해양대학1호관\n(본관 방면)", R.drawable.route_heayang_1ho));
        aRouteProviders.add(new PagerProvider("본관", R.drawable.route_bon_gwan));
        aRouteProviders.add(new PagerProvider("학생회관 남쪽", R.drawable.route_sin_gwan));
        aRouteProviders.add(new PagerProvider("인문대학 서쪽", R.drawable.route_inmoondae_west));
        aRouteProviders.add(new PagerProvider("학생생확관", R.drawable.route_kisooksa));
        aRouteProviders.add(new PagerProvider("인문대학 동쪽", R.drawable.route_inmoondae_east));
        aRouteProviders.add(new PagerProvider("도서관", R.drawable.route_center_library));
        aRouteProviders.add(new PagerProvider("의학전문\n(정문 방면)", R.drawable.route_medical_specialty));
        aRouteProviders.add(new PagerProvider("공대4호관", R.drawable.route_gongdae_4ho));
        aRouteProviders.add(new PagerProvider("해대4호관", R.drawable.route_haeyang_4ho));
        aRouteProviders.add(new PagerProvider("교양강의동", R.drawable.route_kyoyangdong));
        aRouteProviders.add(new PagerProvider("해양대학1호관\n(정문 방면)", R.drawable.route_heayang_1ho));
        aRouteProviders.add(new PagerProvider("제2도서관\n(정문 방면)", R.drawable.route_second_library));
        aRouteProviders.add(new PagerProvider("정문(종점)", R.drawable.route_jeongmoon));

        bRouteProviders = new ArrayList<>();
        bRouteProviders.add(new PagerProvider("정문(출발)", R.drawable.route_jeongmoon));
        bRouteProviders.add(new PagerProvider("제2도서관\n(해대 방면)", R.drawable.route_second_library));
        bRouteProviders.add(new PagerProvider("해양대학1호관\n(본관 방면)", R.drawable.route_heayang_1ho));
        bRouteProviders.add(new PagerProvider("교양강의동", R.drawable.route_kyoyangdong));
        bRouteProviders.add(new PagerProvider("해대4호관", R.drawable.route_haeyang_4ho));
        bRouteProviders.add(new PagerProvider("공대4호관", R.drawable.route_gongdae_4ho));
        bRouteProviders.add(new PagerProvider("의학전문\n(정문 방면)", R.drawable.route_medical_specialty));
        bRouteProviders.add(new PagerProvider("도서관", R.drawable.route_center_library));
        bRouteProviders.add(new PagerProvider("인문대학 동쪽", R.drawable.route_inmoondae_east));
        bRouteProviders.add(new PagerProvider("학생생활관", R.drawable.route_kisooksa));
        bRouteProviders.add(new PagerProvider("인문대학 서쪽", R.drawable.route_inmoondae_west));
        bRouteProviders.add(new PagerProvider("학생회관 남쪽", R.drawable.route_sin_gwan));
        bRouteProviders.add(new PagerProvider("본관", R.drawable.route_bon_gwan));
        bRouteProviders.add(new PagerProvider("해양대학1호관\n(정문 방면)", R.drawable.route_heayang_1ho));
        bRouteProviders.add(new PagerProvider("제2도서관\n(정문 방면)", R.drawable.route_second_library));
        bRouteProviders.add(new PagerProvider("정문(종점)", R.drawable.route_jeongmoon));
    }

    void selectARoute(){
        adapterView.setProviders(aRouteProviders);
    }

    void selectBRoute(){
        adapterView.setProviders(bRouteProviders);
    }

    void setAdapter(PagerAdapterContract.View adapter){
        this.adapterView = adapter;
        this.adapterModel = (PagerAdapterContract.Model) adapter;

        adapter.setProviders(aRouteProviders);
        shuttleView.setAdapter((PagerAdapter)adapter);
    }

    void leftBtnClick(int position) {
        if (position > 0){
//            adapterView.setPosition(position - 1);
            shuttleView.setPagerPosition(position - 1);
        }
    }

    void rightBtnClick(int position) {
        if (position < aRouteProviders.size() -1){
//            adapterView.setPosition(position + 1);
            shuttleView.setPagerPosition(position + 1);
        }
    }

    void pageSelect(int position) {
        String[] busStopNames = adapterModel.getBusStopNames(position);
        shuttleView.routeTextChange(busStopNames[0], busStopNames[1], busStopNames[2]);
    }

    void onDestroy() {
        adapterView = null;
        adapterModel = null;
    }
}
