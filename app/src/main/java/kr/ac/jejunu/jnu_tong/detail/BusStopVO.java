package kr.ac.jejunu.jnu_tong.detail;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class BusStopVO {
    private String busStopName;

    BusStopVO(){

    }
    BusStopVO(String busStopName){
        this.busStopName = busStopName;
    }

    public String getBusStopName() {
        return busStopName;
    }

    public void setBusStopName(String busName) {

        this.busStopName = busName;
    }
}
