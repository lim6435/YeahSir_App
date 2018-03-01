package kr.co.william.yeahsir;

/**
 * Created by sheo on 2018-02-08.
 */

public class AppInfo {

    private static AppInfo appInfoInstance = null;
    private boolean isTestMode = false;
    private boolean isPrintLog = true;

    public static AppInfo getInstance() {
        if (appInfoInstance == null) {
            appInfoInstance = new AppInfo();
        }
        return appInfoInstance;
    }

    public boolean isTestMode() {
        return isTestMode;
    }

    public boolean isPrintLog() {
        return isPrintLog;
    }
}
