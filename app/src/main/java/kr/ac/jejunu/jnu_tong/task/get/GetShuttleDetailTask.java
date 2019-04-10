package kr.ac.jejunu.jnu_tong.task.get;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 5. 19..
 */

public class GetShuttleDetailTask extends BaseGetTask<List<ShuttleVO>> {
    public GetShuttleDetailTask(OnTaskResultListener onTaskResultListener) {
        super(onTaskResultListener);
    }

    @Override
    String url(String[] params) {
        return params[0];
    }

    @Override
    List<ShuttleVO> parse(String responseString) {
        LinkedList<ShuttleVO> shuttleVOS = new LinkedList<>();

        try {
            JSONArray jsonArray = new JSONArray(responseString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                JSONObject stationObject = object.getJSONObject("jnuBusStation");
                JSONObject remainTime = object.getJSONObject("remainTime");

                ShuttleVO vo = new ShuttleVO();
                vo.setId(stationObject.has("id") ? stationObject.getInt("id") : null);
                vo.setName(stationObject.has("name") ? stationObject.getString("name") : null);
                vo.setId(stationObject.has("order") ? stationObject.getInt("id") : null);
                vo.setFirstTime( !remainTime.isNull("first") ? remainTime.getInt("first") : null);
                vo.setSecondTime( !remainTime.isNull("second") ? remainTime.getInt("second") : null);

                shuttleVOS.add(vo);
            }
        } catch (JSONException e) {
            Log.e(this.toString(), "JSON 익셉션! : " + e.getMessage());
        }
        return shuttleVOS;
    }
}
