package kr.ac.jejunu.jnu_tong.main;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class BusVO {
    private String lineID;
    private String lineNo;
    private String detailLineNo;
    private String description;

    public BusVO(){

    }

    public BusVO(String lineID) {
        this.lineID = lineID;
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }
}
