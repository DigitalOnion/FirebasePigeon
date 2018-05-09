package com.outerspace.firebasepigeon;

import java.net.HttpCookie;
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
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_MIME = "text/html";
    private static final String HTML1 = "<html><header><style>";
    private static final String HTML2 = "</style></header><body>";
    private static final String HTML_CLOSE = "</body></html>";
    private static final String H1 = "<H1>";
    private static final String H1_CLOSE = "</H1>";
    private static final String P = "<P>";
    private static final String P_CLOSE = "</P>";
    private static final String BOLD = "<B>";
    private static final String BOLD_CLOSE = "</B>";
    private static final String LIST = "<UL>";
    private static final String LIST_CLOSE = "</UL>";
    private static final String BULLET = "<LI>";
    private static final String BULLET_CLOSE= "</LI>";

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
    protected void onStart() {
        super.onStart();
        WebView web = (WebView) findViewById(R.id.description);
        StringBuilder sb = new StringBuilder();
        sb.append(HTML1).append(getString(R.string.css_styles)).append(HTML2);
        sb.append(H1).append(getString(R.string.desc_title)).append(H1_CLOSE);
        sb.append(P).append(decorate(R.string.desc_1)).append(P_CLOSE);
        sb.append(P).append(decorateList(R.string.desc_2)).append(P_CLOSE);
        sb.append(HTML_CLOSE);

        web.loadData(sb.toString(), TEXT_MIME, null);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager
                .getInstance(getApplicationContext())
                .unregisterReceiver(pigeonBR);
        super.onDestroy();
    }

    // Utilities...
    private String decorate(int resourceId) {
        String s = getString(resourceId);
        s = s.replaceAll("(\\s)[*](\\S+)", "$1" + BOLD + "$2");
        s = s.replaceAll("(\\S)[*](\\s+)", "$1" + BOLD_CLOSE + "$2");
        return s;
    }

    private String decorateList(int resourceId) {
        String s = getString(resourceId);
        s = s.replaceAll("([*]\\s)(.*)", BULLET + "$2" + BULLET_CLOSE);
        return LIST + s + LIST_CLOSE;
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
