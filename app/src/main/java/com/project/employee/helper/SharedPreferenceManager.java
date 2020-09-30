package com.project.employee.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SharedPreferenceManager {

    private static SharedPreferenceManager ourInstance = new SharedPreferenceManager();
    private String defaultString = "";

    public static SharedPreferenceManager getInstance() {
        return ourInstance;
    }

    public SharedPreferenceManager() {
    }


    //Timestamp
    public static void setTimestamp(Context c, String timeStamp) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Timestamp", timeStamp);
        editor.commit();
    }

    public static String getTimestamp(Context c) {
        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(c);
        return sharedPreference.getString("Timestamp", "");
    }

}
