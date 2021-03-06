package kr.ac.jejunu.jnu_tong.ui.main;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.data.vo.JNUEventVO;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleTimeVO;
import kr.ac.jejunu.jnu_tong.ui.shuttle_bus.route.ARoute;

/**
 * Created by seung-yeol on 2018. 6. 10..
 */

public class SharedMainModel implements MainContract.Model {
    private Integer firstImgId;
    private Integer secondImgId;
    private String firstNo;
    private String secondNo;
    private String departTime;
    private String event;
    private String today;
    private String bookmarkedShuttleTitle;
    private ShuttleTimeVO shuttleTimeVO;
    private int bookmarkedShuttledStationId;

    @Override
    public void setDepartureVOS(List<DepartureBusVO> result) {
        if (result == null || result.size() == 0) {
            firstImgId = null;
            secondImgId = null;

            firstNo = null;
            secondNo = null;
        } else if (result.size() > 1) {
            Collections.sort(result);

            firstNo = result.get(0).getCityBusLineInfo().getLineNo() + "번";
            firstImgId = getImgId(result.get(0).getCityBusLineInfo().getLineNo());

            secondNo = result.get(1).getCityBusLineInfo().getLineNo() + "번";
            secondImgId = getImgId(result.get(1).getCityBusLineInfo().getLineNo());

            if (result.get(0).getRemainTime().getFirst() < 4) departTime = "잠시후출발";
            else departTime = result.get(0).getRemainTime().getFirst() + "분전";
        } else {
            firstNo = result.get(0).getCityBusLineInfo().getLineNo() + "번";
            firstImgId = getImgId(result.get(0).getCityBusLineInfo().getLineNo());

            if (result.get(0).getRemainTime().getFirst() < 4) departTime = "잠시후출발";
            else departTime = result.get(0).getRemainTime().getFirst() + "분전";

            secondImgId = null;
            secondNo = null;
        }
    }

    @Override
    public Integer[] getImgIds() {
        return new Integer[]{firstImgId, secondImgId};
    }

    @Override
    public String[] getBusNos() {
        return new String[]{firstNo, secondNo};
    }

    @Override
    public String getDepartTime() {
        return departTime;
    }

    @Override
    public void setJNUEvent(JNUEventVO eventVO) {
        String date = eventVO.getDate();
        Calendar cal = Calendar.getInstance();

        String dayOfWeek;
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                dayOfWeek = "(일)";
                break;
            case 2:
                dayOfWeek = "(월)";
                break;
            case 3:
                dayOfWeek = "(화)";
                break;
            case 4:
                dayOfWeek = "(수)";
                break;
            case 5:
                dayOfWeek = "(목)";
                break;
            case 6:
                dayOfWeek = "(금)";
                break;
            default:
                dayOfWeek = "(토)";
        }

        today = (cal.get(Calendar.MONTH) + 1) + "월 " +
                cal.get(Calendar.DAY_OF_MONTH) + "일" +
                dayOfWeek;

        if (date != null) {
            event = eventVO.getEvent() + "까지 D-" + eventVO.getDDay();
        }
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public String getToday() {
        return today;
    }

    @Override
    public void setBookmarkedShuttleTimeVO(ShuttleTimeVO shuttleTimeVO) {
        this.shuttleTimeVO = shuttleTimeVO;
    }

    @Override
    public String getBookmarkedShuttleTitle() {
        return bookmarkedShuttleTitle;
    }

    @Override
    public Integer getBookmarkedShuttleATime() {
        if (shuttleTimeVO != null && shuttleTimeVO.getA() != null) {
            return shuttleTimeVO.getA().getFirst();
        } else {
            return 9999;
        }
    }

    @Override
    public Integer getBookmarkedShuttleBTime() {
        if (shuttleTimeVO != null && shuttleTimeVO.getB() != null) {
            return shuttleTimeVO.getB().getFirst();
        } else {
            return 9999;
        }
    }

    @Override
    public int getBookmarkedShuttleStationId() {
        return bookmarkedShuttledStationId;
    }

    @Override
    public void setBookmarkedShuttleStationId(int stationId) {
        this.bookmarkedShuttledStationId = stationId;
        this.bookmarkedShuttleTitle = ARoute.values()[stationId - 1].getTitle();
    }

    private Integer getImgId(String lineNo) {
        if (lineNo.length() == 4) {
            return R.drawable.round_shape_yellow;
        } else if (lineNo.charAt(0) == '4') {
            return R.drawable.round_shape_green;
        } else /*if (vo.getLineNo().charAt(0) == '3') */ {
            return R.drawable.round_shape_sky;
        }
    }
}
