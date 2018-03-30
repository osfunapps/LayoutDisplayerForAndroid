package com.osapps.layoutdisplayer.recursionsearch;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.osapps.layoutdisplayer.LayoutDisplayerService;

/**
 * Created by osapps on 30/03/2018.
 */
public class RecursionHandler {


    private final String NODE_PREFIX = "<";
    private final String NODE_SUFFIX = ">";
    private final String SLASH = "/";
    //private String parentNodePrefix = PREFIX;
    //private String childNodePrefix = PREFIX;


    private String nodeSuffix;
    private String endingParentSuffix;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void initRecursionSearch(AccessibilityNodeInfo rootView, int childNumber) {

        String childNumGap = new String(new char[childNumber]).replace("\0", "\t");
        int childCount = rootView.getChildCount();
        boolean hadChildren = childCount != 0;
        String classShortName = getClassShortName(rootView.getClassName().toString());
        String nodeName = childNumGap + NODE_PREFIX + classShortName;

        if (childCount == 0)
            nodeSuffix = SLASH + NODE_SUFFIX;
        else {
            nodeSuffix = NODE_SUFFIX;
            endingParentSuffix = childNumGap + NODE_PREFIX + SLASH + classShortName + NODE_SUFFIX;
        }

        Log.i(LayoutDisplayerService.LOG, nodeName);
        Log.i(LayoutDisplayerService.LOG, childNumGap + "content description: " + rootView.getContentDescription());


        Log.i(LayoutDisplayerService.LOG, childNumGap + "text: " + rootView.getText() + nodeSuffix);

        newLine(childNumber, childNumGap);

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        //    Log.i(TAG, repeated + "available extra data: " + rootView.getAvailableExtraData());


        for (int i = 0; i < childCount; i++)
            initRecursionSearch(rootView.getChild(i), childNumber + 1);

        if(hadChildren) {
            Log.i(LayoutDisplayerService.LOG, endingParentSuffix);
            newLine(childNumber, childNumGap);
        }

    }

    private void newLine(int childNumber, String childNumGap) {
        if (childNumber == 0)
            childNumGap = "\t";
        Log.i(LayoutDisplayerService.LOG, childNumGap + "\n");
    }

    private String getClassShortName(String className) {
        return className.substring(className.lastIndexOf(".") + 1);
    }




}
