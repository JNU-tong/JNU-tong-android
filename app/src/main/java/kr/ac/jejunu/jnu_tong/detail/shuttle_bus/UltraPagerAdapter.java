package kr.ac.jejunu.jnu_tong.detail.shuttle_bus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import kr.ac.jejunu.jnu_tong.R;

/**
 * Created by seung-yeol on 2018. 4. 22..
 */

public class UltraPagerAdapter extends PagerAdapter implements PagerAdapterContract.View, PagerAdapterContract.Model{
    private List<PagerProvider> pagerProvider;
    private Context context;

    UltraPagerAdapter(Context context, List<PagerProvider> pagerProvider){
        this.context = context;
        this.pagerProvider = pagerProvider;
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
        //new LinearLayout(container.getContext());

        busStopImg.setBackground(context.getResources().getDrawable(pagerProvider.get(position).getImgId()));

        container.addView(linearLayout);
//        linearLayout.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, container.getContext().getResources().getDisplayMetrics());
//        linearLayout.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, container.getContext().getResources().getDisplayMetrics());
        return linearLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
}
