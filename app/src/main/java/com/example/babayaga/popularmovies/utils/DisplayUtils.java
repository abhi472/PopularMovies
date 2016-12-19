package com.example.babayaga.popularmovies.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by abhi on 12/16/16.
 */

public class DisplayUtils {

    private static DisplayUtils displayUtils;
    private  DisplayMetrics metrics;


    public static DisplayUtils getInstance(Context context)
    {
        if(displayUtils == null)
        {
            return new DisplayUtils(context);
        }
        else
            return displayUtils;
    }

    private DisplayUtils(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public float returnWidth()
    {
        return metrics.widthPixels;

    }
    public float returnHeight()
    {
        return metrics.heightPixels;
    }
    public float dpToPixel()
    {
        return (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


}
