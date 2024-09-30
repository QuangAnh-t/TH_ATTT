package com.example.th_attt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button mathaythe,madichvong,maaffine,mavigenere,mahill,mahang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mathaythe = findViewById(R.id.mathaythe);
        madichvong = findViewById(R.id.madichvong);
        maaffine = findViewById(R.id.maaffine);
        mavigenere = findViewById(R.id.mavigenere);
        mahill = findViewById(R.id.mahill);
        mahang = findViewById(R.id.mahang);
        mathaythe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MathaytheActivity.class);
                startActivity(intent);
            }
        });
        madichvong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MadichvongActivity.class);
                startActivity(intent);
            }
        });
        maaffine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MaaffineActivity.class);
                startActivity(intent);
            }
        });
        mavigenere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MavigenereActivity.class);
                startActivity(intent);
            }
        });
        mahill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MahillActivity.class);
                startActivity(intent);
            }
        });
        mahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MahangActivity.class);
                startActivity(intent);
            }
        });
    }

}