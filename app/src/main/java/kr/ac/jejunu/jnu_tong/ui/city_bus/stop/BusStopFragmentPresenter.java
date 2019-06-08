package kr.ac.jejunu.jnu_tong.ui.city_bus.stop;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.ac.jejunu.jnu_tong.data.AutoClearedDisposable;
import kr.ac.jejunu.jnu_tong.data.api.JNUService;
import kr.ac.jejunu.jnu_tong.data.vo.BusStopVO;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.BusAdapterContract;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.FragmentPresenter;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusStopFragmentPresenter implements FragmentPresenter, OnTaskResultListener<List<BusStopVO>> {
    private final JNUService mJNUService;
    private final BusAdapterContract.View<BusStopVO> view;
    private final AutoClearedDisposable autoClearedDisposable;
    private String busId;

    @Inject
    BusStopFragmentPresenter(AutoClearedDisposable autoClearedDisposable, BusAdapterContract.View<BusStopVO> view, JNUService mJNUService) {
        this.autoClearedDisposable = autoClearedDisposable;
        this.view = view;
        this.mJNUService = mJNUService;
    }

    private void executeTask(){
        Log.e("retrofit", "URL : http://106.10.46.151:8080/getBusStationListByLineId/" + busId );
        autoClearedDisposable.add(mJNUService.getCityStopList(busId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::addItems, Throwable::printStackTrace));
    }

    @Override
    public void onTaskResult(List<BusStopVO> result) {
        if (result != null){
            view.addItems(result);
        }
    }

    @Override
    public void onViewCreated() {
        executeTask();
    }

    @Override
    public void setBusId(String busId) {
        this.busId = busId;
    }

    @Override
    public void refreshClick() {
        executeTask();
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return ((RecyclerView.Adapter) view);
    }
}
