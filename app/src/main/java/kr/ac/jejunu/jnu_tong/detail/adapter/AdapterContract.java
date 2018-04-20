package kr.ac.jejunu.jnu_tong.detail.adapter;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.jnu_tong.VO.DepartureBusVO;

/**
 * Created by seung-yeol on 2018. 4. 18..
 */

public interface AdapterContract {
    interface View<E>{
        void addItems(List<E> datas);
    }
}