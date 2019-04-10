package kr.ac.jejunu.jnu_tong.ui.main;

import android.util.Log;

import javax.inject.Inject;

import kr.ac.jejunu.jnu_tong.data.AutoClearedDisposable;
import kr.ac.jejunu.jnu_tong.data.IDataManager;

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

    @Override
    public void onCreate() {
        mainModel.setBookmarkedShuttleStationId(dataManager.getShuttleBookmarkId());
        dataManager.doGetShuttleTime(mainModel.getBookmarkedShuttleStationId());

        autoClearedDisposable.add(dataManager.getDepartureBusObservable().subscribe(departureBusVOS -> {
            if (departureBusVOS == null || departureBusVOS.size() == 0) {
                Log.e(this.toString(), "결과가 없어.. ");
                mainView.setDepartureBusData(new Integer[]{null, null}, new String[]{null, null}, null);
            } else {
                mainModel.setDepartureVOS(departureBusVOS);
                mainView.setDepartureBusData(mainModel.getImgIds(), mainModel.getBusNos(), mainModel.getDepartTime());
            }
        }));

        autoClearedDisposable.add(dataManager.getJnuEventObservable().subscribe(jnuEventVO -> {
            mainModel.setJNUEvent(jnuEventVO);
            mainView.setJNUEvent(mainModel.getToday(), mainModel.getEvent());
        }));

        autoClearedDisposable.add(dataManager.getShuttleTimeObservable().subscribe(shuttleTimeVO -> {
            mainModel.setBookmarkedShuttleTimeVO(shuttleTimeVO);
            mainView.setShuttleBusData(
                    mainModel.getBookmarkedShuttleTitle(), mainModel.getBookmarkedShuttleATime(), mainModel.getBookmarkedShuttleBTime()
            );
        }));
    }

    @Override
    public void refreshClick() {
        dataManager.doGetDepartureBusList();
        dataManager.doGetJNUEvent();
    }

    @Override
    public void clickCityBus() {
        mainView.onClickCityBus();
    }
}
