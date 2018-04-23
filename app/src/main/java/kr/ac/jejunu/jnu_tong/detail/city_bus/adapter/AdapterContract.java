package kr.ac.jejunu.jnu_tong.detail.city_bus.adapter;

import java.util.List;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public interface AdapterContract {
    interface View<E>{
        void addItems(List<E> datas);
    }
}