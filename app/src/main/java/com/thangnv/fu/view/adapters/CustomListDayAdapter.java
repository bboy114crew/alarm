package com.thangnv.fu.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.thangnv.fu.R;
import com.thangnv.fu.common.Constants;
import com.thangnv.fu.listener.OnClickOptionAlarmListner;
import com.thangnv.fu.model.RealmInteger;
import com.thangnv.fu.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import static com.thangnv.fu.R.id.simpleCheckedTextView;

/**
 * Created by ll on 5/25/2017.
 */

public class CustomListDayAdapter extends BaseAdapter {
    private List<String> names;
    private Context context;
    private LayoutInflater layoutInflater;
    private String value;
    private List<RealmInteger> listDayRepeate;
    private OnClickOptionAlarmListner onClickOptionAlarmListner;
    private String dayString;
    private RealmInteger dayRepeate;


    public CustomListDayAdapter(Context context, List<String> names, List<RealmInteger> listDayRepeate, OnClickOptionAlarmListner onClickOptionAlarmListner) {
        this.context = context;
        this.names = names;
        layoutInflater = (LayoutInflater.from(context));
        this.onClickOptionAlarmListner = onClickOptionAlarmListner;
        this.listDayRepeate = listDayRepeate;
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
        if (listDayRepeate != null) {
            for (int i = 0; i < listDayRepeate.size(); i++) {
                if (position == listDayRepeate.get(i).getValue()) {
                    viewHolder.checkedTextView.setCheckMarkDrawable(R.drawable.ic_checked);
                    viewHolder.checkedTextView.setChecked(true);
                    break;
                }
            }
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataSize(names, position)) {
                    if (viewHolder.checkedTextView.isChecked()) {
                        value = "un-Checked";
                        viewHolder.checkedTextView.setCheckMarkDrawable(null);
                        viewHolder.checkedTextView.setChecked(false);
                        if (listDayRepeate != null) {
                            int check = getPosition(listDayRepeate, position);
                            if (check != -1) {
                                listDayRepeate.remove(listDayRepeate.get(check));
                            }
                        }
                    } else {
                        value = "Checked";
                            viewHolder.checkedTextView.setCheckMarkDrawable(R.drawable.ic_checked);
                            viewHolder.checkedTextView.setChecked(true);
                            dayRepeate = new RealmInteger();
                            dayRepeate.setValue(position);
                            if (listDayRepeate == null) {
                                listDayRepeate = new ArrayList<>();
                                listDayRepeate.add(dayRepeate);
                            } else {
                            int check = getPosition(listDayRepeate, position);
                            if (check == -1) {
                                listDayRepeate.add(dayRepeate);
                            }
                        }
                    }

                }
                LogUtil.d(LogUtil.TAG, value);
            }
        });
        return view;
    }

    private int getPosition(List<RealmInteger> listDayRepeate, int positionClick) {
        int check = -1;
        if (listDayRepeate != null && listDayRepeate.size() > 0) {
            for (int i = 0; i < listDayRepeate.size(); i++) {
                if (listDayRepeate.get(i).getValue() == positionClick) {
                    check = i;
                }
            }
        }
        return check;
    }

    public String getStringFromPosition(int position) {

        switch (position) {
            case Constants.MONDAY:
                dayString = "Monday";
                break;
            case Constants.TUESDAY:
                dayString = "Tuesday";
                break;
            case Constants.WEDNESDAY:
                dayString = "Wednesday";
                break;
            case Constants.THURSDAY:
                dayString = "Thursday";
                break;
            case Constants.FRIDAY:
                dayString = "Friday";
                break;
            case Constants.SATURDAY:
                dayString = "Saturday";
                break;
            case Constants.SUNDAY:
                dayString = "Sunday";
                break;
            default:
                dayString = "Never";
        }
        return dayString;
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
