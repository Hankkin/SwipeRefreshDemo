package com.hankkin.library;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RefreshTime {
    final static String PRE_NAME = "refresh_time";
    final static String SET_FRESHTIME = "set_refresh_time";
    private static SharedPreferences preferences;
    private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static String getRefreshTime(Context context) {
        preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(SET_FRESHTIME, dateFormat.format(new Date()));
    }

    public static void setRefreshTime(Context context, Date date) {
        preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putString(SET_FRESHTIME, dateFormat.format(date));
        editor.commit();
    }

}