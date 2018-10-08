package com.genix.tribalwarsnotifications

import android.content.Context
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class ListeningService : NotificationListenerService() {

    private val SharedWasLanuchedKey = "wasLaunched"

    override fun onCreate() {
        super.onCreate()

        openAskActivity()
        Log.d(App.TAG, "service started")
    }

    private fun openAskActivity() {
        val prefs = App.getAppContext().getSharedPreferences(App.PREFS_FILE, 0)

        if (!prefs.getBoolean(SharedWasLanuchedKey, false)) {
            startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))

            val prefsEditor = prefs.edit()
            prefsEditor.putBoolean(SharedWasLanuchedKey, true).apply()

            App.getAppContext().openFileOutput(App.FILE_WORKMANAGER_TIMES, Context.MODE_APPEND).use {
                it.write("".toByteArray())
            }
        }
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        Log.d(App.TAG, getActiveNotifications().toString() + "SA")
        Log.i(App.TAG, "new notification: " + sbn.toString())
        FileWriter.Companion.writeToFile(sbn.toString())
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {

        Log.i(App.TAG, "notification removed")
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.i(App.TAG, "Listener connected")
        Log.d(App.TAG, getActiveNotifications().toString() + "S")
    }
}