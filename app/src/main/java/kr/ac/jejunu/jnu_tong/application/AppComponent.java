package kr.ac.jejunu.jnu_tong.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import kr.ac.jejunu.jnu_tong.ui.ActivityBuilder;

@Singleton
@Component(modules = {ActivityBuilder.class,
        AppModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<CommonApp> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder context(Context context);

        AppComponent build();
    }
}
