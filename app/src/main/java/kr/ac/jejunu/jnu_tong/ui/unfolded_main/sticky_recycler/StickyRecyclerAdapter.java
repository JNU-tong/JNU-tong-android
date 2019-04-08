package kr.ac.jejunu.jnu_tong.ui.unfolded_main.sticky_recycler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.application.CommonApp;
import kr.ac.jejunu.jnu_tong.ui.bus.city.CityBusDetailActivity;
import kr.ac.jejunu.jnu_tong.ui.unfolded_main.AdapterContract;
import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 11..
 */

public class StickyRecyclerAdapter extends RecyclerView.Adapter
        implements StickyHeaderHandler, AdapterContract.View<Item>, AdapterContract.Model {
    private List<Item> items;
    private AppCompatActivity activity;
    private CommonApp commonApp;
    private OnDetailClickListener onDetailClickListener;
    private OnHeartClickListener onHeartClickListener;
    private ArrayList<DepartureBusVO> oftenBus;
    private ArrayList<DepartureBusVO> normalBus;

    @Inject
    public StickyRecyclerAdapter(AppCompatActivity activity) {
        this.activity = activity;
        this.commonApp = (CommonApp)activity.getApplication();
        items = new ArrayList<>();
    }

    @Override
    public List<Item> getAdapterData() {
        return items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header, parent, false);
            return new MyHeaderHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citybus_list, parent, false);
            return new MyChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);

        if (item instanceof HeaderItem) {
            HeaderItem headerItem = ((HeaderItem) item);
            ((MyHeaderHolder) holder).headerText.setText(headerItem.getHeaderTitle());
        } else {
            ChildItem childItem = ((ChildItem) item);
            MyChildViewHolder childHolder = (MyChildViewHolder) holder;

            childHolder.busNumText.setBackground(activity.getResources().getDrawable(childItem.getBackGroundId()));
            childHolder.busNumText.setText(childItem.getBusNum());
            childHolder.descriptionText.setText(childItem.getBusDescription());
            childHolder.remainText1.setText(childItem.getFirstRemainTime());
            childHolder.remainText2.setText(childItem.getSecondRemainTime());
            childHolder.heartImage.setBackground(activity.getResources().getDrawable(childItem.getHeartImageId()));
            childHolder.itemView.setOnClickListener(view -> onDetailClickListener.onDetailClick(position));
            childHolder.heartImage.setOnClickListener(view -> onHeartClickListener.onHeartClick(position));
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
            if ( commonApp.hasOftenBus(vo.getLineID()) ) {
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

        Intent intent = new Intent(activity, CityBusDetailActivity.class);
        intent.putExtra("busType", childItem.getBusType());
        intent.putExtra("busID", childItem.getBusID());
        intent.putExtra("busNo", childItem.getBusNum());
        intent.putExtra("busDescription", childItem.getBusDescription());
        activity.startActivity(intent);
    }

    @Override
    public void heartClick(int position) {
        ChildItem childItem = (ChildItem) items.get(position);

        if (commonApp.hasOftenBus(childItem.getBusID())){
            commonApp.deleteOftenBus(childItem.getBusID());

            DepartureBusVO vo = oftenBus.remove(position-1);
            normalBus.add(vo);
        }
        else {
            commonApp.addOftenBus(childItem.getBusID());

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