package kr.ac.jejunu.jnu_tong.data.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.data.vo.BusStopVO;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.data.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleTimeVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JNUService {
    @GET("getDepartureSoonBusList")
    Call<Map<String, DepartureBusVO>> doGetDepartureBusList();

    @GET("jnu/eventDay/first")
    Call<JNUEventVO> doGetJNUEvent();

    @GET("getJnuBusArrivalInfoByStationId")
    Call<ShuttleTimeVO> doGetShuttleTime(@Query("stationId") int stationId);

    @GET("getJnuBusArrivalInfoListByCourse")
    Call<List<ShuttleVO>> doGetShuttleList(@Query("course") String course);

    @GET("getBusScheduleListByLineId/{busId}")
    Observable<List<BusTimeVO>> getCityTimeList(@Path("busId") String busId);

    @GET("getBusStationListByLineId/{busId}")
    Observable<List<BusStopVO>> getCityStopList(@Path("busId") String busId);

    class Factory{
        public static JNUService create(){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor()).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CommonApp.baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            return retrofit.create(JNUService.class);
        }
    }
}
