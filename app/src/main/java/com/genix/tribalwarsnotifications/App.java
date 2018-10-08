package com.genix.tribalwarsnotifications;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context APP_CONTEXT;

    public static final String FILE_NOTIFICATIONS = "notifications.txt";
    public static final String FILE_WORKMANAGER_TIMES = "times.txt";
    public static final String TAG = "HMM";
    public static final String PREFS_FILE = "com.tribal.prefs";


    @Override
    public void onCreate() {
        super.onCreate();

        APP_CONTEXT = this;
    }

    public static Context getAppContext() {
        return APP_CONTEXT;
    }
}
