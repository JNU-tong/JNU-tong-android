package kr.ac.jejunu.jnu_tong.application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import kr.ac.jejunu.jnu_tong.DataManager;
import kr.ac.jejunu.jnu_tong.IDataManager;

@Module
public abstract class AppModule {
    @Singleton
    @Binds
    abstract IDataManager bindDataManager(DataManager dataManager);
}
