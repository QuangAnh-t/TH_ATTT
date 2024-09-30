package com.example.th_attt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MadichvongActivity extends AppCompatActivity {

    EditText vanban, khoak;
    Button mahoa, giaima,back;
    TextView result; // Thêm biến TextView để hiển thị kết quả

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.madichvong);

        // Ánh xạ các thành phần
        vanban = findViewById(R.id.vanban);
        khoak = findViewById(R.id.khoak);
        mahoa = findViewById(R.id.mahoa);
        giaima = findViewById(R.id.giaima);
        result = findViewById(R.id.result);
        back = findViewById(R.id.back);
        mahoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = vanban.getText().toString();
                String kText = khoak.getText().toString();

                if (!text.isEmpty() && !kText.isEmpty()) {
                    try {
                        int k = Integer.parseInt(kText);
                        String encryptedText = maHoa(text, k);
                        result.setText("Mã hóa: " + encryptedText); // Đẩy kết quả xuống TextView
                    } catch (NumberFormatException e) {
                        Toast.makeText(MadichvongActivity.this, "Khóa K phải là một số", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MadichvongActivity.this, "Vui lòng nhập văn bản và khóa K", Toast.LENGTH_SHORT).show();
                }
            }
        });

        giaima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = vanban.getText().toString();
                String kText = khoak.getText().toString();

                if (!text.isEmpty() && !kText.isEmpty()) {
                    try {
                        int k = Integer.parseInt(kText);
                        String decryptedText = giaiMa(text, k);
                        result.setText("Giải mã: " + decryptedText); // Đẩy kết quả xuống TextView
                    } catch (NumberFormatException e) {
                        Toast.makeText(MadichvongActivity.this, "Khóa K phải là một số", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MadichvongActivity.this, "Vui lòng nhập văn bản và khóa K", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MadichvongActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String maHoa(String text, int k) {
        StringBuilder result = new StringBuilder();
        int adjustedK = (k % 26 + 26) % 26;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + adjustedK) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    private String giaiMa(String text, int k) {
        return maHoa(text, 26 - (k % 26));
    }
}
