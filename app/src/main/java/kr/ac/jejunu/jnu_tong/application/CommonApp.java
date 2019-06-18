package kr.ac.jejunu.jnu_tong.application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import kr.ac.jejunu.jnu_tong.data.IDataManager;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class CommonApp extends DaggerApplication {
    public final static String baseURL = "http://106.10.46.151:8080/";
    @Inject
    IDataManager dataManager;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().context(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dataManager.doGetDepartureBusList();
        dataManager.doGetJNUEvent();
        dataManager.doGetShuttleTime(dataManager.getShuttleBookmarkId());
    }
}
