package kr.ac.jejunu.jnu_tong.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceHelper implements IPreferencesHelper {
    private SharedPreferences pref;
    private HashSet<String> oftenBusSet;

    PreferenceHelper(Context context){
        pref = context.getSharedPreferences("bus", MODE_PRIVATE);
        oftenBusSet = new HashSet<>( pref.getStringSet("oftenBus", new HashSet<>()));
    }


    @Override
    public void addOftenBus(String busID){
        oftenBusSet.add(busID);

        pref.edit().putStringSet("oftenBus", oftenBusSet).apply();
    }

    @Override
    public void deleteOftenBus(String busID){
        oftenBusSet.remove(busID);

        pref.edit().remove("oftenBus").apply();
        pref.edit().putStringSet("oftenBus", oftenBusSet).apply();
    }

    @Override
    public boolean hasOftenBus(String busID){
        return oftenBusSet.contains(busID);
    }

    @Override
    public void setShuttleBookmarkId(int shuttleId) {
        pref.edit().putInt("bookmarkShuttleId", shuttleId).apply();
    }

    @Override
    public int getShuttleBookmarkId(){
        return pref.getInt("bookmarkShuttleId", 1);
    }

    @Override
    public void setShuttleBookmarkTitle(String shuttleTitle) {
        pref.edit().putString("bookmarkShuttleTitle", shuttleTitle).apply();
    }

    @Override
    public String getShuttleBookmarkTitle(){
        return pref.getString("bookmarkShuttleTitle", "정문");
    }
}
