package kr.ac.jejunu.jnu_tong.task.get;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.vo.ShuttleTimeVO;
import kr.ac.jejunu.jnu_tong.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 6. 11..
 */

public class GetShuttleMainTask extends BaseGetTask<ShuttleTimeVO> {
    public GetShuttleMainTask(OnTaskResultListener onTaskResultListener) {
        super(onTaskResultListener);
    }

    @Override
    String url(String[] params) {
        return params[0];
    }

    @Override
    ShuttleTimeVO parse(String responseString) {
        ShuttleTimeVO vo = new ShuttleTimeVO();
        try {
            JSONObject result = new JSONObject(responseString);
            JSONObject a = result.getJSONObject("A");
            JSONObject b = result.getJSONObject("B");

            vo.setAFirst( a.has("first") ? a.getInt("first") : null) ;
            vo.setASecond( a.has("second") ? a.getInt("second") : null);
            vo.setBFirst( b.has("first") ? b.getInt("first") : null) ;
            vo.setBSecond( b.has("second") ? b.getInt("second") : null);
        } catch (JSONException e) {
            Log.e(this.toString(), "parse:error " + e.toString() );
        }
        return vo;
    }
}
