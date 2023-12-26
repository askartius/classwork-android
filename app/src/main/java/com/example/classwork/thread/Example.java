package com.example.classwork.thread;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class Example extends Worker {
    public static final String TAG = "Example";

    public Example(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Work in progress");

        try {
            for (int i = 0; i < 3; i++) {
                Log.d(TAG, "Doing...");

                Thread.sleep(10 * 1000);

            }
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
            return Result.failure();
        }

        return Result.success();
    }
}
