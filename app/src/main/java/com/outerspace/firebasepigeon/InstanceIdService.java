package com.outerspace.firebasepigeon;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import android.content.Context;
import android.content.SharedPreferences;

public class InstanceIdService extends FirebaseInstanceIdService {

    public InstanceIdService() { }

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Context context = getApplicationContext();

        String preferencesKey = context.getString(R.string.preferences_key);
        String instanceIdKey = context.getString(R.string.instance_id_token_key);

        SharedPreferences preferences = context.getSharedPreferences(
                preferencesKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(instanceIdKey, refreshedToken);
        editor.apply();
    }
}
