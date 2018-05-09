package com.outerspace.firebasepigeon;

import java.util.Map;

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
        String keyTitle = PigeonBroadcastReceiver.TITLE;
        String keyMessagee = PigeonBroadcastReceiver.MESSAGE;

        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        String title = null;
        String message = null;
        if(data.containsKey(keyTitle))
            title = data.get(keyTitle);
        if(data.containsKey(keyMessagee))
            message = data.get(keyMessagee);

        Intent intent = new Intent();
        intent.setAction(PigeonBroadcastReceiver.ACTION_GET_NOTIFICATION);
        if(title != null)
            intent.putExtra(keyTitle, title);
        if(message != null)
            intent.putExtra(keyMessagee, message);

        LocalBroadcastManager
                .getInstance(getApplicationContext())
                .sendBroadcast(intent);
    }
}
