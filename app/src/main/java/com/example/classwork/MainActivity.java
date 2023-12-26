package com.example.classwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.classwork.databinding.ActivityMainBinding;
import com.example.classwork.thread.Example;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UUID id;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Work in progress");

                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Log.d(TAG, "Work has ended");
            }
        });

        binding.doButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data.Builder()
                        .putString("keyA", "value1")
                        .putInt("keyB", 25)
                        .build();

                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(Example.class)
                        .setInputData(data)
                        .build();

                id = oneTimeWorkRequest.getId();

                WorkManager
                        .getInstance(getApplicationContext())
                        .enqueue(oneTimeWorkRequest);
            }
        });

        binding.getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != null) {
                    WorkManager.getInstance(getApplicationContext())
                            .getWorkInfoByIdLiveData(id)
                            .observe(MainActivity.this, new Observer<WorkInfo>() {
                                @Override
                                public void onChanged(WorkInfo workInfo) {
                                    if (workInfo != null) {
                                        Log.d(TAG, "WorkInfo state: " + workInfo.getState());

                                        String stringData = workInfo.getOutputData().getString("keyC");
                                        Log.d(TAG, "Result data: " + stringData);
                                    }
                                }
                            });
                }
            }
        });
    }
}