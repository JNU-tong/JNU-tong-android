package kr.ac.jejunu.jnu_tong.bus.city.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.vo.BusStopVO;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class BusStopRecyclerAdapter extends RecyclerView.Adapter<BusStopRecyclerAdapter.BusViewHolder>
        implements AdapterContract.View<BusStopVO>{
    private LinkedList<BusStopVO> busStopVOS;

    public BusStopRecyclerAdapter(List<BusStopVO> busStopVOS) {
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

    @Override
    public void addItems(List<BusStopVO> datas) {
        busStopVOS.clear();
        busStopVOS.addAll(datas);
        notifyDataSetChanged();
    }

    class BusViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView busStopName;

        BusViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            busStopName = itemView.findViewById(R.id.bus_stop_name);
        }
    }
}
