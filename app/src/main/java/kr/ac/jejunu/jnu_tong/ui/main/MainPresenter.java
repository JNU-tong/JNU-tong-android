package kr.ac.jejunu.jnu_tong.ui.main;

import android.os.AsyncTask;
import android.util.Log;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.data.AutoClearedDisposable;
import kr.ac.jejunu.jnu_tong.data.IDataManager;
import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.task.get.GetShuttleMainTask;
import kr.ac.jejunu.jnu_tong.ui.bus.shuttle.route.ARoute;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleTimeVO;

/**
 * Created by seung-yeol on 2018. 4. 20..
 */

public class MainPresenter implements MainContract.Presenter{
    private final MainContract.View mainView;
    private final MainContract.Model mainModel;
    private final IDataManager dataManager;
    private final AutoClearedDisposable autoClearedDisposable;

    @Inject
    MainPresenter(AutoClearedDisposable autoClearedDisposable, MainContract.View mainView, IDataManager dataManager) {
        this.mainView = mainView;
        this.dataManager = dataManager;
        this.autoClearedDisposable = autoClearedDisposable;
        mainModel = dataManager.getMainModel();
    }

    public void onCreate() {
        autoClearedDisposable.add(dataManager.getDepartureBusObservable().subscribe(departureBusVOS -> {
            if (departureBusVOS == null || departureBusVOS.size() == 0) {
                Log.e(this.toString(), "결과가 없어.. ");
                mainView.setDepartureBusData(new Integer[]{null, null}, new String[]{null, null}, null);
            } else {
                mainModel.setDepartureVOS(departureBusVOS);
                mainView.setDepartureBusData(mainModel.getImgIds(), mainModel.getBusNos(), mainModel.getDepartTime());
            }
        }));
    }

    @Override
    public void setShuttleBookmarkId(int stationId) {
        GetShuttleMainTask shuttleMainTask = new GetShuttleMainTask(result -> mainView.setShuttleBusData(ARoute.values()[stationId - 1].getTitle(),
                ((ShuttleTimeVO) result).getAFirst(), ((ShuttleTimeVO) result).getBFirst())
        );

        shuttleMainTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                CommonApp.getShuttlePointUrl(stationId)
        );
    }

    @Override
    public void refreshClick() {
        dataManager.getDepartureBusList();
        dataManager.executeJNUEventTask();
    }

    @Override
    public void clickCityBus() {
        mainView.onClickCityBus();
    }
}
