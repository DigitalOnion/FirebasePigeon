package com.outerspace.firebasepigeon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class PigeonBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_GET_NOTIFICATION = "GET_NOTIFICATION";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final int NOTIFICATION_ID = 2;

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(TITLE);
        String message = intent.getStringExtra(MESSAGE);

        String channelId = context.getString(R.string.channel_id);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}
