package com.genix.tribalwarsnotifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        FileWriter.writeTime()

        return Result.SUCCESS
    }

}