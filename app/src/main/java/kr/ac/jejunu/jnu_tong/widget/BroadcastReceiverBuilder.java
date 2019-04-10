package kr.ac.jejunu.jnu_tong.widget;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BroadcastReceiverBuilder {
    @ContributesAndroidInjector
    public abstract AppWidget bindAppWidget();
}
