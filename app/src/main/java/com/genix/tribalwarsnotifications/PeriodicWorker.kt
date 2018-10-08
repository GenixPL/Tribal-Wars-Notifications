package com.genix.tribalwarsnotifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class PeriodicWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val s = SimpleDateFormat("dd-MM-yyyy-hh-mm-ss")
        val format = s.format(Date())

        FileWriter.writeToFile("new work, time:" + format.toString())

        return Result.SUCCESS
    }

}