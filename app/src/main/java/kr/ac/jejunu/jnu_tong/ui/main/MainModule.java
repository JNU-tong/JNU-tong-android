package kr.ac.jejunu.jnu_tong.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {
    @Binds
    public abstract AppCompatActivity bindLifecyleOwner(MainActivity mainActivity);

    @Binds
    public abstract MainContract.View bindMainView(MainActivity mainActivity);

    @Binds
    public abstract MainContract.Presenter bindMainPresenter(MainPresenter mainPresenter);
}
