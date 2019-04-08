package kr.ac.jejunu.jnu_tong;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import kr.ac.jejunu.jnu_tong.retrofit.APIClient;
import kr.ac.jejunu.jnu_tong.retrofit.APIInterface;
import kr.ac.jejunu.jnu_tong.task.get.GetJNUEventTask;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.ui.main.MainModel;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager implements IDataManager {
    private Observable<JNUEventVO> jnuEventObservable;
    private Observable<List<DepartureBusVO>> departureBusObservable;
    private MainContract.Model mainModel = new MainModel();

    @Inject
    DataManager() {
        getDepartureBusList();
        executeJNUEventTask();
    }

    @Override
    public MainContract.Model getMainModel() {
        return mainModel;
    }

//    @Override
//    public void getDepartureBusList() {
//        GetDepartureBusTask getDepartureBusTask = new GetDepartureBusTask((OnTaskResultListener<List<DepartureBusVO>>)
//                result -> departureBusObservable = Observable.just(result));
//
//        getDepartureBusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//    }

    @Override
    public void getDepartureBusList() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Map<String, DepartureBusVO>> request = apiInterface.doGetDepartureBusList();
        request.enqueue(new Callback<Map<String, DepartureBusVO>>() {
            @Override
            public void onResponse(Call<Map<String, DepartureBusVO>> call, Response<Map<String, DepartureBusVO>> response) {
                Log.e("retrofit", "url: " + call.request().url().toString() );
                if (response.body() != null){
                    departureBusObservable = Observable.just(new ArrayList<>(response.body().values()));
                }
            }

            @Override
            public void onFailure(Call<Map<String, DepartureBusVO>> call, Throwable t) {
                Log.e("실패", "onFailure: " );
                t.printStackTrace();
            }
        });
    }

    @Override
    public void executeJNUEventTask() {
        GetJNUEventTask eventTask = new GetJNUEventTask(result -> {
            if (result != null) {
                jnuEventObservable = Observable.just(result);
            }
        });

        eventTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public Observable<List<DepartureBusVO>> getDepartureBusObservable() {
        if (departureBusObservable == null){
            return null;
        }
        return departureBusObservable;
    }

    @Override
    public Observable<JNUEventVO> getJnuEventObservable() {
        return jnuEventObservable;
    }
}
