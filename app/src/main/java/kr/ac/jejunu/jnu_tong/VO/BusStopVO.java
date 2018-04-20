package kr.ac.jejunu.jnu_tong.VO;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class BusStopVO {
    private String stationId;
    private String stationName;
    private String stationOrder;

    public BusStopVO(){}

    public BusStopVO(String name){
        stationName = name;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(String stationOrder) {
        this.stationOrder = stationOrder;
    }
}
