package kr.ac.jejunu.jnu_tong;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.main.BusTimeVO;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class CommonData extends Application {
    private final static String baseURL = "http://218.50.253.120:8080/";

    public static String getBusDepatureListURL(){
        return baseURL + "getDepartureSoonBusList";
    }

    public static String getBusTimeListURL(String busID){
        return baseURL + "getBusScheduleListByLineId/" + busID;
    }

    public static String getBusStopListURL(String busID){
        return baseURL + "getBusStationListByLineId/" + busID;
    }

    public ArrayList<BusTimeVO> getRemainBusTime(List<BusTimeVO> vos){
        ArrayList<BusTimeVO> busTimeVOArrayList = new ArrayList<>();

        if (vos != null){
            for (BusTimeVO vo :
                    vos) {
                if (vo.getRemainTime() >= 0) {
                    busTimeVOArrayList.add(vo);
                }
            }
        }

        return busTimeVOArrayList;
    }
}
