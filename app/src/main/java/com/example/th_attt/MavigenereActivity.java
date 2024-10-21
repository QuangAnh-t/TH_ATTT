package com.example.th_attt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MavigenereActivity extends AppCompatActivity {

    private EditText inputText, inputKey;
    private Button encryptButton, decryptButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mavigenere);

        // Ánh xạ các view
        inputText = findViewById(R.id.inputText);
        inputKey = findViewById(R.id.inputKey);
        encryptButton = findViewById(R.id.encryptButton);
        decryptButton = findViewById(R.id.decryptButton);
        resultText = findViewById(R.id.resultText);
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString().toUpperCase();
                String key = inputKey.getText().toString().toUpperCase();
                if (!text.matches("^[a-zA-Z ]+$") || !key.matches("^[a-zA-Z]+$")) {
                    Toast.makeText(MavigenereActivity.this, "Vui lòng nhập chữ cho cả văn bản và khóa.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String keyExtended = taoKhoa(text, key);
                String encryptedText = maHoa(text, keyExtended);
                resultText.setText("Văn bản đã mã hóa: " + encryptedText);
            }
        });
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString().toUpperCase();
                String key = inputKey.getText().toString().toUpperCase();
                if (!text.matches("^[a-zA-Z ]+$") || !key.matches("^[a-zA-Z]+$")) {
                    Toast.makeText(MavigenereActivity.this, "Vui lòng nhập chữ cho cả văn bản và khóa.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String keyExtended = taoKhoa(text, key);
                String decryptedText = giaiMa(text, keyExtended);
                resultText.setText("Văn bản đã giải mã: " + decryptedText);
            }
        });
    }
    private String taoKhoa(String text, String key) {
        StringBuilder keyBuilder = new StringBuilder(key);
        for (int i = 0; i < text.length() - key.length(); i++) {
            keyBuilder.append(key.charAt(i % key.length()));
        }
        return keyBuilder.toString();
    }
    private String maHoa(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = (char) (((text.charAt(i) + key.charAt(i)) % 26) + 'A');
            encryptedText.append(c);
        }
        return encryptedText.toString();
    }
    private String giaiMa(String text, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = (char) (((text.charAt(i) - key.charAt(i) + 26) % 26) + 'A');
            decryptedText.append(c);
        }
        return decryptedText.toString();
    }
}
