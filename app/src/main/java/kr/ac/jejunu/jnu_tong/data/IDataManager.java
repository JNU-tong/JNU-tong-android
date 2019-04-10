package kr.ac.jejunu.jnu_tong.data;

import java.util.List;

import io.reactivex.Observable;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.data.vo.JNUEventVO;

public interface IDataManager {
    MainContract.Model getMainModel();

    void getDepartureBusList();
    void executeJNUEventTask();

    Observable<List<DepartureBusVO>> getDepartureBusObservable();
    Observable<JNUEventVO> getJnuEventObservable();
}
