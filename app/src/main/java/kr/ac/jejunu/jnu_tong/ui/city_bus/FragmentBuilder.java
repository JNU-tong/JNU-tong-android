package kr.ac.jejunu.jnu_tong.ui.city_bus;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kr.ac.jejunu.jnu_tong.ui.city_bus.stop.BusStopModule;
import kr.ac.jejunu.jnu_tong.ui.city_bus.stop.BusStopRecyclerFragment;
import kr.ac.jejunu.jnu_tong.ui.city_bus.time.BusTimeModule;
import kr.ac.jejunu.jnu_tong.ui.city_bus.time.BusTimeRecyclerFragment;

@Module
public abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = BusStopModule.class)
    public abstract BusStopRecyclerFragment bindBusStopRecyclerFragment();

    @ContributesAndroidInjector(modules = BusTimeModule.class)
    public abstract BusTimeRecyclerFragment bindBusTimeRecyclerFragment();
}
