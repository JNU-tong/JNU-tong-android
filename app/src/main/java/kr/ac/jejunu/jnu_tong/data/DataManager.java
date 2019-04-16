package kr.ac.jejunu.jnu_tong.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kr.ac.jejunu.jnu_tong.data.api.JNUService;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.data.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleTimeVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;

public class DataManager implements IDataManager, IPreferencesHelper {
    private IPreferencesHelper preferencesHelper;
    private IDataBus dataBus;

    @Inject
    DataManager(Context context, JNUService mJNUService) {
        this.preferencesHelper = new PreferenceHelper(context);
        this.dataBus = new DataBus(mJNUService);
    }

    /*
    * IDataBus
    * */
    @Override
    public MainContract.Model getMainModel() {
        return dataBus.getMainModel();
    }

    @Override
    public void doGetDepartureBusList() {
        dataBus.doGetDepartureBusList();
    }

    @Override
    public void doGetJNUEvent() {
        dataBus.doGetJNUEvent();
    }

    @Override
    public void doGetShuttleTime(int stationId) {
        dataBus.doGetShuttleTime(stationId);
    }

    @Override
    public void doGetShuttleList(String course) {
        dataBus.doGetShuttleList(course);
    }

    @Override
    public Disposable getCityBusTimeListObservable(String busId, Consumer<List<BusTimeVO>> onNext, Consumer<Throwable> onError) {
        return dataBus.getCityBusTimeListObservable(busId, onNext, onError);
    }

    @Override
    public Observable<List<DepartureBusVO>> getDepartureBusObservable() {
        return dataBus.getDepartureBusObservable();
    }

    @Override
    public Observable<JNUEventVO> getJnuEventObservable() {
        return dataBus.getJnuEventObservable();
    }

    @Override
    public Observable<ShuttleTimeVO> getShuttleTimeObservable() {
        return dataBus.getShuttleTimeObservable();
    }

    @Override
    public Observable<List<ShuttleVO>> getShuttleListObservable() {
        return dataBus.getShuttleListObservable();
    }

    /*
     * PreferenceHelper
     * */
    @Override
    public void addOftenBus(String busID) {
        preferencesHelper.addOftenBus(busID);
    }

    @Override
    public void deleteOftenBus(String busID) {
        preferencesHelper.deleteOftenBus(busID);
    }

    @Override
    public boolean hasOftenBus(String busID) {
        return preferencesHelper.hasOftenBus(busID);
    }

    @Override
    public int getShuttleBookmarkId() {
        return preferencesHelper.getShuttleBookmarkId();
    }

    @Override
    public void setShuttleBookmarkId(int shuttleId) {
        preferencesHelper.setShuttleBookmarkId(shuttleId);
    }

    @Override
    public String getShuttleBookmarkTitle() {
        return preferencesHelper.getShuttleBookmarkTitle();
    }

    @Override
    public void setShuttleBookmarkTitle(String shuttleTitle) {
        preferencesHelper.setShuttleBookmarkTitle(shuttleTitle);
    }
}
