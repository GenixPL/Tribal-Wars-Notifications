package com.genix.tribalwarsnotifications

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

class FileWriter {

    companion object {
        fun writeToFile(message: String) {
            App.getAppContext().openFileOutput(App.FILE_NOTIFICATIONS, Context.MODE_APPEND).use {
                it.write((message + "\n").toByteArray())
            }
        }

        fun writeTime() {
            val s = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
            val format = s.format(Date())

            App.getAppContext().openFileOutput(App.FILE_WORKMANAGER_TIMES, Context.MODE_APPEND).use {
                it.write((format + "\n").toByteArray())
            }
        }
    }
}
