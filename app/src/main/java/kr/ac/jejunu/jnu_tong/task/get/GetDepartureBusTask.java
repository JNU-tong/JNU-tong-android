package kr.ac.jejunu.jnu_tong.task.get;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class GetDepartureBusTask extends BaseGetTask<List<DepartureBusVO>> {
    public GetDepartureBusTask(OnTaskResultListener onTaskResultListener) {
        super(onTaskResultListener);
    }

    @Override
    String url(String[] params) {
        return CommonApp.getBusDepartureListURL();
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
