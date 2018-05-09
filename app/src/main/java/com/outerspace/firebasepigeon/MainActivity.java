package com.outerspace.firebasepigeon;

import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private PigeonBroadcastReceiver pigeonBR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pigeonBR = new PigeonBroadcastReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(PigeonBroadcastReceiver.ACTION_GET_NOTIFICATION);
        LocalBroadcastManager
                .getInstance(getApplicationContext())
                .registerReceiver(pigeonBR, filter);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager
                .getInstance(getApplicationContext())
                .unregisterReceiver(pigeonBR);
        super.onDestroy();
    }

    // UI Callbacks...
    //
    public void onClickBtnShowToken(View view) {
        String preferencesKey = getString(R.string.preferences_key);
        String instanceIdKey = getString(R.string.instance_id_token_key);
        String noKeyFound = getString(R.string.no_key_found);

        SharedPreferences preferences = getSharedPreferences(
                preferencesKey, Context.MODE_PRIVATE);

        String instanceKey = preferences.getString(instanceIdKey, noKeyFound);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.fb_instance_key).setMessage(instanceKey).create().show();
    }

    public void onClickBtnTestIt(View view) {
        String title = "Hola";
        String message = "Hola Luis!";
        Context context = this.getBaseContext();

        String channelId = context.getString(R.string.channel_id);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(2, builder.build());
    }

}
