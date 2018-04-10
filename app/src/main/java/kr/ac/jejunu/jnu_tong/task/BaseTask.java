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

public abstract class BaseTask extends AsyncTask<String, Void, ArrayList<BusStopVO>> {
    private TaskResult<BusStopVO> taskResult;

    BaseTask(TaskResult taskResult){
        this.taskResult = taskResult;
    }

    @Override
    protected ArrayList<BusStopVO> doInBackground(String[] params) {
        String urlStr = url(params);
        Log.e(this.toString(), "URL :  " + urlStr);

        String response = read(urlStr);

        return parse(response);
    }

    abstract String url(String[] params);

    private String read(String urlStr){
        byte [ ] byteData = null;

        try {
            URL url = new URL(urlStr);

            URLConnection conn = url.openConnection ( );
            conn.setUseCaches ( false );
            conn.connect();
            InputStream is = conn.getInputStream ( );

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream ( );
            byte [ ] byteBuffer = new byte [ 1024 ];
            int nLength = 0;

            while ( ( nLength = is.read ( byteBuffer , 0 , byteBuffer.length ) ) != -1 ){
                byteArrayOutputStream.write ( byteBuffer , 0 , nLength );
            }

            byteData = byteArrayOutputStream.toByteArray ( );

            if ( byteData.length <= 0 ){
                return null;
            }
        } catch (IOException e) {
            Log.e(this.toString(), " 아이오익셉션 " + e.getMessage());
        }

        return new String(byteData);
    }

    abstract ArrayList<BusStopVO> parse(String responseString);

    @Override
    protected void onPostExecute(ArrayList<BusStopVO> resultList) {
        taskResult.setTaskResult(resultList);
    }
}
