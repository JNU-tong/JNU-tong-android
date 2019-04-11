package kr.ac.jejunu.jnu_tong.ui.shuttle_bus;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ShuttleModule {
    @Binds
    public abstract ShuttleContract.Presenter bindPresenter(ShuttlePresenter shuttlePresenter);

    @Binds
    public abstract ShuttleContract.View bindView(ShuttleDetailActivity shuttleDetailActivity);

    @Binds
    public abstract AppCompatActivity bindLifeCycleOwner(ShuttleDetailActivity shuttleDetailActivity);
}
