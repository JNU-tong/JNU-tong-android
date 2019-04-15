package kr.ac.jejunu.jnu_tong.ui.shuttle_bus;

import javax.inject.Inject;

import androidx.viewpager.widget.PagerAdapter;
import kr.ac.jejunu.jnu_tong.data.AutoClearedDisposable;
import kr.ac.jejunu.jnu_tong.data.IDataManager;

/**
 * Created by seung-yeol on 2018. 4. 24..
 */

public class ShuttlePresenter implements ShuttleContract.Presenter {
    private final IDataManager dataManager;
    private final ShuttleContract.View view;
    private final AutoClearedDisposable autoClearedDisposable;
    private final ShuttleContract.Model model;
    private final PagerAdapter pagerAdapter;
    private PagerAdapterContract.View adapterView;
    private PagerAdapterContract.Model adapterModel;

    @Inject
    ShuttlePresenter(AutoClearedDisposable autoClearedDisposable, ShuttleContract.View view, IDataManager dataManager, UltraPagerAdapter ultraPagerAdapter) {
        this.view = view;
        this.dataManager = dataManager;
        this.autoClearedDisposable = autoClearedDisposable;
        adapterView = ultraPagerAdapter;
        adapterModel = ultraPagerAdapter;
        pagerAdapter = ultraPagerAdapter;
        model = new ShuttleModel();
        model.selectARoute();
    }

    @Override
    public void onCreate() {
        model.setBookmarkId(dataManager.getShuttleBookmarkId());
        view.setPositionByBookmarkId(model.getPositionByBookmarkId());

        autoClearedDisposable.add(dataManager.getShuttleListObservable().subscribe(shuttleVOS -> {
            adapterModel.changeProvider(shuttleVOS);
            adapterView.notifyDataChange();

            model.changeProvider(shuttleVOS);
            String[] shuttleBusStops = model.getShuttleBusStops();
            view.setBusPositionList(shuttleBusStops);
        }));

        dataManager.doGetShuttleList("A");
    }

    @Override
    public PagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }

    @Override
    public void leftBtnClick(int position) {
        if (position > 0) {
            view.setPagerPosition(position - 1);
        }
    }

    @Override
    public void rightBtnClick(int position) {
        if (position < ((PagerAdapter) adapterModel).getCount() - 1) {
            view.setPagerPosition(position + 1);
        }
    }

    @Override
    public void pageSelect(int position) {
        String[] busStopNames = adapterModel.getBusStopNames(position);
        view.routeTextChange(busStopNames[0], busStopNames[1], busStopNames[2]);

        if (adapterModel.getBusStopId(position) == model.getBookmarkId())
            view.setHeartOn(true);
        else view.setHeartOn(false);
    }

    @Override
    public void selectARoute() {
        dataManager.doGetShuttleList("A");
        adapterModel.selectARoute();
        model.selectARoute();
        view.setPositionByBookmarkId(model.getPositionByBookmarkId());
    }

    @Override
    public void selectBRoute() {
        dataManager.doGetShuttleList("B");
        adapterModel.selectBRoute();
        model.selectBRoute();
        view.setPositionByBookmarkId(model.getPositionByBookmarkId());
    }

    @Override
    public void heartClick(int position) {
        Integer stopId = adapterModel.getBusStopId(position);
        setBookmarkId(stopId);
    }

    @Override
    public void setBookmarkId(int stopId) {
        model.setBookmarkId(stopId);
        view.setPositionByBookmarkId(model.getPositionByBookmarkId());

        dataManager.setShuttleBookmarkId(stopId);
        dataManager.setShuttleBookmarkTitle(adapterModel.getBusStopNameById(stopId));
    }
}
