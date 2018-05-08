package com.outerspace.firebasepigeon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PigeonBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_GET_NOTIFICATION = "GET_NOTIFICATION";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Receving notification", Toast.LENGTH_SHORT).show();
    }
}
