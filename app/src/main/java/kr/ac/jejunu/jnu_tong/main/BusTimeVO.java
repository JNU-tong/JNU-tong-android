package kr.ac.jejunu.jnu_tong.main;

/**
 * Created by seung-yeol on 2018. 4. 10..
 */

public class BusTimeVO {

    private String scheduleNo;
    private String departureTime;
    private String weekdayHoliday;
    private int remainTime;


    public String getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(String scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getWeekdayHoliday() {
        return weekdayHoliday;
    }

    public void setWeekdayHoliday(String weekdayHoliday) {
        this.weekdayHoliday = weekdayHoliday;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }
}
