package com.example.th_attt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MahillActivity extends AppCompatActivity {

    private EditText nhapvanban;
    private EditText nhapkhoa;
    private TextView ketqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mahill); // Layout XML của bạn

        // Thiết lập padding cho view chính để tương thích với hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo các view
        nhapvanban = findViewById(R.id.nhapvanban);
        nhapkhoa = findViewById(R.id.nhapkhoa);
        ketqua = findViewById(R.id.ketqua);

        // Nút mã hóa
        Button mahoa = findViewById(R.id.mahoa);
        mahoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEncryptButtonClick();
            }
        });

        // Nút giải mã
        Button giaima = findViewById(R.id.giaima);
        giaima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDecryptButtonClick();
            }
        });
    }

    private void onEncryptButtonClick() {
        String text = nhapvanban.getText().toString();
        String key = nhapkhoa.getText().toString();

        // Kiểm tra tính hợp lệ của đầu vào
        if (!isValidInput(text, key)) {
            return; // Nếu không hợp lệ, dừng lại
        }

        int[][] keyMatrix = convertKeyToMatrix(key);
        String encryptedText = encrypt(text, keyMatrix);
        ketqua.setText("Kết quả mã hóa: " + encryptedText);
    }

    private void onDecryptButtonClick() {
        String text = nhapvanban.getText().toString();
        String key = nhapkhoa.getText().toString();

        // Kiểm tra tính hợp lệ của đầu vào
        if (!isValidInput(text, key)) {
            return; // Nếu không hợp lệ, dừng lại
        }

        int[][] keyMatrix = convertKeyToMatrix(key);
        String decryptedText = decrypt(text, keyMatrix);
        ketqua.setText("Kết quả giải mã: " + decryptedText);
    }

    private boolean isValidInput(String text, String key) {
        // Kiểm tra nếu văn bản rỗng
        if (text.isEmpty() || key.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập văn bản và khóa!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Kiểm tra xem khóa có phải là số nguyên hợp lệ không
        if (!isNumeric(key)) {
            Toast.makeText(this, "Khóa chỉ được nhập số nguyên!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private int[][] convertKeyToMatrix(String key) {
        // Tính kích thước ma trận từ độ dài của khóa
        int n = (int) Math.sqrt(key.length());
        int[][] keyMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Chuyển đổi ký tự số thành số nguyên
                keyMatrix[i][j] = Character.getNumericValue(key.charAt(i * n + j));
            }
        }
        return keyMatrix;
    }

    private String encrypt(String text, int[][] keyMatrix) {
        // Thực hiện mã hóa văn bản dựa trên ma trận khóa
        // Cần thêm mã thực sự để mã hóa theo thuật toán Hill
        return "Văn bản đã được mã hóa"; // Thay đổi bằng mã mã hóa thực tế
    }

    private String decrypt(String text, int[][] keyMatrix) {
        // Thực hiện giải mã văn bản dựa trên ma trận khóa
        // Cần thêm mã thực sự để giải mã theo thuật toán Hill
        return "Văn bản đã được giải mã"; // Thay đổi bằng mã giải mã thực tế
    }
}
