package kr.ac.jejunu.jnu_tong.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.main.BusStopVO;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class BusRecyclerAdapter extends RecyclerView.Adapter<BusRecyclerAdapter.BusViewHolder> {
    private LinkedList<BusStopVO> busStopVOS;

    BusRecyclerAdapter(List<BusStopVO> busStopVOS) {
        this.busStopVOS = new LinkedList<>(busStopVOS);
    }

    @Override
    public BusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus_stop_list, parent, false);

        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BusViewHolder holder, int position) {
        holder.busStopName.setText(busStopVOS.get(position).getStationName());
    }

    @Override
    public int getItemCount() {
        return busStopVOS.size();
    }

    public void add(List<BusStopVO> data){
        busStopVOS.addAll(data);
        notifyDataSetChanged();
    }

    class BusViewHolder extends RecyclerView.ViewHolder {
        TextView busStopName;

        BusViewHolder(View itemView) {
            super(itemView);

            busStopName = itemView.findViewById(R.id.bus_stop_name);
        }
    }
}
