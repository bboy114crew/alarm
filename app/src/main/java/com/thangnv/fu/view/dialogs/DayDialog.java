package com.thangnv.fu.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thangnv.fu.R;
import com.thangnv.fu.listener.OnClickOptionAlarmListner;
import com.thangnv.fu.view.adapters.CustomListDayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ll on 5/25/2017.
 */

public class DayDialog extends Dialog implements OnClickOptionAlarmListner{
    private LinearLayout viewBackDay;
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);

    }

    List<String> listDay = Arrays.asList("Every Monday", "Every Tuesday", "Every Wednesday", "Every Thursday", "Every Friday",
                                "Every Saturday", "Every Sunday");
    public DayDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_day_repeate);
        viewBackDay = (LinearLayout) findViewById(R.id.view_back);
        // initiate a ListView
        ListView listView = (ListView) findViewById(R.id.listViewDay);
        // set the adapter to fill the data in ListView
        CustomListDayAdapter customAdapter = new CustomListDayAdapter(getContext(), listDay, DayDialog.this);
        listView.setAdapter(customAdapter);
        viewBackDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    @Override
    public void addDayRepeate(View view, ArrayList<Integer> arrayList) {

    }
}
