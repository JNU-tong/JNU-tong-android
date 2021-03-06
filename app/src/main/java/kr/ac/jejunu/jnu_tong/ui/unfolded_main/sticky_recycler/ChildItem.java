package kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class ChildItem implements Item {
    private DepartureBusVO vo;
    private String busNum;
    private String busID;
    private String busDescription;
    private String firstRemainTime;
    private String secondRemainTime;
    private String busType;
    private int backGroundId;
    private int heartImageId;

    ChildItem(DepartureBusVO vo) {
        this.vo = vo;
        busNum = vo.getCityBusLineInfo().getLineNo() + "번";
        busID = vo.getCityBusLineInfo().getLineId();
        busDescription = vo.getCityBusLineInfo().getDescription();
        if (vo.getRemainTime().getFirst() < 4){
            firstRemainTime = "잠시후";
        } else {
            firstRemainTime = vo.getRemainTime().getFirst() + "분전";
        }

        if (vo.getRemainTime().getSecond() == null) {
            secondRemainTime = "";
        } else if (vo.getRemainTime().getSecond() < 4){
            secondRemainTime = "잠시후";
        } else {
            secondRemainTime = vo.getRemainTime().getSecond() + "분전";
        }

        if (vo.getCityBusLineInfo().getLineNo().length() == 4) {
            busType = "yellow";
            backGroundId = R.drawable.round_shape_yellow;
        } else if (vo.getCityBusLineInfo().getLineNo().charAt(0) == '4') {
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

    public String getFirstRemainTime() {
        return firstRemainTime;
    }
    public String getSecondRemainTime() {
        return secondRemainTime;
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
