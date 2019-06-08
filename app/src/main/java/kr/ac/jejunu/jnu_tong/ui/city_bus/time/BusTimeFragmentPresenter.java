package kr.ac.jejunu.jnu_tong.ui.city_bus.time;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.RecyclerView;
import kr.ac.jejunu.jnu_tong.data.AutoClearedDisposable;
import kr.ac.jejunu.jnu_tong.data.IDataManager;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.BusAdapterContract;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.FragmentPresenter;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public class BusTimeFragmentPresenter implements FragmentPresenter{
    private final IDataManager dataManager;
    private final AutoClearedDisposable autoClearedDisposable;
    private BusAdapterContract.View<BusTimeVO> view;
    private String busId;

    @Inject
    BusTimeFragmentPresenter(AutoClearedDisposable autoClearedDisposable, BusAdapterContract.View<BusTimeVO> view, IDataManager dataManager) {
        this.autoClearedDisposable = autoClearedDisposable;
        this.view = view;
        this.dataManager = dataManager;
    }

    private void executeTask() {
        Log.e("retrofit", "URL : http://106.10.46.151:8080/getBusScheduleListByLineId/" + busId);
        autoClearedDisposable.add(dataManager.getCityBusTimeListObservable(busId,
                busTimeVOS -> {
                    ArrayList<BusTimeVO> remainBusTime = getValidValue(busTimeVOS);
                    view.addItems(remainBusTime);
                }, Throwable::printStackTrace));
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

    // 남은시간 양수인것만 가져옴
    private ArrayList<BusTimeVO> getValidValue(List<BusTimeVO> vos) {
        ArrayList<BusTimeVO> busTimeVOArrayList = new ArrayList<>();

        if (vos != null) {
            for (BusTimeVO vo :
                    vos) {
                if (vo.getRemainTime() >= 0) {
                    busTimeVOArrayList.add(vo);
                }
            }
        }

        return busTimeVOArrayList;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return ((RecyclerView.Adapter) view);
    }
}
