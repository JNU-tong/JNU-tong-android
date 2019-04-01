package kr.ac.jejunu.jnu_tong.expended_main.sticky_recycler;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class SimpleDiffCallback extends DiffUtil.Callback {

    private final List<Item> oldList;
    private final List<Item> newList;

    SimpleDiffCallback(List<Item> oldList, List<Item> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override public int getOldListSize() {
        return oldList.size();
    }

    @Override public int getNewListSize() {
        return newList.size();
    }

    @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(oldItemPosition, newItemPosition);
    }
}
