package kr.ac.jejunu.jnu_tong;

import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.task.get.GetDepartureBusTask;
import kr.ac.jejunu.jnu_tong.task.get.GetJNUEventTask;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.ui.main.MainModel;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;

public class DataManager implements IDataManager {
    private Observable<JNUEventVO> jnuEventObservable;
    private Observable<List<DepartureBusVO>> departureBusObservable;
    private MainContract.Model mainModel = new MainModel();

    @Inject
    DataManager() {
        executeDepartureBusTask();
        executeJNUEventTask();
    }

    @Override
    public MainContract.Model getMainModel() {
        return mainModel;
    }

    @Override
    public void executeDepartureBusTask() {
        GetDepartureBusTask getDepartureBusTask = new GetDepartureBusTask((OnTaskResultListener<List<DepartureBusVO>>)
                result -> departureBusObservable = Observable.just(result));

        getDepartureBusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
        return departureBusObservable;
    }

    @Override
    public Observable<JNUEventVO> getJnuEventObservable() {
        return jnuEventObservable;
    }
}
