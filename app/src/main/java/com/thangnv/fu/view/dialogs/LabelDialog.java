package com.thangnv.fu.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.thangnv.fu.R;

/**
 * Created by ll on 5/25/2017.
 */

public class LabelDialog extends Dialog {
    private LinearLayout viewBack;
    private EditText etLabel;

    public LabelDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_label_alarm);
        viewBack = (LinearLayout) findViewById(R.id.view_back);
        etLabel = (EditText) findViewById(R.id.txt_label);
        viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
