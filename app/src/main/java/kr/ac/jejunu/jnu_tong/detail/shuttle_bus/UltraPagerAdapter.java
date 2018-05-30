package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;
import kr.ac.jejunu.jnu_tong.task.BitmapUtil;

/**
 * Created by seung-yeol on 2018. 4. 22..
 */

public class UltraPagerAdapter extends PagerAdapter implements PagerAdapterContract.View, PagerAdapterContract.Model {
    private List<PagerProvider> pagerProvider;
    private Context context;

    UltraPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pagerProvider.size();
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

        BitmapUtil bitmapUtil = new BitmapUtil(context);
        bitmapUtil.loadBitmap(pagerProvider.get(position).getImgId(), busStopImg);

        container.addView(frameLayout);
        return frameLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        FrameLayout layout = (FrameLayout) object;
        container.removeView(layout);

        System.gc();
    }

    @Override
    public void setProviders(ArrayList<PagerProvider> providers) {
        pagerProvider = providers;
        notifyDataSetChanged();
    }

    @Override
    public String[] getBusStopNames(int position) {
        String[] busStopNames = new String[3];

        if (position > 0) {
            busStopNames[0] = pagerProvider.get(position - 1).getTitle();
        } else {
            busStopNames[0] = "";
        }

        busStopNames[1] = pagerProvider.get(position).getTitle();

        if (position < pagerProvider.size() - 1) {
            busStopNames[2] = pagerProvider.get(position + 1).getTitle();
        } else {
            busStopNames[2] = "";
        }


        return busStopNames;
    }
}
