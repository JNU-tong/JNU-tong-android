package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.detail.shuttle_bus.route.ARoute;
import kr.ac.jejunu.jnu_tong.detail.shuttle_bus.route.BRoute;
import kr.ac.jejunu.jnu_tong.detail.shuttle_bus.route.Route;
import kr.ac.jejunu.jnu_tong.task.BitmapUtil;
import kr.ac.jejunu.jnu_tong.vo.ShuttleVO;

/**
 * Created by seung-yeol on 2018. 4. 22..
 */

public class UltraPagerAdapter extends PagerAdapter
        implements PagerAdapterContract.View, PagerAdapterContract.Model{
    private List<ShuttleVO> pagerProvider;
    private Route[] route;
    private Context context;
    SparseArray< View > views = new SparseArray<>();

    UltraPagerAdapter(Context context) {
        this.context = context;
        this.route = ARoute.values();
        pagerProvider = new ArrayList<>();
    }

    @Override
    public int getCount() {
        Log.e("sd", "getCount: " + pagerProvider.size());
        return route.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        @SuppressLint("InflateParams")
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item_shuttle_stop, container, false);

        ImageView busStopImg = frameLayout.findViewById(R.id.img_bus_stop);
        TextView txtFirstTime = frameLayout.findViewById(R.id.txt_first_time);
        TextView txtSecondTime = frameLayout.findViewById(R.id.txt_second_time);

        BitmapUtil bitmapUtil = new BitmapUtil(context);
        bitmapUtil.loadBitmap(route[position].getImgId(), busStopImg);
        if (pagerProvider.size() != 0){
            initShuttleTime(position, txtFirstTime, txtSecondTime);
        }

        container.addView(frameLayout);
        views.put(position, frameLayout);
        return frameLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        FrameLayout layout = (FrameLayout) object;
        container.removeView(layout);
        views.remove(position);
        layout = null;
        System.gc();
    }

    @Override
    public void notifyDataSetChanged() {
        int key;
        for(int i = 0; i < views.size(); i++) {
            key = views.keyAt(i);
            View view = views.get(key);

            TextView txtFirstTime = view.findViewById(R.id.txt_first_time);
            TextView txtSecondTime = view.findViewById(R.id.txt_second_time);

            initShuttleTime(key, txtFirstTime, txtSecondTime);
        }
        super.notifyDataSetChanged();
    }

    private void initShuttleTime(int key, TextView txtFirstTime, TextView txtSecondTime) {
        if ( pagerProvider.get(key).getFirstTime() == null){
            txtFirstTime.setText("운행X");
        }
        else {
            txtFirstTime.setText(pagerProvider.get(key).getFirstTime() + "분전");
        }

        if (pagerProvider.get(key).getSecondTime() == null){
            txtSecondTime.setText("운행X");
        }
        else {
            txtSecondTime.setText(pagerProvider.get(key).getSecondTime() + "분전");
        }
    }


    @Override
    public String[] getBusStopNames(int position) {
        String[] busStopNames = new String[3];

        if (position > 0) {
            busStopNames[0] = route[position-1].getTitle();
        } else {
            busStopNames[0] = "";
        }

        busStopNames[1] = route[position].getTitle();

        if (position < route.length - 1) {
            busStopNames[2] = route[position+1].getTitle();
        } else {
            busStopNames[2] = "";
        }

        return busStopNames;
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
        pagerProvider = new ArrayList<>(provider);
    }

    @Override
    public void notifyDataChange() {
        notifyDataSetChanged();
    }
}
