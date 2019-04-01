package kr.ac.jejunu.jnu_tong.expended_main.sticky_recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.ac.jejunu.jnu_tong.CommonData;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.bus.city.CityBusDetailActivity;
import kr.ac.jejunu.jnu_tong.main.MainAdapterContract;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class StickyRecyclerAdapter extends RecyclerView.Adapter
        implements StickyHeaderHandler, MainAdapterContract.View<Item>, MainAdapterContract.Model {
    private List<Item> items;
    private Context context;
    private CommonData commonData;
    private OnDetailClickListener onDetailClickListener;
    private OnHeartClickListener onHeartClickListener;
    private ArrayList<DepartureBusVO> oftenBus;
    private ArrayList<DepartureBusVO> normalBus;

    public StickyRecyclerAdapter(Context context, List<Item> items) {
        this.context = context;
        this.commonData = (CommonData)((AppCompatActivity)context).getApplication();
        this.items = new ArrayList<>(items);
    }

    @Override
    public List<Item> getAdapterData() {
        return items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header, parent, false);
            return new MyHeaderHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citybus_list, parent, false);
            return new MyChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);

        if (item instanceof HeaderItem) {
            HeaderItem headerItem = ((HeaderItem) item);
            ((MyHeaderHolder) holder).headerText.setText(headerItem.getHeaderTitle());
        } else {
            ChildItem childItem = ((ChildItem) item);

            ((MyChildViewHolder) holder).busNumText.setBackground(context.getResources().getDrawable(childItem.getBackGroundId()));
            ((MyChildViewHolder) holder).busNumText.setText(childItem.getBusNum());
            ((MyChildViewHolder) holder).descriptionText.setText(childItem.getBusDescription());
            ((MyChildViewHolder) holder).remainText1.setText(childItem.getFirstRemainTime());
            ((MyChildViewHolder) holder).remainText2.setText(childItem.getSecondRemainTime());
            ((MyChildViewHolder) holder).heartImage.setBackground(context.getResources().getDrawable(childItem.getHeartImageId()));
            ((MyChildViewHolder) holder).itemView.setOnClickListener(view -> onDetailClickListener.onDetailClick(position));
            ((MyChildViewHolder) holder).heartImage.setOnClickListener(view -> onHeartClickListener.onHeartClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof HeaderItem)
            return 1;
        else
            return 2;
    }

    @Override
    public void setItems(List<Item> items) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SimpleDiffCallback(this.items, items));
        this.items.clear();
        this.items.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public List<Item> taskResultItems(List<DepartureBusVO> result) {
        ArrayList<DepartureBusVO> arrayList = new ArrayList<>(result);
        oftenBus = new ArrayList<>();
        normalBus = new ArrayList<>();

        for (DepartureBusVO vo : arrayList) {
            if ( commonData.hasOftenBus(vo.getLineID()) ) {
                oftenBus.add(vo);
            } else {
                normalBus.add(vo);
            }
        }

        return sortItems();
    }

    @Override
    public void goDetailActivity(int position) {
        ChildItem childItem = (ChildItem) items.get(position);

        Intent intent = new Intent(context, CityBusDetailActivity.class);
        intent.putExtra("busType", childItem.getBusType());
        intent.putExtra("busID", childItem.getBusID());
        intent.putExtra("busNo", childItem.getBusNum());
        intent.putExtra("busDescription", childItem.getBusDescription());
        context.startActivity(intent);
    }

    @Override
    public void heartClick(int position) {
        ChildItem childItem = (ChildItem) items.get(position);

        if (commonData.hasOftenBus(childItem.getBusID())){
            commonData.deleteOftenBus(childItem.getBusID());

            DepartureBusVO vo = oftenBus.remove(position-1);
            normalBus.add(vo);
        }
        else {
            commonData.addOftenBus(childItem.getBusID());

            DepartureBusVO vo = normalBus.remove(position - oftenBus.size() - 2);
            oftenBus.add(vo);
        }

        ArrayList<Item> listItems = sortItems();
        setItems(listItems);
    }

    private ArrayList<Item> sortItems() {
        Collections.sort(oftenBus);
        Collections.sort(normalBus);

        ArrayList<Item> listItems = new ArrayList<>();
        listItems.add(new HeaderItem("자주타는버스"));
        for (DepartureBusVO vo :
                oftenBus) {
            ChildItem childItem = new ChildItem(vo);
            childItem.setHeartImageId(R.mipmap.ic_heart_on);
            listItems.add(childItem);
        }

        listItems.add(new HeaderItem("도착예정버스"));
        for (DepartureBusVO vo :
                normalBus) {
            ChildItem childItem = new ChildItem(vo);
            childItem.setHeartImageId(R.mipmap.ic_heart_off);
            listItems.add(childItem);
        }
        return listItems;
    }

    public void setDetailClickListener(OnDetailClickListener onDetailClickListener) {
        this.onDetailClickListener = onDetailClickListener;
    }

    public void setOnHeartClickListener(OnHeartClickListener onHeartClickListener) {
        this.onHeartClickListener = onHeartClickListener;
    }

    private class MyHeaderHolder extends RecyclerView.ViewHolder {
        TextView headerText;

        MyHeaderHolder(View itemView) {
            super(itemView);
            headerText = itemView.findViewById(R.id.text_header);
        }
    }

    private class MyChildViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView busNumText;
        TextView descriptionText;
        TextView remainText1;
        TextView remainText2;
        ImageButton heartImage;

        MyChildViewHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            busNumText = itemView.findViewById(R.id.text_bus_num);
            descriptionText = itemView.findViewById(R.id.text_description);
            remainText1 = itemView.findViewById(R.id.text_remain1);
            remainText2 = itemView.findViewById(R.id.text_remain2);
            heartImage = itemView.findViewById(R.id.btn_heart);
        }
    }
}