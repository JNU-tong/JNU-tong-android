package kr.ac.jejunu.jnu_tong.data.vo;


import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import lombok.Getter;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

@Getter
public class DepartureBusVO implements Comparable<DepartureBusVO> {
    @SerializedName("cityBusLineInfo")
    private CityBusLineInfo cityBusLineInfo;
    @SerializedName("remainTime")
    private RemainTime remainTime;

    @Override
    public int compareTo(@NonNull DepartureBusVO o) {
        return remainTime.first.compareTo(o.remainTime.first);
    }

    @Getter
    public class CityBusLineInfo {
        @SerializedName("lineId")
        private String lineId;

        @SerializedName("lineNo")
        private String lineNo;

        @SerializedName("detailLineNo")
        private String detailLineNo;

        @SerializedName("description")
        private String description;
    }

    @Getter
    public class RemainTime {
        @SerializedName("first")
        private Integer first;

        @SerializedName("second")
        private Integer second;
    }
}
