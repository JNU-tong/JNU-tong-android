package kr.ac.jejunu.jnu_tong.task;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.main.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class GetDepartureBusTask extends BaseTask<DepartureBusVO> {
    public GetDepartureBusTask(TaskResult taskResult) {
        super(taskResult);
    }

    @Override
    String url(String[] params) {
        return CommonData.getBusDepartureListURL();
    }

    @Override
    List<DepartureBusVO> parse(String responseString) {
        LinkedList<DepartureBusVO> departureBusVOS = new LinkedList<>();

        try {
            JSONObject responseObject = new JSONObject(responseString);
            Iterator iterator = responseObject.keys();

            LinkedList<String> keys = new LinkedList<>();
            for (Iterator it = iterator; it.hasNext(); ) {
                keys.add((String)it.next());
            }

            for (String key :
                    keys) {
                JSONObject jsonObject = responseObject.getJSONObject(key);
                JSONObject cityBusLineInfo = jsonObject.getJSONObject("cityBusLineInfo");
                JSONObject remainTime = jsonObject.getJSONObject("remainTime");

                DepartureBusVO vo = new DepartureBusVO();
                vo.setLineID(cityBusLineInfo.getString("lineId"));
                vo.setLineNo(cityBusLineInfo.getString("lineNo"));
                vo.setDetailLineNo(cityBusLineInfo.getString("detailLineNo"));
                vo.setDescription(cityBusLineInfo.getString("description"));
                vo.setFirst(remainTime.isNull("first") ?  -1 : remainTime.getInt("first"));
                vo.setSecond(remainTime.isNull("second") ?  -1 : remainTime.getInt("second"));

                departureBusVOS.add(vo);
            }

        } catch (JSONException e) {
            Log.e(this.toString(), "JSON 익셉션! : " + e.getMessage());
        }

        return departureBusVOS;
    }
}
