package com.osapps.layoutdisplayer.recursionsearch;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by osapps on 30/03/2018.
 */
public class SharedPrefsHandler {

    private static final String PREFS_NAME = "layout_service";
    private static final String PACKAGE_NAME = "package_name";

    public static SharedPrefsHandler instance;
    private static SharedPreferences prefs;


    private SharedPrefsHandler(){}

    public static SharedPrefsHandler getInstance(Context context){
        if(instance == null) {
            instance = new SharedPrefsHandler();
            prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
        return instance;
    }

    public void saveNewPackageName(String newPackageName){
        prefs.edit().putString(PACKAGE_NAME, newPackageName).apply();
    }

    public String getPackageName(){
        return prefs.getString(PACKAGE_NAME,"");

    }
}
