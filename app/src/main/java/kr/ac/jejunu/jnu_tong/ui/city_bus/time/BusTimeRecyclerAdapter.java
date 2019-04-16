package kr.ac.jejunu.jnu_tong.ui.city_bus.time;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.RecyclerView;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.data.vo.BusTimeVO;
import kr.ac.jejunu.jnu_tong.ui.city_bus.base.BusAdapterContract;

/**
 * Created by seung-yeol on 2018. 4. 10.
 */

public class BusTimeRecyclerAdapter extends RecyclerView.Adapter<BusTimeRecyclerAdapter.BusTimeViewHolder>
        implements BusAdapterContract.View<BusTimeVO> {
    private ArrayList<BusTimeVO> provider;

    @Inject
    BusTimeRecyclerAdapter() {
        provider = new ArrayList<>();
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

        if (vo.getRemainTime() < 4) {
            holder.countTime.setText("잠시후도착");
        } else {
            holder.countTime.setText(vo.getRemainTime() + "분");
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