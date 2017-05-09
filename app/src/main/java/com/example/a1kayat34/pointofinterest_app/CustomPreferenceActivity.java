package com.example.a1kayat34.pointofinterest_app;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class CustomPreferenceActivity extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
