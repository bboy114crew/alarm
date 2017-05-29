package com.thangnv.fu.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thangnv.fu.R;
import com.thangnv.fu.listener.OnClickOptionAlarmListner;
import com.thangnv.fu.view.adapters.CustomListSoundAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ll on 5/25/2017.
 */

public class SoundDialog extends Dialog implements OnClickOptionAlarmListner{
    private LinearLayout viewBackSound;

    List<String> ringToneName = new ArrayList<>(getNotifications().keySet());

    public SoundDialog(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_sound_alarm);

        viewBackSound = (LinearLayout) findViewById(R.id.view_back);
        // initiate a ListView

        ListView listView = (ListView) findViewById(R.id.listViewSound);

        // set the adapter to fill the data in ListView
        CustomListSoundAdapter customListSoundAdapter = new CustomListSoundAdapter(getContext(), ringToneName, SoundDialog.this);
        listView.setAdapter(customListSoundAdapter);
        listView.setChoiceMode (ListView.CHOICE_MODE_SINGLE);
        viewBackSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public Map<String, String> getNotifications() {
        RingtoneManager manager = new RingtoneManager(getContext());
        manager.setType(RingtoneManager.TYPE_RINGTONE);
        Cursor cursor = manager.getCursor();

        Map<String, String> list = new HashMap<>();
        while (cursor.moveToNext()) {
            String notificationTitle = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
            String notificationUri = cursor.getString(RingtoneManager.URI_COLUMN_INDEX);

            list.put(notificationTitle, notificationUri);
        }

        return list;
    }


    @Override
    public void addDayRepeate(View view, HashMap<String, Boolean> dayRepeate) {

    }

    @Override
    public boolean[] getListDayRepeate() {
        return new boolean[0];
    }

}
