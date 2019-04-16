package kr.ac.jejunu.jnu_tong.ui.city_bus.time;

import androidx.recyclerview.widget.RecyclerView;
import dagger.Binds;
import dagger.Module;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.BusAdapterContract;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.FragmentPresenter;

@Module
public abstract class BusTimeModule {
    @Binds
    public abstract RecyclerView.Adapter bindAdapter(BusTimeRecyclerAdapter busTimeRecyclerAdapter);

    @Binds
    public abstract BusAdapterContract.View<BusTimeVO> bindView(BusTimeRecyclerAdapter busTimeRecyclerAdapter);

    @Binds
    public abstract FragmentPresenter bindPresenter(BusTimeFragmentPresenter busTimeFragmentPresenter);
}
