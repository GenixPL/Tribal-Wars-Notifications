package com.genix.tribalwarsnotifications

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class ListeningService : NotificationListenerService() {

    private val TAG = this::class as String

    override fun onNotificationPosted(sbn: StatusBarNotification?) {

        Log.i(TAG, "new notification")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        Log.i(TAG, "notification removed")
    }
}