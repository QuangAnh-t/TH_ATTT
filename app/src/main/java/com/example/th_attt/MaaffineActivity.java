package com.example.th_attt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MaaffineActivity extends AppCompatActivity {

    private EditText nhapvanban, a, b;
    private TextView hienthi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maaffine);

        // Khởi tạo các View
        nhapvanban = findViewById(R.id.nhapvanban);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        hienthi = findViewById(R.id.hienthi);
        Button mahoa = findViewById(R.id.mahoa);
        Button giaima = findViewById(R.id.giaima);

        // Thiết lập sự kiện cho nút Mã Hóa
        mahoa.setOnClickListener(v -> encodeAffine());

        // Thiết lập sự kiện cho nút Giải Ma
        giaima.setOnClickListener(v -> decodeAffine());
    }

    private void encodeAffine() {
        String text = nhapvanban.getText().toString();
        int aValue;
        int bValue;

        // Kiểm tra giá trị a và b
        try {
            aValue = Integer.parseInt(a.getText().toString());
            bValue = Integer.parseInt(b.getText().toString());
        } catch (NumberFormatException e) {
            hienthi.setText("Vui lòng nhập giá trị a và b hợp lệ!");
            return;
        }

        StringBuilder encoded = new StringBuilder();

        // Mã hóa từng ký tự
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int encodedChar = (aValue * (c - base) + bValue) % 26 + base;
                encoded.append((char) encodedChar);
            } else {
                encoded.append(c); // Giữ nguyên ký tự không phải chữ cái
            }
        }
        hienthi.setText(encoded.toString()); // Hiển thị kết quả mã hóa
    }

    private void decodeAffine() {
        String text = nhapvanban.getText().toString();
        int aValue;
        int bValue;

        // Kiểm tra giá trị a và b
        try {
            aValue = Integer.parseInt(a.getText().toString());
            bValue = Integer.parseInt(b.getText().toString());
        } catch (NumberFormatException e) {
            hienthi.setText("Vui lòng nhập giá trị a và b hợp lệ!");
            return;
        }

        StringBuilder decoded = new StringBuilder();
        int aInverse = modInverse(aValue, 26); // Tìm giá trị nghịch đảo của a
        if (aInverse == -1) {
            hienthi.setText("Khóa a không hợp lệ!"); // Thông báo lỗi
            return;
        }

        // Giải mã từng ký tự
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int decodedChar = (aInverse * ((c - base - bValue + 26) % 26)) % 26 + base;
                decoded.append((char) decodedChar);
            } else {
                decoded.append(c); // Giữ nguyên ký tự không phải chữ cái
            }
        }
        hienthi.setText(decoded.toString()); // Hiển thị kết quả giải mã
    }

    private int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x; // Trả về giá trị nghịch đảo
            }
        }
        return -1; // Không tồn tại giá trị nghịch đảo
    }
}