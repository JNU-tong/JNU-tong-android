package kr.ac.jejunu.jnu_tong.main.sticky_recycler;

import kr.ac.jejunu.jnu_tong.main.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class ChildItem implements Item {
    private String busNum;
    private String busID;
    private String busDescription;
    private String remainTime;

    public ChildItem(DepartureBusVO vo) {
        busNum = vo.getLineNo() + "번";
        busID = vo.getLineID();
        busDescription = vo.getDescription();
        remainTime = vo.getFirst() + "분전";
    }

    public String getBusNum() {
        return busNum;
    }

    public String getBusID() {
        return busID;
    }

    public String getBusDescription() {
        return busDescription;
    }

    public String getRemainTime() {
        return remainTime;
    }
}
