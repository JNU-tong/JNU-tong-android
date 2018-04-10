package kr.ac.jejunu.jnu_tong.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.main.BusTimeVO;

/**
 * Created by seung-yeol on 2018. 4. 10.
 */

public class BusTimeRecyclerAdapter extends RecyclerView.Adapter<BusTimeRecyclerAdapter.BusTimeViewHolder>{
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
        
    }

    public void add(List<BusTimeVO> addData){
        provider.addAll(addData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return provider.size();
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