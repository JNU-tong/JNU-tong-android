package kr.ac.jejunu.jnu_tong.ui.city_bus.stop;

import androidx.recyclerview.widget.RecyclerView;
import dagger.Binds;
import dagger.Module;
import kr.ac.jejunu.jnu_tong.data.vo.BusStopVO;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.BusAdapterContract;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.FragmentPresenter;

@Module
public abstract class BusStopModule {
    @Binds
    public abstract RecyclerView.Adapter bindAdapter(BusStopRecyclerAdapter busStopRecyclerAdapter);

    @Binds
    public abstract BusAdapterContract.View<BusStopVO> bindView(BusStopRecyclerAdapter busStopRecyclerAdapter);

    @Binds
    public abstract FragmentPresenter bindPresenter(BusStopFragmentPresenter busStopFragmentPresenter);
}
