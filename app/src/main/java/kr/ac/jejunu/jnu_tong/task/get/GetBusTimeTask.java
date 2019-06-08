/*
package kr.ac.jejunu.jnu_tong.task.get;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.task.OnTaskResultListener;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;

*/
/**
 * Created by seung-yeol on 2018. 4. 10..
 *//*


public class GetBusTimeTask extends BaseGetTask<List<BusTimeVO>> {
    public GetBusTimeTask(OnTaskResultListener onTaskResultListener) {
        super(onTaskResultListener);
    }

    @Override
    String url(String[] params) {
        return params[0];
    }

    @Override
    ArrayList<BusTimeVO> parse(String responseString) {
        JSONObject jsonObject;
        ArrayList<BusTimeVO> busTimeVOArrayList = new ArrayList<>();

        try {
            if (responseString == null){
                return null;
            }

            jsonObject = new JSONObject(responseString);

            JSONArray jsonArray = jsonObject.getJSONArray("busScheduleList");

            //요거는 마지막시간 들어간거
            JSONObject latestSchedule = jsonObject.getJSONObject("latestSchedule");


            for (int i = 0; i < jsonArray.length() ; i++){
                JSONObject busScheduleList = jsonArray.getJSONObject(i);

                BusTimeVO busTimeVO = new BusTimeVO();
                busTimeVO.setScheduleNo(busScheduleList.has("scheduleNo") ? busScheduleList.getString("scheduleNo") : null);
                busTimeVO.setDepartureTime(busScheduleList.has("departureTime") ? busScheduleList.getString("departureTime") : null);
                busTimeVO.setRemainTime(busScheduleList.has("remainTime") ? busScheduleList.getInt("remainTime") : 0);
                busTimeVO.setWeekdayHoliday(busScheduleList.has("weekdayHoliday") ? busScheduleList.getString("weekdayHoliday") : null);

                busTimeVOArrayList.add(busTimeVO);
            }

        } catch (JSONException e) {
            Log.e(this.toString(), "jsonException!!! " + e.getMessage() );
        }

        return busTimeVOArrayList;
    }
}
*/
