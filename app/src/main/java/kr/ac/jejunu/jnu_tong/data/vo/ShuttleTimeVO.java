package kr.ac.jejunu.jnu_tong.data.vo;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by seung-yeol on 2018. 6. 11..
 */
@Getter
public class ShuttleTimeVO {
    @SerializedName("A")
    private A a;

    @SerializedName("B")
    private B b;

    @Getter
    public static class A{
        private Integer first;
        private Integer second;
    }

    @Getter
    public static class B{
        private Integer first;
        private Integer second;
    }
}
