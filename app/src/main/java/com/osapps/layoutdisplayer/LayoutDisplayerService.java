package com.osapps.layoutdisplayer;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.osapps.layoutdisplayer.recursionsearch.LayoutDisplayer;
import com.osapps.layoutdisplayer.recursionsearch.SharedPrefsHandler;

/**
 * Created by OsApps on 29/12/2017.
 */

public class LayoutDisplayerService extends AccessibilityService {

    public static final String LOG = "layout displayer";

    /*todo: set package name here and that's it **/
    private static final String APP_PACKAGE_NAME = "com.whatsapp";

    private LayoutDisplayer layoutDisplayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG, "onCreate");
        layoutDisplayer = new LayoutDisplayer();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event) {
        Log.d(LOG, "Running");
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        //check if window ready
        if (rootInActiveWindow == null) return;

        //check if this is google's youtube native app
        String currentPackageName = rootInActiveWindow.getPackageName().toString();


        //check the origin of the package name
        String requestedPackageName;
        String spPackageName = SharedPrefsHandler.getInstance(getBaseContext()).getPackageName();
        if(spPackageName == null || spPackageName.equals(""))
            requestedPackageName = APP_PACKAGE_NAME;
        else
            requestedPackageName = spPackageName;


            if (!currentPackageName.equals(requestedPackageName)) return;

        layoutDisplayer.printLayout(rootInActiveWindow);

    }


    @Override
    public void onInterrupt() {
    }

}
