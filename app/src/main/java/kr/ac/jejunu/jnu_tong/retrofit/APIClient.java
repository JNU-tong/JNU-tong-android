package kr.ac.jejunu.jnu_tong.retrofit;

import kr.ac.jejunu.jnu_tong.application.CommonApp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static Retrofit getClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()).build();

        return new Retrofit.Builder()
                .baseUrl(CommonApp.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
