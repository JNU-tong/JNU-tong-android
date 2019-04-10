package kr.ac.jejunu.jnu_tong.data;

import java.util.List;

import io.reactivex.Observable;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.data.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleTimeVO;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;

public interface IDataBus {
    MainContract.Model getMainModel();
    void doGetDepartureBusList();
    void doGetJNUEvent();
    void doGetShuttleTime(int stationId);
    Observable<List<DepartureBusVO>> getDepartureBusObservable();
    Observable<JNUEventVO> getJnuEventObservable();
    Observable<ShuttleTimeVO> getShuttleTimeObservable();
}
