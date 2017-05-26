package com.thangnv.fu.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.thangnv.fu.R;
import com.thangnv.fu.listener.OnClickOptionAlarmListner;
import com.thangnv.fu.utils.LogUtil;

import java.util.List;

import static com.thangnv.fu.R.id.simpleCheckedTextView;

/**
 * Created by ll on 5/26/2017.
 */

public class CustomListSoundAdapter extends BaseAdapter {
    private List<String> names;
    private Context context;
    private LayoutInflater layoutInflater;
    private String value;
    private String ringTone;

    private OnClickOptionAlarmListner onClickOptionAlarmListner;

    public CustomListSoundAdapter(Context context, List<String> names, OnClickOptionAlarmListner onClickOptionAlarmListner) {
        this.context = context;
        this.names = names;
        layoutInflater = (LayoutInflater.from(context));
        this.onClickOptionAlarmListner = onClickOptionAlarmListner;

    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_day_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.checkedTextView = (CheckedTextView) view.findViewById(simpleCheckedTextView);
            viewHolder.itemView = view.findViewById(R.id.layout_iteam_day_alarm);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.checkedTextView.setText(names.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataSize(names, position)) {
                    if (viewHolder.checkedTextView.isChecked()) {
                        value = "un-Checked";
                        viewHolder.checkedTextView.setCheckMarkDrawable(null);
                        viewHolder.checkedTextView.setChecked(false);

                    } else {
                        value = "Checked";
                        viewHolder.checkedTextView.setCheckMarkDrawable(R.drawable.ic_checked);
                        viewHolder.checkedTextView.setChecked(true);
                        setRingTone(names.get(position));
                    }
                }
                LogUtil.d(LogUtil.TAG, value);
            }
        });
        return view;
    }

    public void setRingTone(String ringTone) {
        this.ringTone = ringTone;
    }

    private class ViewHolder {
        private View itemView;
        private CheckedTextView checkedTextView;
    }

    public boolean checkDataSize(List<String> listData, int position) {
        if (listData != null && listData.size() > position) {
            return true;
        }
        return false;
    }
}
