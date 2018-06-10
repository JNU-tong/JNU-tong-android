package kr.ac.jejunu.jnu_tong.vo;

/**
 * Created by seung-yeol on 2018. 6. 10..
 */

public class JNUEventVO {
    private Integer id;
    private Integer dDay;
    private String date;
    private String event;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getdDay() {
        return dDay;
    }

    public void setdDay(Integer dDay) {
        this.dDay = dDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
