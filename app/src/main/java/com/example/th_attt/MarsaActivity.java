package com.example.th_attt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MarsaActivity extends AppCompatActivity {
    EditText sop, soq, som;
    Button giai;
    TextView ketqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marsa);
        sop = findViewById(R.id.sop);
        soq = findViewById(R.id.soq);
        giai = findViewById(R.id.giai);
        ketqua = findViewById(R.id.ketqua);
        som = findViewById(R.id.som);
        giai.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String soP = sop.getText().toString();
                String soQ = soq.getText().toString();
                String soM = som.getText().toString();
                
                if (soP.isEmpty() || soQ.isEmpty() || soM.isEmpty()) {
                    ketqua.setText("Vui lòng nhập đầy đủ số P, Q và M.");
                    return;
                }
                
                try {
                    String ketquaText = giaiRSA(soP, soQ, soM);
                    ketqua.setText(ketquaText);
                } catch (NumberFormatException e) {
                    ketqua.setText("Vui lòng nhập số hợp lệ.");
                } catch (ArithmeticException e) {
                    ketqua.setText("Lỗi toán học: " + e.getMessage());
                }
            }
        });
    }

    public String giaiRSA(String soP, String soQ, String soM) {
        int p = Integer.parseInt(soP);
        int q = Integer.parseInt(soQ);
        int m = Integer.parseInt(soM);
        int n = p * q;
        
        int phi = (p - 1) * (q - 1);
        int e = 2;
        while (e < phi) {
            if (ucln(e, phi) == 1) break;
            e++;
        }
        int d = 0;
        while (d <= n) {
            if ((d * e) % phi == 1) break;
            d++;
        }
        int ketquamahoa = (int) Math.pow(m, e) % n; 
        
        int ketquagiaima = (int) Math.pow(ketquamahoa, d) % n;
        return "Kết quả mã hóa: " + ketquamahoa + "\n" + "Kết quả giải mã: " + ketquagiaima;
    }

    private int ucln(int a, int b) {
        if (b == 0) return a;
        return ucln(b, a % b);
    }
}
