package com.osapps.layoutdisplayer.recursionsearch;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.osapps.layoutdisplayer.LayoutDisplayerService;

/**
 * Created by osapps on 30/03/2018.
 */

public class LayoutDisplayer {

    private RecursionHandler recursionHandler;

    public LayoutDisplayer() {
        this.recursionHandler = new RecursionHandler();
    }


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void printLayout(AccessibilityNodeInfo rootInActiveWindow) {
            logRunMark();
            recursionHandler.initRecursionSearch(rootInActiveWindow,0);
            logRunMark();

        }


    private void logRunMark() {
        Log.i(LayoutDisplayerService.LOG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
