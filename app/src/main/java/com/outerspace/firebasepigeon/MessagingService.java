package com.outerspace.firebasepigeon;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class MessagingService extends FirebaseMessagingService {

    public MessagingService() { }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Intent intent = new Intent();
        intent.setAction(PigeonBroadcastReceiver.ACTION_GET_NOTIFICATION);
        intent.putExtra(PigeonBroadcastReceiver.TITLE, "Hola Luis!");
        LocalBroadcastManager
                .getInstance(getApplicationContext())
                .sendBroadcast(intent);
    }
}
