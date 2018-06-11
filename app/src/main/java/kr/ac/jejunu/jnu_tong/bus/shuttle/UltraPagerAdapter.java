package kr.ac.jejunu.jnu_tong.bus.shuttle;

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

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.bus.shuttle.route.ARoute;
import kr.ac.jejunu.jnu_tong.bus.shuttle.route.BRoute;
import kr.ac.jejunu.jnu_tong.bus.shuttle.route.Route;
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
    private SparseArray< View > views = new SparseArray<>();

    UltraPagerAdapter(Context context) {
        this.context = context;
        this.route = ARoute.values();
        pagerProvider = new ArrayList<>();
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
        @SuppressLint("InflateParams")
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item_shuttle_stop, container, false);

        ImageView busStopImg = frameLayout.findViewById(R.id.img_bus_stop);
        TextView txtFirstTime = frameLayout.findViewById(R.id.txt_first_time);
        TextView txtSecondTime = frameLayout.findViewById(R.id.txt_second_time);

        BitmapUtil bitmapUtil = new BitmapUtil(context);
        bitmapUtil.loadBitmap(route[position].getImgId(), busStopImg);
        if (pagerProvider.size() != 0){
            setShuttleStopTime(position, txtFirstTime, txtSecondTime);
        }

        container.addView(frameLayout);
        views.put(position, frameLayout);
        return frameLayout;
    }

    private void setShuttleStopTime(int position, TextView txtFirstTime, TextView txtSecondTime) {
        if ( pagerProvider.get(position).getFirstTime() == null){
            txtFirstTime.setText("운행X");
        }
        else {
            txtFirstTime.setText(pagerProvider.get(position).getFirstTime() + "분전");
        }

        if (pagerProvider.get(position).getSecondTime() == null){
            txtSecondTime.setText("운행X");
        }
        else {
            txtSecondTime.setText(pagerProvider.get(position).getSecondTime() + "분전");
        }
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
        int key = 0;
        for(int i = 0; i < views.size(); i++) {
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
        Log.e("ss", "changeProvider: " + provider.size() );
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
