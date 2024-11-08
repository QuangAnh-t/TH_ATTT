package com.example.th_attt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button mathaythe,madichvong,maaffine,mavigenere,mahill,mahang,mathaythedon,marsa;
    Handler handler = new Handler();

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
        mathaythedon = findViewById(R.id.mathaythedonbang);
        marsa = findViewById(R.id.marsa);
        madichvong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                madichvong.setBackgroundColor(Color.parseColor("#D32F2F"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        madichvong.setBackgroundColor(Color.parseColor("#FF5722"));
                        Intent intent = new Intent(MainActivity.this, MadichvongActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.chuyencanh, R.anim.chuyencanh2);
                    }
                }, 500);

            }
        });
        mathaythe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mathaythe.setBackgroundColor(Color.parseColor("#D32F2F"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mathaythe.setBackgroundColor(Color.parseColor("#FFC107"));
                        Intent intent = new Intent(MainActivity.this, MathaytheActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.chuyencanh, R.anim.chuyencanh2);
                    }
                }, 500);

            }
        });
        mathaythedon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mathaythedon.setBackgroundColor(Color.parseColor("#D32F2F"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mathaythedon.setBackgroundColor(Color.parseColor("#03A9F4"));
                        Intent intent = new Intent(MainActivity.this, MathaythedonbangActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.chuyencanh, R.anim.chuyencanh2);
                    }
                }, 500);

            }
        });
        mavigenere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mavigenere.setBackgroundColor(Color.parseColor("#D32F2F"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mavigenere.setBackgroundColor(Color.parseColor("#8BC34A"));
                        Intent intent = new Intent(MainActivity.this, MavigenereActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.chuyencanh, R.anim.chuyencanh2);
                    }
                }, 500);

            }
        });
        maaffine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maaffine.setBackgroundColor(Color.parseColor("#D32F2F"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        maaffine.setBackgroundColor(Color.parseColor("#FFEB3B"));
                        Intent intent = new Intent(MainActivity.this, MaaffineActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.chuyencanh, R.anim.chuyencanh2);
                    }
                }, 500);

            }
        });
        mahill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mahill.setBackgroundColor(Color.parseColor("#D32F2F"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mahill.setBackgroundColor(Color.parseColor("#4CAF50"));
                        Intent intent = new Intent(MainActivity.this, MahillActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.chuyencanh, R.anim.chuyencanh2);
                    }
                }, 500);

            }
        });
        mahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mahang.setBackgroundColor(Color.parseColor("#D32F2F"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mahang.setBackgroundColor(Color.parseColor("#009688"));
                        Intent intent = new Intent(MainActivity.this, MahangActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.chuyencanh, R.anim.chuyencanh2);
                    }
                }, 500);

            }
        });
        marsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marsa.setBackgroundColor(Color.parseColor("#D32F2F"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        marsa.setBackgroundColor(Color.parseColor("#FF9800"));
                        Intent intent = new Intent(MainActivity.this, MarsaActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.chuyencanh, R.anim.chuyencanh2);
                    }
                }, 500);
            }
        });
    }}