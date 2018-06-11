package kr.ac.jejunu.jnu_tong;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class CommonData extends Application {
    private final static String baseURL = "http://218.50.253.120:8080/";
    private SharedPreferences pref;
    private HashSet<String > oftenBusSet;

    @Override
    public void onCreate() {
        super.onCreate();

        pref = getSharedPreferences("bus", MODE_PRIVATE);
        oftenBusSet = (HashSet<String>) pref.getStringSet("oftenBus", new HashSet<>());
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

    public void addOftenBus(String busID){
        oftenBusSet.add(busID);

        pref.edit().putStringSet("oftenBus", oftenBusSet).commit();
    }

    public void deleteOftenBus(String busID){
        oftenBusSet.remove(busID);

        pref.edit().putStringSet("oftenBus", oftenBusSet).commit();
    }

    public boolean hasOftenBus(String busID){
        return oftenBusSet.contains(busID);
    }

    public void setShuttleBookmark(int shuttleId) {
        pref.edit().putInt("bookmarkShuttleId", shuttleId).commit();
    }

    public int getShuttleBookmarkId(){
        int shuttleBookmarkId = 0;
        pref.getInt("bookmarkShuttleId", shuttleBookmarkId);

        return shuttleBookmarkId;
    }

    public static String getJNUEventURL() {
        return baseURL + "jnu/eventDay/first";
    }
}
