package kr.ac.jejunu.jnu_tong.main;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 6. 10..
 */

public class MainModel implements MainContract.Model {
    private Integer firstImgId;
    private Integer secondImgId;
    private String firstNo;
    private String secondNo;
    private String departTime;

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

            firstNo = result.get(0).getLineNo() + "번";
            firstImgId = getImgId(result.get(0).getLineNo());

            secondNo = result.get(1).getLineNo() + "번";
            secondImgId = getImgId(result.get(1).getLineNo());

            if(result.get(0).getFirst() < 4) departTime = "잠시후도착";
            else departTime = result.get(0).getFirst()+"분";
        }
        else{
            firstNo = result.get(0).getLineNo() + "번";
            firstImgId = getImgId(result.get(0).getLineNo());

            if(result.get(0).getFirst() < 4) departTime = "잠시후도착";
            else departTime = result.get(0).getFirst()+"분";

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

    private Integer getImgId(String lineNo) {
        if (lineNo.length() == 5) {
            return R.drawable.round_shape_yellow;
        } else if (lineNo.charAt(0) == '4') {
            return R.drawable.round_shape_green;
        } else /*if (vo.getLineNo().charAt(0) == '3') */{
            return R.drawable.round_shape_sky;
        }
    }
}
