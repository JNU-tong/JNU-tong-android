package kr.ac.jejunu.jnu_tong.ui.main;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.data.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.data.vo.JNUEventVO;

/**
 * Created by seung-yeol on 2018. 6. 10..
 */

public class MainModel implements MainContract.Model {
    private Integer firstImgId;
    private Integer secondImgId;
    private String firstNo;
    private String secondNo;
    private String departTime;
    private String event;
    private String today;

    @Override
    public void setDepartureVOS(List<DepartureBusVO> result) {
        if (result == null || result.size() == 0){
            firstImgId = null;
            secondImgId = null;

            firstNo = null;
            secondNo = null;
        }
        else if (result.size() > 1){
            Collections.sort(result);

            firstNo = result.get(0).getCityBusLineInfo().getLineNo() + "번";
            firstImgId = getImgId(result.get(0).getCityBusLineInfo().getLineNo());

            secondNo = result.get(1).getCityBusLineInfo().getLineNo() + "번";
            secondImgId = getImgId(result.get(1).getCityBusLineInfo().getLineNo());

            if(result.get(0).getRemainTime().getFirst() < 4) departTime = "잠시후도착";
            else departTime = result.get(0).getRemainTime().getFirst() + "분전";
        }
        else{
            firstNo = result.get(0).getCityBusLineInfo().getLineNo() + "번";
            firstImgId = getImgId(result.get(0).getCityBusLineInfo().getLineNo());

            if(result.get(0).getRemainTime().getFirst() < 4) departTime = "잠시후도착";
            else departTime = result.get(0).getRemainTime().getFirst()+"분전";

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
        Calendar cal= Calendar.getInstance ();

        String dayOfWeek;
        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case 1: dayOfWeek ="(일)"; break;
            case 2: dayOfWeek ="(월)"; break;
            case 3: dayOfWeek ="(화)"; break;
            case 4: dayOfWeek ="(수)"; break;
            case 5: dayOfWeek ="(목)"; break;
            case 6: dayOfWeek ="(금)"; break;
            default: dayOfWeek ="(토)";
        }

        today = new StringBuilder()
                .append(cal.get(Calendar.MONTH) + 1).append("월 ")
                .append(cal.get(Calendar.DAY_OF_MONTH)).append("일")
                .append(dayOfWeek).toString();

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

    private Integer getImgId(String lineNo) {
        if (lineNo.length() == 4) {
            return R.drawable.round_shape_yellow;
        } else if (lineNo.charAt(0) == '4') {
            return R.drawable.round_shape_green;
        } else /*if (vo.getLineNo().charAt(0) == '3') */{
            return R.drawable.round_shape_sky;
        }
    }
}
