package kr.ac.jejunu.jnu_tong.data;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.data.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleTimeVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;
import kr.ac.jejunu.jnu_tong.ui.main.MainContract;

public interface IDataBus {
    MainContract.Model getMainModel();
    void doGetDepartureBusList();
    void doGetJNUEvent();
    void doGetShuttleTime(int stationId);
    void doGetShuttleList(String course);

    Disposable getCityBusTimeListObservable(String busId, Consumer<List<BusTimeVO>> onNext, Consumer<Throwable> onError);

    Observable<List<DepartureBusVO>> getDepartureBusObservable();
    Observable<JNUEventVO> getJnuEventObservable();
    Observable<ShuttleTimeVO> getShuttleTimeObservable();
    Observable<List<ShuttleVO>> getShuttleListObservable();
}
