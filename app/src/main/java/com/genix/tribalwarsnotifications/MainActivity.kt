package com.genix.tribalwarsnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startNotificationService()

        btn_notify.setOnClickListener { sendNotification() }
    }

    private fun sendNotification() {
        val notManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val NotificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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

        Log.i(TAG, "Notification should be sent")
    }

    private fun startNotificationService() {
//        val service = ListeningService()
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
