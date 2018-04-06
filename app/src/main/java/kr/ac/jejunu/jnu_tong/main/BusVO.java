package kr.ac.jejunu.jnu_tong.main;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class BusVO {
    private String BusName;

    public BusVO(){

    }
    public BusVO(String busName) {
        BusName = busName;
    }

    public String getBusName() {
        return BusName;
    }

    public void setBusName(String busName) {
        BusName = busName;
    }
}
