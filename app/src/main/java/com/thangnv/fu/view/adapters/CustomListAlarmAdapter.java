package com.thangnv.fu.view.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.thangnv.fu.R;
import com.thangnv.fu.listener.OnClickItemListViewListener;
import com.thangnv.fu.model.AlarmInfo;
import com.thangnv.fu.utils.LogUtil;

import java.util.List;

import static android.content.ContentValues.TAG;
import static android.support.v4.content.ContextCompat.getColor;

/**
 * Created by ThangNV28 on 5/15/2017.
 */

public class CustomListAlarmAdapter extends BaseAdapter {
    private List<AlarmInfo> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private OnClickItemListViewListener onClickItemListViewListener;

    public CustomListAlarmAdapter(Context context, List<AlarmInfo> listData, OnClickItemListViewListener onClickItemListViewListener) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.onClickItemListViewListener = onClickItemListViewListener;
    }

    @Override
    public int getCount() {
        if (checkDataEmpty(listData)) {
            return listData.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {

        if (checkDataSize(listData, position)) {
            return listData.get(position);
        } else
            return null;
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
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        AlarmInfo alarmInfo = this.listData.get(position);
        viewHolder.timeAlarm.setText(alarmInfo.getTimeAlarm());
        String[] parts = alarmInfo.getTimeAlarm().split(":");
        int newHour = Integer.parseInt(parts[0]);
        if (newHour > 12) {
            viewHolder.tvState.setText("PM");
        } else {
            viewHolder.tvState.setText("AM");
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
                if (checkDataSize(listData,position)) {
                    AlarmInfo alarmInfo = listData.get(position);
                    LogUtil.d(TAG, "switch " + alarmInfo.isStateAlarm() + " to " + !alarmInfo.isStateAlarm());
                    boolean new_status = !alarmInfo.isStateAlarm();
                    viewHolder.stateAlarm.setChecked(new_status);
                    if (onClickItemListViewListener != null) {
                        onClickItemListViewListener.onUpdateStatus(v, new_status, alarmInfo.getId(), position);
                    }
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemListViewListener != null) {
                    onClickItemListViewListener.OnClickItem(v, position);
                    LogUtil.d(TAG, "Click to " + v + position);
                }
            }
        });
        return view;
    }

    public boolean checkDataEmpty(List<AlarmInfo> listData) {
        if (listData != null) {
            return true;
        }
        return false;
    }

    public boolean checkDataSize(List<AlarmInfo> listData, int position) {
        if (listData != null && listData.size() > position) {
            return true;
        }
        return false;
    }

    private class ViewHolder {
        private View itemView;
        private TextView timeAlarm;
        private TextView tvState;
        private Switch stateAlarm;
        public ViewHolder (View view){
            this.timeAlarm = (TextView) view.findViewById(R.id.tv_TimeAlarm);
            this.tvState = (TextView) view.findViewById(R.id.tv_State);
            this.stateAlarm = (Switch) view.findViewById(R.id.sw_state);
            this.itemView = view.findViewById(R.id.layout_item_alarm);
        }
    }
}
