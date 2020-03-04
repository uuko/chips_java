package com.example.chips_java;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MyDiffUtilCallback extends DiffUtil.Callback {
    public MyDiffUtilCallback(List<Contact> oldList, List<Contact> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    private List<Contact> oldList;
   private List<Contact> newList;
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getName().equals(newList.get(newItemPosition).getName());
    }

//    @Override
//    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
//    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Contact beanOld = oldList.get(oldItemPosition);
        Contact beanNew = newList.get(newItemPosition);
        if (beanOld.getPinId()!=(beanNew.getPinId())) {
            return false;//如果有内容不同，就返回false
        }
        return true; //默认两个data内容是相同的



    }

}
