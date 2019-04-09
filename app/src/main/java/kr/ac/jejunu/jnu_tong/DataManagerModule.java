package kr.ac.jejunu.jnu_tong;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kr.ac.jejunu.jnu_tong.retrofit.JNUService;

@Module
public class DataManagerModule {
    @Provides
    @Singleton
    public JNUService bindJUNService(){
        return JNUService.Factory.create();
    }
}
