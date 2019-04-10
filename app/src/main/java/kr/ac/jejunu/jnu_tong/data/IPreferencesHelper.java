package kr.ac.jejunu.jnu_tong.data;

public interface IPreferencesHelper {
    void addOftenBus(String busID);

    void deleteOftenBus(String busID);

    boolean hasOftenBus(String busID);

    void setShuttleBookmarkId(int shuttleId);

    int getShuttleBookmarkId();

    void setShuttleBookmarkTitle(String shuttleTitle);

    String getShuttleBookmarkTitle();
}
