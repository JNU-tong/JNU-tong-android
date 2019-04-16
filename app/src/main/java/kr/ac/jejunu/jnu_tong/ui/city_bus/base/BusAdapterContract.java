package kr.ac.jejunu.jnu_tong.ui.city_bus.base;

import java.util.List;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public interface BusAdapterContract {
    interface View<E>{
        void addItems(List<E> datas);
    }
}