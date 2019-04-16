package kr.ac.jejunu.jnu_tong.ui.city_bus;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class CityBusModule {
    @Binds
    public abstract AppCompatActivity bindLifeCycleOwner(CityBusDetailActivity cityBusDetailActivity);
}
