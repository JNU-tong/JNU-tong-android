package kr.ac.jejunu.jnu_tong.application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import kr.ac.jejunu.jnu_tong.data.DataManager;
import kr.ac.jejunu.jnu_tong.data.IDataManager;

@Module
public abstract class AppModule {
    @Singleton
    @Binds
    abstract IDataManager bindDataManager(DataManager dataManager);
}
