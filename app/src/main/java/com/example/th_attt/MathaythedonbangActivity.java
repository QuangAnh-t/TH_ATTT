package com.example.th_attt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class    MathaythedonbangActivity extends AppCompatActivity {
    EditText vanban, khoak;
    Button giama, mahoa, back;
    TextView hienthi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mathaythedonbang);
        vanban = findViewById(R.id.vanban);
        khoak = findViewById(R.id.khoak);
        mahoa = findViewById(R.id.mahoa);
        giama = findViewById(R.id.giaima);
        back = findViewById(R.id.back);
        hienthi = findViewById(R.id.hienthi);

        mahoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vanbant = vanban.getText().toString();
                String khoakt = khoak.getText().toString();
                if (vanbant.isEmpty() || khoakt.isEmpty()) {
                    hienthi.setText("Vui lòng nhập văn bản và Khóa");
                } else if (khoakt.length() != 26) {
                    hienthi.setText("Khóa K phải có 26 ký tự");
                } else if (!khoakt.matches("[a-zA-Z]+")) {  // Kiểm tra chỉ chứa chữ cái
                    hienthi.setText("Khóa K chỉ được chứa các ký tự chữ cái (A-Z, a-z)");
                } else {
                    String ketqua = mahoa(vanbant, khoakt);
                    hienthi.setText(ketqua);
                }
            }
        });

        giama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vanbant = vanban.getText().toString();
                String khoakt = khoak.getText().toString();
                if (vanbant.isEmpty() || khoakt.isEmpty()) {
                    hienthi.setText("Vui lòng nhập văn bản và Khóa");
                } else if (khoakt.length() != 26) {
                    hienthi.setText("Khóa K phải có 26 ký tự");
                } else if (!khoakt.matches("[a-zA-Z]+")) {
                    hienthi.setText("Khóa K chỉ được chứa các ký tự chữ cái (A-Z, a-z)");
                } else {
                    String ketqua = giaMa(vanbant, khoakt);
                    hienthi.setText(ketqua);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MathaythedonbangActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public String mahoa(String vanban, String bangThayThe) {
        StringBuilder ketqua = new StringBuilder();
        for (char c : vanban.toCharArray()) {
            if (Character.isLetter(c)) {

                int index = Character.toLowerCase(c) - 'a';
                char maHoa = bangThayThe.charAt(index);
                ketqua.append(Character.isUpperCase(c) ? Character.toUpperCase(maHoa) : maHoa);
            } else {
                ketqua.append(c);
            }
        }
        return ketqua.toString();
    }

    public String giaMa(String vanban, String bangThayThe) {
        StringBuilder ketqua = new StringBuilder();
        for (char c : vanban.toCharArray()) {
            if (Character.isLetter(c)) {

                int index = bangThayThe.indexOf(Character.toLowerCase(c));
                if (index != -1) {
                    char giamMa = (char) ('a' + index);
                    ketqua.append(Character.isUpperCase(c) ? Character.toUpperCase(giamMa) : giamMa);
                }
            } else {
                ketqua.append(c);
            }
        }
        return ketqua.toString();
    }
}
