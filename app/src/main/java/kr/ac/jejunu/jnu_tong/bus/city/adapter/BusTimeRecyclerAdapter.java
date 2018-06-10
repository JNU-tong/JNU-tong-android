package kr.ac.jejunu.jnu_tong.bus.city.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.vo.BusTimeVO;

/**
 * Created by seung-yeol on 2018. 4. 10.
 */

public class BusTimeRecyclerAdapter extends RecyclerView.Adapter<BusTimeRecyclerAdapter.BusTimeViewHolder>
        implements AdapterContract.View<BusTimeVO>{
    private ArrayList<BusTimeVO> provider;

    public BusTimeRecyclerAdapter(List<BusTimeVO> provider) {
        this.provider = new ArrayList<>(provider);
    }

    @Override
    public BusTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus_time_list, parent, false);

        return new BusTimeRecyclerAdapter.BusTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BusTimeViewHolder holder, int position) {
        BusTimeVO vo = provider.get(position);

        holder.departureTime.setText(vo.getDepartureTime());

        if (vo.getRemainTime() < 4){
            holder.countTime.setText("잠시후도착");
        }
        else {
            holder.countTime.setText(vo.getRemainTime()+"분");
        }

    }

    @Override
    public int getItemCount() {
        return provider.size();
    }

    @Override
    public void addItems(List<BusTimeVO> datas) {
        provider.clear();
        provider.addAll(datas);
        notifyDataSetChanged();
    }

    class BusTimeViewHolder extends RecyclerView.ViewHolder {
        TextView departureTime;
        TextView countTime;

        BusTimeViewHolder(View itemView) {
            super(itemView);

            departureTime = itemView.findViewById(R.id.text_departure_time);
            countTime = itemView.findViewById(R.id.text_count_time);
        }
    }
}