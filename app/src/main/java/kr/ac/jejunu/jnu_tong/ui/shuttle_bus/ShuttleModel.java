package kr.ac.jejunu.jnu_tong.ui.shuttle_bus;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.ui.shuttle_bus.route.ARoute;
import kr.ac.jejunu.jnu_tong.ui.shuttle_bus.route.BRoute;
import kr.ac.jejunu.jnu_tong.ui.shuttle_bus.route.Route;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 6. 8..
 */

public class ShuttleModel implements ShuttleContract.Model {
    private List<String> busStops;
    private Route[] route;
    private int shuttleBookmarkId;

    ShuttleModel() {
        busStops = new ArrayList<>();
    }

    @Override
    public void changeProvider(List<ShuttleVO> result) {
        ArrayList<ShuttleVO> vos = new ArrayList<>(result);

        int temp = 0;
        for (int i = 1; i < vos.size(); i++) {
            Integer firstTime = vos.get(i).getRemainTime().getFirstTime();
            if (firstTime != null){
                if (vos.get(temp).getRemainTime().getFirstTime() > firstTime){
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

    @Override
    public void setBookmarkId(int stopId) {
        shuttleBookmarkId = stopId;
    }

    @Override
    public int getBookmarkId() {
        return shuttleBookmarkId;
    }

    @Override
    public Integer getPositionByBookmarkId() {
        for (int i = 0; i < route.length; i++) {
            if (route[i].getId() == shuttleBookmarkId){
                return i;
            }
        }
        return null;
    }
}

