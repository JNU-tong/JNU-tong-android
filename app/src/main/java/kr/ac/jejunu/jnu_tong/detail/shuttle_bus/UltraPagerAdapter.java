package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.R;

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
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item_shuttle_stop, container, false);

        ImageView busStopImg = linearLayout.findViewById(R.id.img_bus_stop);
        Drawable drawable = context.getResources().getDrawable(pagerProvider.get(position).getImgId());
        busStopImg.setBackground(drawable);

        container.addView(linearLayout);

        return linearLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        LinearLayout layout = (LinearLayout) object;
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
