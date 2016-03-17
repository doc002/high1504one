package com.example.administrator.mygift.tools;

import android.util.Log;

/**
 * Created by Administrator on 16-3-15.
 */
public class LogTool {
    public static boolean DEBUG = true;

    public static void LOG_D(Class cls, String log) {
        if (DEBUG) {
            Log.d(cls.toString(), log);
        }
    }
}
