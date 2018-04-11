package kr.ac.jejunu.jnu_tong.main.sticky_recycler;

import kr.ac.jejunu.jnu_tong.main.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class ChildItem implements Item {
    private String busNum;
    private String busTitle;
    private String busDescriptoon;
    private String remainTime;

    public ChildItem(DepartureBusVO vo) {
        busNum = vo.getLineNo() + "번";
        busTitle = vo.getDescription();
        remainTime = vo.getFirst() + "분";
    }

    public String getBusNum() {
        return busNum;
    }

    public String getBusTitle() {
        return busTitle;
    }

    public String getBusDescription() {
        return busDescriptoon;
    }

    public String getRemainTime() {
        return remainTime;
    }
}
