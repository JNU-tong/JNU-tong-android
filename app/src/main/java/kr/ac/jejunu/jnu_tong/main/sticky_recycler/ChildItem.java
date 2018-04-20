package kr.ac.jejunu.jnu_tong.main.sticky_recycler;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.VO.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class ChildItem implements Item {
    private DepartureBusVO vo;
    private String busNum;
    private String busID;
    private String busDescription;
    private String remainTime;
    private String busType;
    private int backGroundId;
    private int heartImageId;

    public ChildItem(DepartureBusVO vo) {
        this.vo = vo;
        busNum = vo.getLineNo() + "번";
        busID = vo.getLineID();
        busDescription = vo.getDescription();
        remainTime = vo.getFirst() + "분전";

        if (vo.getLineNo().length() == 5) {
            busType = "yellow";
            backGroundId = R.drawable.round_shape_yellow;
        } else if (vo.getLineNo().charAt(0) == '4') {
            busType = "green";
            backGroundId = R.drawable.round_shape_green;
        } else /*if (vo.getLineNo().charAt(0) == '3') */{
            busType = "sky";
            backGroundId = R.drawable.round_shape_sky;

        }
    }

    public DepartureBusVO getVo() {
        return vo;
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

    public String getBusType() {
        return busType;
    }

    public int getBackGroundId() {
        return backGroundId;
    }

    public int getHeartImageId() {
        return heartImageId;
    }

    public void setHeartImageId(int heartImageId) {
        this.heartImageId = heartImageId;
    }
}
