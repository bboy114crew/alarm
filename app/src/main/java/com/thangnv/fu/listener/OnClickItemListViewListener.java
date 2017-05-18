package com.thangnv.fu.listener;

import android.view.View;

/**
 * Created by ll on 5/17/2017.
 */

public interface OnClickItemListViewListener {

    void OnClickItem(View mView, int position);
    void onUpdateStatus(View mView, boolean status,int position);
}
