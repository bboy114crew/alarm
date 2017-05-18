package com.thangnv.fu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.thangnv.fu.listener.OnClickItemListViewListener;
import com.thangnv.fu.model.AlarmInfo;

import java.util.List;

/**
 * Created by ThangNV28 on 5/15/2017.
 */

public class CustomListAdapter extends BaseAdapter {
    private List<AlarmInfo> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private OnClickItemListViewListener mOnClickListener;

    public CustomListAdapter(Context context, List<AlarmInfo> listData, OnClickItemListViewListener mOnClickListener) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_alarm_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.timeAlarm = (TextView) view.findViewById(R.id.tv_TimeAlarm);
            viewHolder.content = (TextView) view.findViewById(R.id.tv_Content);
            viewHolder.stateAlarm = (Switch) view.findViewById(R.id.sw_state);
            viewHolder.itemView = view.findViewById(R.id.layout_item_alarm);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        AlarmInfo alarmInfo = this.listData.get(position);
        viewHolder.timeAlarm.setText(alarmInfo.getTimeAlarm());
        viewHolder.content.setText("");
        viewHolder.stateAlarm.setChecked(alarmInfo.isStateAlarm());
        viewHolder.stateAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (viewHolder.stateAlarm.isChecked()) {
//
//                } else {
//
//                }
//                if(mOnClickListener!=null){
//                    mOnClickListener.onUpdateStatus(compoundButton,status,position);
//                }
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.OnClickItem(v, position);

                }
            }
        });
        return view;
    }

    private class ViewHolder {
        private View itemView;
        public TextView timeAlarm;
        public TextView content;
        public Switch stateAlarm;
    }
}
