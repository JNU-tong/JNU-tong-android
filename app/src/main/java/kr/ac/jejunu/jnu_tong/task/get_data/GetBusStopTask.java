package kr.ac.jejunu.jnu_tong.task.get_data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.vo.BusStopVO;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class GetBusStopTask extends BaseGetTask<BusStopVO> {

    public GetBusStopTask(OnTaskResultListener onTaskResultListener){
        super(onTaskResultListener);
    }

    @Override
    String url(String[] params){
        return params[0];
    }

    @Override
    ArrayList<BusStopVO> parse(String responseString){
        JSONArray responseJSON = null;

        try {
            responseJSON = new JSONArray(responseString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<BusStopVO> arrayList = new ArrayList<>();

        if (responseJSON == null){
            return null;
        }

        for (int i = 0 ; i < responseJSON.length() ; i++){
            try {
                JSONObject jsonObject = responseJSON.getJSONObject(i);

                BusStopVO busStopVO = new BusStopVO();

                busStopVO.setStationId(jsonObject.has("stationId") ? jsonObject.getString("stationId") : null);
                busStopVO.setStationName(jsonObject.has("stationName") ? jsonObject.getString("stationName") : null);
                busStopVO.setStationOrder(jsonObject.has("stationOrder") ? jsonObject.getString("stationOrder") : null);

                arrayList.add(busStopVO);
            } catch (JSONException e) {
                Log.e(this.toString(), " 제이슨익셉션2 " );
            }
        }

        return arrayList;
    }
}
