package com.outerspace.firebasepigeon;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MessagingService extends FirebaseMessagingService {

    public MessagingService() { }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        remoteMessage.
        super.onMessageReceived(remoteMessage);
    }
}
