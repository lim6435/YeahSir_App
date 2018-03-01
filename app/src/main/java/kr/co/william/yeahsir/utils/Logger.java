package kr.co.william.yeahsir.utils;

import android.util.Log;

import kr.co.william.yeahsir.AppInfo;

/**
 * Created by sheo on 2018-02-08.
 */

public class Logger {

    private static final boolean enabled = AppInfo.getInstance().isPrintLog();

    public static void v(String tag, String message) {
        if (enabled) {
            Log.v(tag, checkMessage(message));
        }
    }

    public static void d(String tag, String message) {
        if (enabled) {
            Log.d(tag, checkMessage(message));
        }
    }

    public static void i(String tag, String message) {
        if (enabled) {
            Log.i(tag, checkMessage(message));
        }
    }

    public static void w(String tag, String message) {
        if (enabled) {
            Log.w(tag, checkMessage(message));
        }
    }

    public static void e(String tag, String message) {
        if (enabled) {
            Log.e(tag, checkMessage(message));
        }
    }

    public static String checkMessage(String message) {
        if (message == null) {
            return "message is null";
        } else {
            return message;
        }
    }
}
