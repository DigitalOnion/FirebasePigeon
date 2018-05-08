package com.outerspace.firebasepigeon;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
