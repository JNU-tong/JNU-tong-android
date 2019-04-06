package kr.ac.jejunu.jnu_tong;

import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.task.get.GetDepartureBusTask;
import kr.ac.jejunu.jnu_tong.task.get.GetJNUEventTask;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.ui.main.MainModel;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;

public class DataManager implements IDataManager {
    @Inject
    DataManager() {
    }

    private MainContract.Model mainModel = new MainModel();

    public MainContract.Model getMainModel() {
        return mainModel;
    }

    //observable로 바꿀거임
    public void executeDepartureBusTask(OnTaskResultListener<List<DepartureBusVO>> listener) {
        GetDepartureBusTask getDepartureBusTask = new GetDepartureBusTask(listener);

        getDepartureBusTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //observable로 바꿀거임
    public void executeJNUEventTask(OnTaskResultListener<JNUEventVO> listener){
        GetJNUEventTask eventTask = new GetJNUEventTask(listener);

        eventTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
