/*
package kr.ac.jejunu.jnu_tong.task.get;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.vo.JNUEventVO;

*/
/**
 * Created by seung-yeol on 2018. 6. 10..
 *//*


public class GetJNUEventTask extends BaseGetTask<JNUEventVO> {
    public GetJNUEventTask(OnTaskResultListener<JNUEventVO> onTaskResultListener) {
        super(onTaskResultListener);
    }

    @Override
    String url(String[] params) {
        return CommonApp.getJNUEventURL();
    }

    @Override
    JNUEventVO parse(String responseString) {
        JNUEventVO eventVO = new JNUEventVO();
        try {
            if (responseString != null) {
                JSONObject result = new JSONObject(responseString);

                eventVO.setId(result.has("id") ? result.getInt("id") : null);
                eventVO.setDate(result.has("date") ? result.getString("date") : null);
                eventVO.setdDay(result.has("dDay") ? result.getInt("dDay") : null);
                eventVO.setEvent(result.has("event") ? result.getString("event") : null);
            }
        } catch (JSONException e) {
            Log.e(this.toString(), "parse: error " + e.toString() );
        }

        return eventVO;
    }
}
*/
