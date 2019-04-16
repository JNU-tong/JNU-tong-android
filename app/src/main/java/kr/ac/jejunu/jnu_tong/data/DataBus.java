package kr.ac.jejunu.jnu_tong.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import kr.ac.jejunu.jnu_tong.data.api.JNUService;
import kr.ac.jejunu.jnu_tong.data.vo.BusStopVO;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.data.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleTimeVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.ui.main.SharedMainModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBus implements IDataBus {
    private BehaviorSubject<JNUEventVO> jnuEventObservable;
    private BehaviorSubject<List<DepartureBusVO>> departureBusObservable;
    private BehaviorSubject<ShuttleTimeVO> shuttleTimeObservable;
    private BehaviorSubject<List<ShuttleVO>> shuttleListObservable;
    private MainContract.Model mainModel;
    private JNUService mJNUService;

    DataBus(JNUService mJNUService) {
        this.mJNUService = mJNUService;
        mainModel = new SharedMainModel();
        departureBusObservable = BehaviorSubject.createDefault(new ArrayList<>());
        jnuEventObservable = BehaviorSubject.createDefault(new JNUEventVO());
        shuttleTimeObservable = BehaviorSubject.createDefault(new ShuttleTimeVO());
        shuttleListObservable = BehaviorSubject.createDefault(new ArrayList<>());
    }

    @Override
    public MainContract.Model getMainModel() {
        return mainModel;
    }

    @Override
    public void doGetDepartureBusList() {
        Call<Map<String, DepartureBusVO>> request = mJNUService.doGetDepartureBusList();
        request.enqueue(new Callback<Map<String, DepartureBusVO>>() {
            @Override
            public void onResponse(Call<Map<String, DepartureBusVO>> call, Response<Map<String, DepartureBusVO>> response) {
                Log.e("retrofit", "url: " + call.request().url().toString());
                if (response.isSuccessful() && response.body() != null) {
                    departureBusObservable.onNext(new ArrayList<>(response.body().values()));
                }
            }

            @Override
            public void onFailure(Call<Map<String, DepartureBusVO>> call, Throwable t) {
                Log.e("실패", "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void doGetJNUEvent() {
        mJNUService.doGetJNUEvent().enqueue(new Callback<JNUEventVO>() {
            @Override
            public void onResponse(Call<JNUEventVO> call, Response<JNUEventVO> response) {
                Log.e("retrofit", "url: " + call.request().url().toString());
                if (response.isSuccessful() && response.body() != null) {
                    jnuEventObservable.onNext(response.body());
                }
            }

            @Override
            public void onFailure(Call<JNUEventVO> call, Throwable t) {
                Log.e("실패", "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void doGetShuttleTime(int stationId) {
        mJNUService.doGetShuttleTime(stationId).enqueue(new Callback<ShuttleTimeVO>() {
            @Override
            public void onResponse(Call<ShuttleTimeVO> call, Response<ShuttleTimeVO> response) {
                Log.e("retrofit", "url: " + call.request().url().toString());
                if (response.isSuccessful() && response.body() != null) {
                    shuttleTimeObservable.onNext(response.body());
                }
            }

            @Override
            public void onFailure(Call<ShuttleTimeVO> call, Throwable t) {
                Log.e("실패", "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void doGetShuttleList(String course) {
        mJNUService.doGetShuttleList(course).enqueue(new Callback<List<ShuttleVO>>() {
            @Override
            public void onResponse(Call<List<ShuttleVO>> call, Response<List<ShuttleVO>> response) {
                Log.e("retrofit", "url: " + call.request().url().toString());
                if (response.isSuccessful() && response.body() != null) {
                    shuttleListObservable.onNext(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ShuttleVO>> call, Throwable t) {
                Log.e("실패", "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public Disposable getCityBusTimeListObservable(String busId, Consumer<List<BusTimeVO>> onNext, Consumer<Throwable> onError) {
        return mJNUService.getCityTimeList(busId)
                .subscribeOn(Schedulers.io())
                .subscribe(onNext,onError);
    }

    @Override
    public Disposable getCityBusStopListObservable(String busId, Consumer<List<BusStopVO>> onNext, Consumer<Throwable> onError) {
        return mJNUService.getCityStopList(busId)
                .subscribeOn(Schedulers.io())
                .subscribe(onNext,onError);
    }


    @Override
    public Observable<List<DepartureBusVO>> getDepartureBusObservable() {
        return departureBusObservable;
    }

    @Override
    public Observable<JNUEventVO> getJnuEventObservable() {
        return jnuEventObservable;
    }

    @Override
    public Observable<ShuttleTimeVO> getShuttleTimeObservable() {
        return shuttleTimeObservable;
    }

    @Override
    public Observable<List<ShuttleVO>> getShuttleListObservable() {
        return shuttleListObservable;
    }
}
