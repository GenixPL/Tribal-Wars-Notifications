package com.genix.tribalwarsnotifications

import android.content.Context
import android.util.Log
import java.io.File

class FileWriter {

    companion object {
        fun writeToFile(message: String) {
//        File(App.NOT_FILE).writeText(message + "\n")

            val file = File(App.getAppContext().filesDir, App.NOT_FILE)
            App.getAppContext().openFileOutput(App.NOT_FILE, Context.MODE_APPEND).use {
                it.write((message + "\n").toByteArray())
            }

            Log.d(App.TAG, "new line should be added to file")
        }
    }
}
