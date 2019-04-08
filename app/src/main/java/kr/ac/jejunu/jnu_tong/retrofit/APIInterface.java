package kr.ac.jejunu.jnu_tong.retrofit;

import java.util.Map;

import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/getDepartureSoonBusList")
    Call<Map<String, DepartureBusVO>> doGetDepartureBusList();
}
