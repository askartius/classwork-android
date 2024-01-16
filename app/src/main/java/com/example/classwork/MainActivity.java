package com.example.classwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.classwork.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.redColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.canvas.color = getResources().getColor(R.color.red);
                binding.canvas.invalidate();
            }
        });

        binding.greenColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.canvas.color = getResources().getColor(R.color.green);
                binding.canvas.invalidate();
            }
        });

        binding.blueColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.canvas.color = getResources().getColor(R.color.blue);
                binding.canvas.invalidate();
            }
        });
    }
}