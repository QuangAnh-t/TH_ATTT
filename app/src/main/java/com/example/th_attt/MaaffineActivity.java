package com.example.th_attt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MaaffineActivity extends AppCompatActivity {

    private EditText nhapvanban, a, b;
    private TextView hienthi;
    Button back,mahoa,giaima;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maaffine);

        nhapvanban = findViewById(R.id.nhapvanban);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        hienthi = findViewById(R.id.hienthi);
        mahoa = findViewById(R.id.mahoa);
        giaima = findViewById(R.id.giaima);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MaaffineActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mahoa.setOnClickListener(v -> sulymahoa());
        giaima.setOnClickListener(v -> sulygiama());
    }

    private void sulymahoa() {

        String text = nhapvanban.getText().toString();
        int aValue;
        int bValue;
        if(text.isEmpty()){
            hienthi.setText("Vui Lòng Nhập Thông Tin");
            return;

        } else if (!text.matches("^[a-zA-Z ]+$")) {
            hienthi.setText("Vui Lòng Nhập Chữ Cho Văn Bản");
            return;
        }
        else {
            try {
                aValue = Integer.parseInt(a.getText().toString());
                bValue = Integer.parseInt(b.getText().toString());
            } catch (NumberFormatException e) {
                hienthi.setText("Vui lòng nhập giá trị a và b hợp lệ!(phải là số)");
                return;
            }
            StringBuilder chuoimahoa = new StringBuilder();
            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = Character.isLowerCase(c) ? 'a' : 'A';
                    int tinhchuoimahoa = (aValue * (c - base) + bValue) % 26 + base;
                    chuoimahoa.append((char) tinhchuoimahoa);
                } else {
                    chuoimahoa.append(c);
                }
            }
            hienthi.setText("Kết Quả Mã Hóa là"+chuoimahoa.toString());
        }
    }
    private void sulygiama() {
        String text = nhapvanban.getText().toString();
        int aValue;
        int bValue;
        if (text.isEmpty()) {
            hienthi.setText("Vui Lòng Nhập Thông Tin");


        } else if (!text.matches("^[a-zA-z ]+$")) {
            hienthi.setText("Vui Lòng Nhập Chữ Cho Văn Bản");

        } else {
            try {
                aValue = Integer.parseInt(a.getText().toString());
                bValue = Integer.parseInt(b.getText().toString());
            } catch (NumberFormatException e) {
                hienthi.setText("Vui lòng nhập giá trị a và b hợp lệ!(phải là số)");
                return;
            }

            StringBuilder chuoigiama = new StringBuilder();
            int nghichdao = timadao(aValue, 26);
            if (nghichdao == -1) {
                hienthi.setText("Khóa a không hợp lệ!");
                return;
            }
            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = Character.isLowerCase(c) ? 'a' : 'A';
                    int tinhchuoigiama = (nghichdao * ((c - base - bValue + 26) % 26)) % 26 + base;
                    chuoigiama.append((char) tinhchuoigiama);
                } else {
                    chuoigiama.append(c);
                }
            }
            hienthi.setText("Kết Quả Giải Hóa là" + chuoigiama.toString());
        }
    }

    private int timadao(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }
}



