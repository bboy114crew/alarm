package com.thangnv.fu;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.thangnv.fu.listener.OnClickItemListViewListener;
import com.thangnv.fu.model.AlarmInfo;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.support.v4.content.ContextCompat.getColor;

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
    public View getView(final int position, View view, final ViewGroup viewGroup) {
        final ViewHolder viewHolder;
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
        Log.d(TAG, "getView: " + position);
        AlarmInfo alarmInfo = this.listData.get(position);
        viewHolder.timeAlarm.setText(alarmInfo.getTimeAlarm());
        String[] parts = alarmInfo.getTimeAlarm().split(":");
        int newHour = Integer.parseInt(parts[0]);
        if (newHour > 12){
            viewHolder.content.setText("PM");
        } else {
            viewHolder.content.setText("AM");
        }

        viewHolder.stateAlarm.setChecked(alarmInfo.isStateAlarm());
        int color = getColor(context, R.color.text_dark);
        if (!alarmInfo.isStateAlarm()) {
            color = ContextCompat.getColor(context, R.color.text_light);
        }

        viewHolder.timeAlarm.setTextColor(color);

        viewHolder.stateAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listData != null && listData.size() >= position) {
                    AlarmInfo alarmInfo = listData.get(position);
                    boolean new_status = !alarmInfo.isStateAlarm();
                    viewHolder.stateAlarm.setChecked(new_status);
                    if (mOnClickListener != null) {
                        mOnClickListener.onUpdateStatus(v, new_status, alarmInfo.getId(), position);
                    }
                }

            }

        });
        Log.d(TAG, "getView: " + viewHolder.stateAlarm.isChecked());
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
