package com.example.th_attt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MathaytheActivity extends AppCompatActivity {

    TextView textViewCode;
    EditText vanban;
    EditText khoak;
    Button mahoa;
    Button giaima;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mathaythe);

        // Ánh xạ các thành phần giao diện
        vanban = findViewById(R.id.vanban);
        khoak = findViewById(R.id.khoak);
        textViewCode = findViewById(R.id.textViewCode);
        mahoa = findViewById(R.id.mahoa);
        giaima = findViewById(R.id.giaima);

        // Xử lý sự kiện khi nhấn nút mã hóa
        mahoa.setOnClickListener(v -> {
            String text = vanban.getText().toString();
            String key = khoak.getText().toString();

            if (text.isEmpty() || key.isEmpty()) {
                textViewCode.setText("Vui lòng nhập khóa mã hóa và văn bản.");
            } else {
                // Khởi tạo PlayfairCipher với khóa
                PlayfairCipher playfairCipher = new PlayfairCipher(key);

                // Mã hóa văn bản
                String encryptedText = playfairCipher.encrypt(text);

                // Hiển thị kết quả
                textViewCode.setText("Văn bản đã mã hóa: " + encryptedText);
            }
        });

        // Xử lý sự kiện khi nhấn nút giải mã
        giaima.setOnClickListener(v -> {
            String text = vanban.getText().toString();
            String key = khoak.getText().toString();

            if (key.isEmpty() || text.isEmpty()) {
                textViewCode.setText("Vui lòng nhập khóa mã hóa và văn bản.");
            } else {
                // Khởi tạo PlayfairCipher với khóa
                PlayfairCipher playfairCipher = new PlayfairCipher(key);

                // Giải mã văn bản
                String decryptedText = playfairCipher.decrypt(text);

                // Hiển thị kết quả
                textViewCode.setText("Văn bản đã giải mã: " + decryptedText);
            }
        });
    }

    public class PlayfairCipher {
        private String key;
        private char[][] matrix = new char[5][5];

        public PlayfairCipher(String key) {
            this.key = key;
            createMatrix();
        }

        private void createMatrix() {
            String keyString = removeDuplicates(key + "abcdefghijklmnopqrstuvwxyz");
            keyString = keyString.replace('j', 'i');

            boolean[] used = new boolean[26];
            int k = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    while (used[keyString.charAt(k) - 'a']) {
                        k++;
                    }
                    matrix[i][j] = keyString.charAt(k);
                    used[keyString.charAt(k) - 'a'] = true;
                    k++;
                }
            }
        }

        private String removeDuplicates(String str) {
            StringBuilder sb = new StringBuilder();
            boolean[] seen = new boolean[26];
            for (char c : str.toCharArray()) {
                if (!seen[c - 'a']) {
                    sb.append(c);
                    seen[c - 'a'] = true;
                }
            }
            return sb.toString();
        }

        public String encrypt(String text) {
            text = vanbandaduocxuly(text);
            StringBuilder encryptedText = new StringBuilder();

            for (int i = 0; i < text.length(); i += 2) {
                char a = text.charAt(i);
                char b = text.charAt(i + 1);
                int[] posA = findPosition(a);
                int[] posB = findPosition(b);

                if (posA[0] == posB[0]) {
                    encryptedText.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                    encryptedText.append(matrix[posB[0]][(posB[1] + 1) % 5]);
                } else if (posA[1] == posB[1]) {
                    encryptedText.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                    encryptedText.append(matrix[(posB[0] + 1) % 5][posB[1]]);
                } else {
                    encryptedText.append(matrix[posA[0]][posB[1]]);
                    encryptedText.append(matrix[posB[0]][posA[1]]);
                }
            }
            return encryptedText.toString();
        }

        public String decrypt(String text) {
            StringBuilder decryptedText = new StringBuilder();

            for (int i = 0; i < text.length(); i += 2) {
                char a = text.charAt(i);
                char b = text.charAt(i + 1);
                int[] posA = findPosition(a);
                int[] posB = findPosition(b);

                if (posA[0] == posB[0]) {
                    decryptedText.append(matrix[posA[0]][(posA[1] + 4) % 5]);
                    decryptedText.append(matrix[posB[0]][(posB[1] + 4) % 5]);
                } else if (posA[1] == posB[1]) {
                    decryptedText.append(matrix[(posA[0] + 4) % 5][posA[1]]);
                    decryptedText.append(matrix[(posB[0] + 4) % 5][posB[1]]);
                } else {
                    decryptedText.append(matrix[posA[0]][posB[1]]);
                    decryptedText.append(matrix[posB[0]][posA[1]]);
                }
            }
            return decryptedText.toString();
        }

        private String vanbandaduocxuly(String text) {
            text = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
            text = text.replace('j', 'i');
            StringBuilder processedText = new StringBuilder();

            for (int i = 0; i < text.length(); i++) {
                processedText.append(text.charAt(i));
                if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                    processedText.append('x');
                }
            }
            if (processedText.length() % 2 != 0) {
                processedText.append('x');
            }
            return processedText.toString();
        }

        private int[] findPosition(char c) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (matrix[i][j] == c) {
                        return new int[]{i, j};
                    }
                }
            }
            throw new IllegalArgumentException("Ký Tự " + c + " không tìm được.");
        }
    }
}
