package kr.ac.jejunu.jnu_tong.data.vo;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

@Getter
public class BusStopVO {
    @SerializedName("stationId")
    private String stationId;

    @SerializedName("stationName")
    private String stationName;

    @SerializedName("stationOrder")
    private String stationOrder;
}
