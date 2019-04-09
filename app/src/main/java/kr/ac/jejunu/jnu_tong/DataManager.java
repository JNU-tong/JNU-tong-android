package kr.ac.jejunu.jnu_tong;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import kr.ac.jejunu.jnu_tong.retrofit.JNUService;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.ui.main.MainModel;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager implements IDataManager {
    private BehaviorSubject<JNUEventVO> jnuEventObservable;
    private BehaviorSubject<List<DepartureBusVO>> departureBusObservable;
    private JNUService JNUService;
    private MainContract.Model mainModel = new MainModel();

    @Inject
    DataManager(JNUService JNUService) {
        this.JNUService = JNUService;
        departureBusObservable = BehaviorSubject.createDefault(new ArrayList<>());
        jnuEventObservable = BehaviorSubject.createDefault(new JNUEventVO());
        getDepartureBusList();
        executeJNUEventTask();
    }

    @Override
    public MainContract.Model getMainModel() {
        return mainModel;
    }

    @Override
    public void getDepartureBusList() {
        Call<Map<String, DepartureBusVO>> request = JNUService.doGetDepartureBusList();
        request.enqueue(new Callback<Map<String, DepartureBusVO>>() {
            @Override
            public void onResponse(Call<Map<String, DepartureBusVO>> call, Response<Map<String, DepartureBusVO>> response) {
                Log.e("retrofit", "url: " + call.request().url().toString() );
                if (response.isSuccessful() && response.body() != null){
                    departureBusObservable.onNext(new ArrayList<>(response.body().values()));
                }
            }

            @Override
            public void onFailure(Call<Map<String, DepartureBusVO>> call, Throwable t) {
                Log.e("실패", "onFailure: " + t.getMessage() );
                t.printStackTrace();
            }
        });
    }

    @Override
    public void executeJNUEventTask() {
        JNUService.doGetJNUEvent().enqueue(new Callback<JNUEventVO>() {
            @Override
            public void onResponse(Call<JNUEventVO> call, Response<JNUEventVO> response) {
                Log.e("retrofit", "url: " + call.request().url().toString() );
                if (response.isSuccessful() && response.body() != null){
                    jnuEventObservable.onNext(response.body());
                }
            }

            @Override
            public void onFailure(Call<JNUEventVO> call, Throwable t) {
                Log.e("실패", "onFailure: " + t.getMessage() );
                t.printStackTrace();
            }
        });
    }

    @Override
    public Observable<List<DepartureBusVO>> getDepartureBusObservable() {
        Log.e(this.toString(), "getDepartureBusObservable.size = "+ departureBusObservable.getValue().size() );
        return departureBusObservable;
    }

    @Override
    public Observable<JNUEventVO> getJnuEventObservable() {
        return jnuEventObservable;
    }
}
