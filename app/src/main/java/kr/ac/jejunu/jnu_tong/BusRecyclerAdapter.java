package kr.ac.jejunu.jnu_tong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by seung-yeol on 2018. 4. 6..
 */

public class BusRecyclerAdapter extends RecyclerView.Adapter<BusRecyclerAdapter.BusViewHolder> {
    private Provider provider;
    private Context context;

    BusRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus_list, parent, false);

        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BusViewHolder holder, int position) {
        holder.busStopName.setText(provider.getData(position).getName());
    }

    @Override
    public int getItemCount() {
        return Provder.getCount();
    }

    class BusViewHolder extends RecyclerView.ViewHolder {
        TextView busStopName;

        BusViewHolder(View itemView) {
            super(itemView);

            busStopName = itemView.findViewById(R.id.bus_stop_name);
        }
    }

}
