package kr.ac.jejunu.jnu_tong.main.sticky_recycler;

import android.os.Build;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class StickyRecyclerAdapter extends RecyclerView.Adapter implements StickyHeaderHandler {
    private List<Item> data;

    public StickyRecyclerAdapter(List<Item> items) {
        data = new ArrayList<>(items);
    }

    public void setData(List<Item> items) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SimpleDiffCallback(data, items));
        data.clear();
        data.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public List<Item> getAdapterData() {
        return data;
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header, parent, false);
            return new MyHeaderHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_child, parent, false);
            return new MyChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = data.get(position);

        if (item instanceof HeaderItem) {
            HeaderItem headerItem = ((HeaderItem) item);
            ((MyHeaderHolder) holder).headerText.setText(headerItem.getHeaderTitle());
        } else {
            ChildItem childItem = ((ChildItem) item);
            ((MyChildViewHolder) holder).busNumText.setText(childItem.getBusNum());
            ((MyChildViewHolder) holder).descriptionText.setText(childItem.getBusDescription());
            ((MyChildViewHolder) holder).remainText.setText(childItem.getRemainTime());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof HeaderItem)
            return 1;
        else
            return 2;
    }

    private class MyHeaderHolder extends RecyclerView.ViewHolder {
        TextView headerText;

        MyHeaderHolder(View itemView) {
            super(itemView);
            headerText = itemView.findViewById(R.id.text_header);
        }
    }

    private class MyChildViewHolder extends RecyclerView.ViewHolder {
        TextView busNumText;
        TextView descriptionText;
        TextView remainText;
        ImageButton heartImage;

        MyChildViewHolder(View itemView) {
            super(itemView);
            busNumText = itemView.findViewById(R.id.text_bus_num);
            descriptionText = itemView.findViewById(R.id.text_description);
            remainText = itemView.findViewById(R.id.text_remain);
            heartImage = itemView.findViewById(R.id.btn_heart);
        }
    }
}
