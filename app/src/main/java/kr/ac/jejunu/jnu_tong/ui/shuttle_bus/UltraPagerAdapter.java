package kr.ac.jejunu.jnu_tong.ui.shuttle_bus;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.data.vo.ShuttleVO;
import kr.ac.jejunu.jnu_tong.ui.shuttle_bus.route.ARoute;
import kr.ac.jejunu.jnu_tong.ui.shuttle_bus.route.BRoute;
import kr.ac.jejunu.jnu_tong.ui.shuttle_bus.route.Route;
import kr.ac.jejunu.jnu_tong.util.BitmapUtil;

/**
 * Created by seung-yeol on 2018. 4. 22..
 */

public class UltraPagerAdapter extends PagerAdapter
        implements PagerAdapterContract.View, PagerAdapterContract.Model {
    private List<ShuttleVO> pagerProvider;
    private Route[] route;
    private SparseArray<View> views;
    private BitmapUtil bitmapUtil;

    @Inject
    UltraPagerAdapter(BitmapUtil bitmapUtil) {
        this.route = ARoute.values();
        this.bitmapUtil = bitmapUtil;
        pagerProvider = new ArrayList<>();
        views = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return route.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item_shuttle_stop, container, false);

        ImageView busStopImg = frameLayout.findViewById(R.id.img_bus_stop);
        TextView txtFirstTime = frameLayout.findViewById(R.id.txt_first_time);
        TextView txtSecondTime = frameLayout.findViewById(R.id.txt_second_time);

        bitmapUtil.loadBitmap(route[position].getImgId(), busStopImg);
        if (pagerProvider.size() != 0) {
            setShuttleStopTime(position, txtFirstTime, txtSecondTime);
        }

        container.addView(frameLayout);
        views.put(position, frameLayout);
        return frameLayout;
    }

    private void setShuttleStopTime(int position, TextView txtFirstTime, TextView txtSecondTime) {
        Integer firstTime = pagerProvider.get(position).getRemainTime().getFirstTime();
        Integer secondTime = pagerProvider.get(position).getRemainTime().getSecondTime();

        if (firstTime == null) {
            txtFirstTime.setText("운행X");
        } else {
            txtFirstTime.setText(firstTime + "분전");
        }

        if (secondTime == null) {
            txtSecondTime.setText("운행X");
        } else {
            txtSecondTime.setText(secondTime + "분전");
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        FrameLayout layout = (FrameLayout) object;
        container.removeView(layout);
        views.remove(position);
        layout = null;
    }

    @Override
    public void notifyDataSetChanged() {
        int key = 0;
        for (int i = 0; i < views.size(); i++) {
            key = views.keyAt(i);
            View view = views.get(key);

            TextView txtFirstTime = view.findViewById(R.id.txt_first_time);
            TextView txtSecondTime = view.findViewById(R.id.txt_second_time);
            setShuttleStopTime(key, txtFirstTime, txtSecondTime);
        }
        super.notifyDataSetChanged();
    }


    @Override
    public String[] getBusStopNames(int position) {
        String[] busStopNames = new String[3];

        if (position > 0) {
            busStopNames[0] = route[position - 1].getTitle();
        } else {
            busStopNames[0] = "";
        }

        busStopNames[1] = route[position].getTitle();

        if (position < route.length - 1) {
            busStopNames[2] = route[position + 1].getTitle();
        } else {
            busStopNames[2] = "";
        }

        return busStopNames;
    }

    @Override
    public String getBusStopNameById(int id) {
        return ARoute.values()[id - 1].getTitle();
    }

    @Override
    public void selectARoute() {
        route = ARoute.values();
    }

    @Override
    public void selectBRoute() {
        route = BRoute.values();
    }

    @Override
    public void changeProvider(List<ShuttleVO> provider) {
        Log.e("ss", "changeProvider: " + provider.size());
        pagerProvider = new ArrayList<>(provider);
        notifyDataSetChanged();
    }

    @Override
    public int getBusStopId(int position) {
        return route[position].getId();
    }

    @Override
    public void notifyDataChange() {
        notifyDataSetChanged();
    }
}
