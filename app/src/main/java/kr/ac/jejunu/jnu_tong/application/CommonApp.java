package kr.ac.jejunu.jnu_tong.application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import kr.ac.jejunu.jnu_tong.data.IDataManager;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class CommonApp extends DaggerApplication {
    @Inject
    IDataManager dataManager;

    public final static String baseURL = "http://106.10.46.151:8080/";

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().context(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dataManager.doGetDepartureBusList();
        dataManager.doGetJNUEvent();
    }

    public static String getBusTimeListURL(String busID){
        return baseURL + "getBusScheduleListByLineId/" + busID;
    }

    public static String getBusStopListURL(String busID){
        return baseURL + "getBusStationListByLineId/" + busID;
    }

    public static String getShuttleListURL(String course){
        return baseURL + "getJnuBusArrivalInfoListByCourse?course=" + course;
    }

    public static String getShuttlePointUrl(int stationId) {
        return baseURL + "getJnuBusArrivalInfoByStationId?stationId=" + stationId;
    }
}
