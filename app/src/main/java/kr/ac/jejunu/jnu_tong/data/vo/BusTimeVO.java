package kr.ac.jejunu.jnu_tong.data.vo;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by seung-yeol on 2018. 4. 10..
 */
@Getter
public class BusTimeVO {
    @SerializedName("scheduleNo")
    private String scheduleNo;

    @SerializedName("departureTime")
    private String departureTime;

    @SerializedName("weekdayHoliday")
    private String weekdayHoliday;

    @SerializedName("remainTime")
    private int remainTime;
}
