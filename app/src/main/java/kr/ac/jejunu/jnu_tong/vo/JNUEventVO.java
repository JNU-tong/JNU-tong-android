package kr.ac.jejunu.jnu_tong.vo;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by seung-yeol on 2018. 6. 10..
 */

@Getter
public class JNUEventVO {
    @SerializedName("id")
    private Integer id;

    @SerializedName("dDay")
    private Integer dDay;

    @SerializedName("date")
    private String date;

    @SerializedName("event")
    private String event;
}
