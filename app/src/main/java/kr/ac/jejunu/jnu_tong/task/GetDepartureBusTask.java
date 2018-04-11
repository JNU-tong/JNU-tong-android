package kr.ac.jejunu.jnu_tong.task;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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
    ArrayList<DepartureBusVO> parse(String responseString) {
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
                DepartureBusVO vo = new DepartureBusVO();
            }

        } catch (JSONException e) {
            Log.e(this.toString(), "JSON 익셉션! : " + e.getMessage());
        }

        return null;
    }
}
