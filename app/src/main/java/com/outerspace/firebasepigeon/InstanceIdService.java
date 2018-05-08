package com.outerspace.firebasepigeon;

import java.io.IOException;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class InstanceIdService extends FirebaseInstanceIdService {
    private String preferencesKey;
    private String instanceIdKey;
    public InstanceIdService() { }

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        preferencesKey = context.getString(R.string.preferences_key);
        instanceIdKey = context.getString(R.string.instance_id_token_key);

        SharedPreferences preferences = context.getSharedPreferences(
                preferencesKey, Context.MODE_PRIVATE);
        if(!preferences.contains(instanceIdKey)) {
            try {
                FirebaseInstanceId.getInstance().deleteInstanceId();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Context context = getApplicationContext();

        SharedPreferences preferences = context.getSharedPreferences(
                preferencesKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(instanceIdKey, refreshedToken);
        editor.apply();

    }
}
