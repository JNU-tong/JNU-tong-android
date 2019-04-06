package kr.ac.jejunu.jnu_tong;

import java.util.List;

import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;

public interface IDataManager {
    MainContract.Model getMainModel();

    void executeDepartureBusTask(OnTaskResultListener<List<DepartureBusVO>> onTaskResultListener);

    void executeJNUEventTask(OnTaskResultListener<JNUEventVO> eventVOOnTaskResultListener);
}
