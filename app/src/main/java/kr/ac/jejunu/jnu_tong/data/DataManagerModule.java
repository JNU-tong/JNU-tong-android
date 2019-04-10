package kr.ac.jejunu.jnu_tong.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kr.ac.jejunu.jnu_tong.data.api.JNUService;

@Module
public class DataManagerModule {
    @Provides
    @Singleton
    public JNUService bindJUNService(){
        return JNUService.Factory.create();
    }
}
