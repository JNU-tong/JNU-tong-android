package kr.ac.jejunu.jnu_tong.task;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.main.BusStopVO;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class GetBusStopTask extends BaseTask {

    public GetBusStopTask(TaskResult taskResult){
        super(taskResult);
    }

    @Override
    String url(String[] params){
        return CommonData.getBusLineListURL(params[0]);
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
