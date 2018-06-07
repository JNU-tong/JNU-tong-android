package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.detail.shuttle_bus.route.ARoute;
import kr.ac.jejunu.jnu_tong.detail.shuttle_bus.route.BRoute;
import kr.ac.jejunu.jnu_tong.detail.shuttle_bus.route.Route;
import kr.ac.jejunu.jnu_tong.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public class ShuttleModel implements ShuttleContract.ShuttleModel {
    private List<String> busStops;
    private Route[] route;

    ShuttleModel() {
        busStops = new ArrayList<>();
    }

    @Override
    public void changeProvider(List<ShuttleVO> result) {
        ArrayList<ShuttleVO> vos = new ArrayList<>(result);

        int temp = 0;
        for (int i = 1; i < vos.size(); i++) {
            if (vos.get(i).getFirstTime() != null){
                if (vos.get(temp).getFirstTime() > vos.get(i).getFirstTime()){
                    temp = i;
                }
            }
        }

        while (temp < vos.size()) {
            if (busStops.size() < 5){
                busStops.add( route[temp].getTitle());
            }
            temp++;
        }
    }

    @Override
    public String[] getShuttleBusStops() {
        String[] stops = new String[busStops.size()];
        busStops.toArray(stops);

        for (int i = 0 ; i < stops.length ; i++){
            Log.e("dd", "getShuttleBusStops: " + stops[i] );
        }

        return stops;
    }

    @Override
    public void selectARoute() {
        route = ARoute.values();
    }

    @Override
    public void selectBRoute() {
        route = BRoute.values();
    }
}

