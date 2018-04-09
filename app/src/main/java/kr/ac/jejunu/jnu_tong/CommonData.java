package kr.ac.jejunu.jnu_tong;

import android.app.Application;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class CommonData extends Application {
    private final static String baseURL = "http://218.50.253.120:8080/";

    public static String getBusDepatureListURL(){
        return baseURL + "getDepartureSoonBusList";
    }

    public static String getBusLineListURL(String busID){
        return baseURL + "getBusStationListByLineId/" + busID;
    }
}
