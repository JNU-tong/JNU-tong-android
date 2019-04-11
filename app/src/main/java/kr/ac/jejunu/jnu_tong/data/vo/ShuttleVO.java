package kr.ac.jejunu.jnu_tong.data.vo;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by seung-yeol on 2018. 5. 19..
 */

@Getter
public class ShuttleVO {
    @SerializedName("jnuBusStation")
    private JnuBusStation jnuBusStation;

    @SerializedName("remainTime")
    private RemainTime remainTime;

    @Getter
    private class JnuBusStation{
        @SerializedName("id")
        private Integer id;

        @SerializedName("mane")
        private String name;

        @SerializedName("order")
        private Integer order;

    }

    @Getter
    public class RemainTime{
        @SerializedName("first")
        private Integer firstTime;

        @SerializedName("second")
        private Integer secondTime;
    }
}
