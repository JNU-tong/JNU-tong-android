package kr.ac.jejunu.jnu_tong.main;

import java.util.List;

import kr.ac.jejunu.jnu_tong.vo.DepartureBusVO;
import kr.ac.jejunu.jnu_tong.main.sticky_recycler.Item;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public interface MainAdapterContract {
    interface View<E>{
        void setItems(List<E> datas);
    }

    interface Model{
        List<Item> taskResultItems(List<DepartureBusVO> departureBusVOS);

        void goDetailActivity(int position);

        void heartClick(int position);
    }
}