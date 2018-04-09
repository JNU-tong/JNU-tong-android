package kr.ac.jejunu.jnu_tong.main;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class DepartureBusVO {
    private String lineID;
    private String lineNo;
    private String detailLineNo;
    private String description;

    private int first;
    private int second;

    public DepartureBusVO(){

    }

    public DepartureBusVO(String lineID) {
        this.lineID = lineID;
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getDetailLineNo() {
        return detailLineNo;
    }

    public void setDetailLineNo(String detailLineNo) {
        this.detailLineNo = detailLineNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
