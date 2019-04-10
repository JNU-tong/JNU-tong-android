package kr.ac.jejunu.jnu_tong.ui;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kr.ac.jejunu.jnu_tong.ui.main.MainActivity;
import kr.ac.jejunu.jnu_tong.ui.main.MainModule;
import kr.ac.jejunu.jnu_tong.ui.shuttle_bus.ShuttleBusDetailActivity;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.UnfoldedMainActivity;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.UnfoldedMainModule;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = MainModule.class)
    public abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = UnfoldedMainModule.class)
    public abstract UnfoldedMainActivity bindUnfoldedMainActivity();

    @ContributesAndroidInjector
    public abstract ShuttleBusDetailActivity bindShuttleBusDetailActivity();
}
