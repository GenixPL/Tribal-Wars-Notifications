package com.genix.tribalwarsnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG = "HMM"
    private lateinit var notManager:NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        startNotificationService()

        btn_notify.setOnClickListener { sendNotification() }
        btn_getNotifications.setOnClickListener { getNotifications() }
        btn_askForPermissions.setOnClickListener { askForPermissions() }

        val periodicRequest:PeriodicWorkRequest =
                PeriodicWorkRequest.Builder(PeriodicWorker::class.java, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
                        .build()
//        WorkManager.getInstance().enqueue(periodicRequest)
        WorkManager.getInstance().
                enqueueUniquePeriodicWork("checkEvery15min", ExistingPeriodicWorkPolicy.KEEP, periodicRequest)
    }

    /* UI methods */
    private fun sendNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notChannelId = "some_channel_id"
            val notChannelName:CharSequence = "some_channel_name"
            val notDescription = "some_channel_description"
            val notImportance = NotificationManager.IMPORTANCE_HIGH

            val notChannel = NotificationChannel(notChannelId, notChannelName, notImportance)
            notChannel.description = notDescription
            notChannel.enableLights(true)
            notChannel.lightColor = Color.RED
            notChannel.enableVibration(true)

            notManager.createNotificationChannel(notChannel)


            val notTitle = "My notification"
            val notText = "Some text"

            val notBuilder = NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle(notTitle)
                    .setContentText(notText)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setChannelId(notChannelId)

            notManager.notify(12, notBuilder.build())

        } else {
            val notTitle = "My notification"
            val notText = "Some text"

            val notBuilder = NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle(notTitle)
                    .setContentText(notText)
                    .setPriority(Notification.PRIORITY_MAX)

            notManager.notify(12, notBuilder.build())
        }

        Log.d(TAG, "Notification should be sent")
    }

    private fun askForPermissions() {
        startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
    }

    private fun getNotifications() {
        var str = ""
        var stringList = App.getAppContext().openFileInput(App.FILE_WORKMANAGER_TIMES).bufferedReader().readLines()
        stringList.forEach { str += it + "\n\n" }
        tx_notInfo.text = str
        tx_notInfo.movementMethod = ScrollingMovementMethod()
    }

    private fun startNotificationService() {
        startService(Intent(this, ListeningService::class.java))
    }


    /* Other methods */
    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
