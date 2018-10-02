package com.genix.tribalwarsnotifications

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class ListeningService : NotificationListenerService() {

    private val TAG = "HMM"

    override fun onCreate() {
        super.onCreate()
        startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))

    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {

        Log.i(TAG, "new notification: " + sbn.toString())
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {

        Log.i(TAG, "notification removed")
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.i(TAG, "Listener connected")
    }
}