package kr.ac.jejunu.jnu_tong.detail.adapter;

import java.util.List;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public interface AdapterContract {
    interface View<E>{
        void addDatas(List<E> datas);
    }
}