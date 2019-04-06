package kr.ac.jejunu.jnu_tong.application;

import android.content.SharedPreferences;

import java.util.HashSet;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import kr.ac.jejunu.jnu_tong.IDataManager;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class CommonApp extends DaggerApplication {
    @Inject
    IDataManager dataManager;

    private final static String baseURL = "http://106.10.46.151:8080/";
    private SharedPreferences pref;
    private HashSet<String> oftenBusSet;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().context(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        pref = getSharedPreferences("bus", MODE_PRIVATE);
        oftenBusSet = new HashSet<>( pref.getStringSet("oftenBus", new HashSet<>()));
    }

    public static String getBusDepartureListURL(){
        return baseURL + "getDepartureSoonBusList";
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

    public static String getJNUEventURL() {
        return baseURL + "jnu/eventDay/first";
    }


    public void addOftenBus(String busID){
        oftenBusSet.add(busID);

        pref.edit().putStringSet("oftenBus", oftenBusSet).apply();
    }

    public void deleteOftenBus(String busID){
        oftenBusSet.remove(busID);

        pref.edit().remove("oftenBus").apply();
        pref.edit().putStringSet("oftenBus", oftenBusSet).apply();
    }

    public boolean hasOftenBus(String busID){
        return oftenBusSet.contains(busID);
    }

    public void setShuttleBookmarkId(int shuttleId) {
        pref.edit().putInt("bookmarkShuttleId", shuttleId).apply();
    }

    public int getShuttleBookmarkId(){
        return pref.getInt("bookmarkShuttleId", 1);
    }

    public void setShuttleBookmarkTitle(String shuttleTitle) {
        pref.edit().putString("bookmarkShuttleTitle", shuttleTitle).apply();
    }

    public String getShuttleBookmarkTitle(){
        return pref.getString("bookmarkShuttleTitle", "정문");
    }
}
