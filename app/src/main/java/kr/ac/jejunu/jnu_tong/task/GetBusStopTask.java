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

import kr.ac.jejunu.jnu_tong.main.BusStopVO;

/**
 * Created by seung-yeol on 2018. 4. 9..
 */

public class GetBusStopTask extends AsyncTask<String, Void, ArrayList<BusStopVO>> {
    private TaskResult<BusStopVO> taskResult;

    public GetBusStopTask(TaskResult taskResult){
        this.taskResult = taskResult;
    }

    @Override
    protected ArrayList<BusStopVO> doInBackground(String[] params) {
        String urlStr = params[0];
        ArrayList<BusStopVO> parsedList = null;

        try {
            Log.e(this.toString(), "URL :  " + urlStr);
            URL url = new URL(urlStr);

            URLConnection conn = url.openConnection ( );
            conn.setUseCaches ( false );
            conn.connect();
            InputStream is = conn.getInputStream ( );

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream ( );
            byte [ ] byteBuffer = new byte [ 1024 ];
            byte [ ] byteData = null;
            int nLength = 0;

            while ( ( nLength = is.read ( byteBuffer , 0 , byteBuffer.length ) ) != -1 ){
                byteArrayOutputStream.write ( byteBuffer , 0 , nLength );
            }

            byteData = byteArrayOutputStream.toByteArray ( );

            if ( byteData.length <= 0 ){
                return null;
            }

            String response = new String(byteData);

            JSONArray responseJson = new JSONArray(response);
            parsedList = parseToList(responseJson);
        } catch (JSONException e) {
            Log.e(this.toString(), " 제이슨익셉션 " );
        } catch (IOException e) {
            Log.e(this.toString(), " 아이오익셉션 " );
        }

        return parsedList;
    }

    private ArrayList<BusStopVO> parseToList(JSONArray responseJSON){
        ArrayList<BusStopVO> arrayList = new ArrayList<>();

        if (responseJSON == null){
            return null;
        }

        for (int i = 0 ; i < responseJSON.length() ; i++){
            try {
                JSONObject jsonObject = responseJSON.getJSONObject(i);

                BusStopVO busStopVO = new BusStopVO();

                busStopVO.setStationId(jsonObject.has("stationId")? jsonObject.getString("stationId") : null);
                busStopVO.setStationId(jsonObject.has("StationName")? jsonObject.getString("StationName") : null);
                busStopVO.setStationId(jsonObject.has("stationOrder")? jsonObject.getString("stationOrder") : null);

                arrayList.add(busStopVO);
            } catch (JSONException e) {
                Log.e(this.toString(), " 제이슨익셉션2 " );
            }
        }

        return arrayList;
    }


    @Override
    protected void onPostExecute(ArrayList<BusStopVO> resultList) {
        taskResult.setTaskResult(resultList);
    }
}
