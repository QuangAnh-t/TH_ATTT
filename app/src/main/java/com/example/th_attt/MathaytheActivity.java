package com.example.th_attt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MathaytheActivity extends AppCompatActivity {

    TextView textViewMa;
    EditText editTextVanBan;
    EditText editTextKhoa;
    Button buttonMaHoa;
    Button buttonGiaiMa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mathaythe);


        editTextVanBan = findViewById(R.id.vanban);
        editTextKhoa = findViewById(R.id.khoak);
        textViewMa = findViewById(R.id.textViewCode);
        buttonMaHoa = findViewById(R.id.mahoa);
        buttonGiaiMa = findViewById(R.id.giaima);


        buttonMaHoa.setOnClickListener(v -> {
            String vanBan = editTextVanBan.getText().toString();
            String khoa = editTextKhoa.getText().toString();

            if (vanBan.isEmpty() || khoa.isEmpty()) {
                textViewMa.setText("Vui lòng nhập khóa mã hóa và văn bản.");
            } else if (!vanBan.matches("[a-zA-Z]+") || !khoa.matches("[a-zA-Z]+")) {
                textViewMa.setText("Văn bản và khóa chỉ được chứa các ký tự chữ cái.");
            } else {
                matran playfairCipher = new matran(khoa);
                String maTranChuoi = playfairCipher.hienThiMaTran();
                textViewMa.setText("Ma trận đã xử lý:\n" + maTranChuoi);

                String vanBanDaMaHoa = playfairCipher.mahoar(vanBan);
                textViewMa.append("\nVăn bản đã mã hóa: " + vanBanDaMaHoa);
            }
        });

        buttonGiaiMa.setOnClickListener(v -> {
            String vanBan = editTextVanBan.getText().toString();
            String khoa = editTextKhoa.getText().toString();

            if (vanBan.isEmpty() || khoa.isEmpty()) {
                textViewMa.setText("Vui lòng nhập khóa mã hóa và văn bản.");
            } else if (!vanBan.matches("[a-zA-Z]+") || !khoa.matches("[a-zA-Z]+")) {
                textViewMa.setText("Văn bản và khóa chỉ được chứa các ký tự chữ cái.");
            } else {
                matran playfairCipher = new matran(khoa);
                String maTranChuoi = playfairCipher.hienThiMaTran();
                textViewMa.setText("Ma trận đã xử lý:\n" + maTranChuoi);


                String vanBanDaMaHoa = playfairCipher.giaimar(vanBan);
                textViewMa.append("\nVăn bản đã mã hóa: " + vanBanDaMaHoa);
            }
        });
    }

    public class matran {
        private String khoa;
        private char[][] maTran = new char[5][5];

        public matran(String khoa) {
            this.khoa = khoa;
            createMatrix();
        }

        private void createMatrix() {
            String chuoiKhoa = kiemtra(khoa + "abcdefghijklmnopqrstuvwxyz");
            chuoiKhoa = chuoiKhoa.replace('j', 'i');

            boolean[] daSuDung = new boolean[26];
            int k = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    while (daSuDung[chuoiKhoa.charAt(k) - 'a']) {
                        k++;
                    }
                    maTran[i][j] = chuoiKhoa.charAt(k);
                    daSuDung[chuoiKhoa.charAt(k) - 'a'] = true;
                    k++;
                }
            }
        }

        private String kiemtra(String str) {
            StringBuilder sb = new StringBuilder();
            boolean[] daXem = new boolean[26];
            for (char c : str.toCharArray()) {
                if (!daXem[c - 'a']) {
                    sb.append(c);
                    daXem[c - 'a'] = true;
                }
            }
            return sb.toString();
        }

        public String mahoar(String text) {
            text = vanbandaduocxuly(text);
            StringBuilder vanBanDaMaHoa = new StringBuilder();

            for (int i = 0; i < text.length(); i += 2) {
                char a = text.charAt(i);
                char b = text.charAt(i + 1);
                int[] viTriA = timvitri(a);
                int[] viTriB = timvitri(b);

                if (viTriA[0] == viTriB[0]) {
                    vanBanDaMaHoa.append(maTran[viTriA[0]][(viTriA[1] + 1) % 5]);
                    vanBanDaMaHoa.append(maTran[viTriB[0]][(viTriB[1] + 1) % 5]);
                } else if (viTriA[1] == viTriB[1]) {
                    vanBanDaMaHoa.append(maTran[(viTriA[0] + 1) % 5][viTriA[1]]);
                    vanBanDaMaHoa.append(maTran[(viTriB[0] + 1) % 5][viTriB[1]]);
                } else {
                    vanBanDaMaHoa.append(maTran[viTriA[0]][viTriB[1]]);
                    vanBanDaMaHoa.append(maTran[viTriB[0]][viTriA[1]]);
                }
            }
            return vanBanDaMaHoa.toString();
        }

        public String giaimar(String text) {
            text = vanbandaduocxuly(text);
            StringBuilder vanBanDaGiaiMa = new StringBuilder();

            for (int i = 0; i < text.length(); i += 2) {
                char a = text.charAt(i);
                char b = text.charAt(i + 1);
                int[] viTriA = timvitri(a);
                int[] viTriB = timvitri(b);

                if (viTriA[0] == viTriB[0]) {
                    vanBanDaGiaiMa.append(maTran[viTriA[0]][(viTriA[1] + 4) % 5]);
                    vanBanDaGiaiMa.append(maTran[viTriB[0]][(viTriB[1] + 4) % 5]);
                } else if (viTriA[1] == viTriB[1]) {
                    vanBanDaGiaiMa.append(maTran[(viTriA[0] + 4) % 5][viTriA[1]]);
                    vanBanDaGiaiMa.append(maTran[(viTriB[0] + 4) % 5][viTriB[1]]);
                } else {
                    vanBanDaGiaiMa.append(maTran[viTriA[0]][viTriB[1]]);
                    vanBanDaGiaiMa.append(maTran[viTriB[0]][viTriA[1]]);
                }
            }
            return vanBanDaGiaiMa.toString();
        }

        private String vanbandaduocxuly(String text) {
            text = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
            text = text.replace('j', 'i');
            StringBuilder vanBanDaXuLy = new StringBuilder();

            for (int i = 0; i < text.length(); i++) {
                vanBanDaXuLy.append(text.charAt(i));
                if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                    vanBanDaXuLy.append('x');
                }
            }
            if (vanBanDaXuLy.length() % 2 != 0) {
                vanBanDaXuLy.append('x');
            }
            return vanBanDaXuLy.toString();
        }

        private int[] timvitri(char c) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (maTran[i][j] == c) {
                        return new int[]{i, j};
                    }
                }
            }
            throw new IllegalArgumentException("Ký Tự " + c + " không tìm được.");
        }
        public String hienThiMaTran() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    sb.append(maTran[i][j]).append(" ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
