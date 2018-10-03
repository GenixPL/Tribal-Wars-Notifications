package com.genix.tribalwarsnotifications

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class ListeningService : NotificationListenerService() {

    override fun onCreate() {
        super.onCreate()

        openAskActivity()
    }

    private fun openAskActivity() {
        val prefs = App.getAppContext().getSharedPreferences(App.PREFS_FILE, 0)

        startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {

        Log.i(App.TAG, "new notification: " + sbn.toString())
        FileWriter().writeToFile(sbn.toString())
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {

        Log.i(App.TAG, "notification removed")
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.i(App.TAG, "Listener connected")
    }
}