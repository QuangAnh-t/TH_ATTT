package com.example.th_attt;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import android.util.Base64;

public class MarsaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marsa);

        // Khai báo các thành phần giao diện
        EditText vanBanNhap = findViewById(R.id.inputText); // Nhập văn bản
        EditText khoaCongKhaiNhap = findViewById(R.id.publicKey); // Nhập khóa công khai
        EditText khoaRiengTuNhap = findViewById(R.id.privateKey); // Nhập khóa riêng tư
        Button nutMaHoa = findViewById(R.id.encryptButton); // Nút mã hóa
        Button nutGiaiMa = findViewById(R.id.decryptButton); // Nút giải mã
        TextView ketQuaHienThi = findViewById(R.id.resultText); // Hiển thị kết quả

        // Tạo cặp khóa RSA (dùng để thử nghiệm, trong ứng dụng thực tế nên tạo/lưu trữ khóa an toàn)
        KeyPair capKhoa = taoCapKhoaRSA();
        PublicKey khoaCongKhai = capKhoa.getPublic();
        PrivateKey khoaRiengTu = capKhoa.getPrivate();

     
        khoaCongKhaiNhap.setText(Base64.encodeToString(khoaCongKhai.getEncoded(), Base64.DEFAULT));
        khoaRiengTuNhap.setText(Base64.encodeToString(khoaRiengTu.getEncoded(), Base64.DEFAULT));

       
        nutMaHoa.setOnClickListener(v -> {
            String vanBanRo = vanBanNhap.getText().toString(); 
            String vanBanMaHoa = maHoaRSA(vanBanRo, khoaCongKhai); 
            ketQuaHienThi.setText("Mã hóa: " + vanBanMaHoa); 
        });

        
        nutGiaiMa.setOnClickListener(v -> {
            String vanBanMaHoa = vanBanNhap.getText().toString(); 
            String vanBanGiaiMa = giaiMaRSA(vanBanMaHoa, khoaRiengTu); 
            ketQuaHienThi.setText("Giải mã: " + vanBanGiaiMa); 
        });
    }


    private KeyPair taoCapKhoaRSA() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

 
    private String maHoaRSA(String vanBanRo, PublicKey khoaCongKhai) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, khoaCongKhai);
            byte[] maHoaBytes = cipher.doFinal(vanBanRo.getBytes());
            return Base64.encodeToString(maHoaBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Giải mã dữ liệu bằng khóa riêng tư
    private String giaiMaRSA(String vanBanMaHoa, PrivateKey khoaRiengTu) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, khoaRiengTu);
            byte[] maHoaBytes = Base64.decode(vanBanMaHoa, Base64.DEFAULT);
            byte[] giaiMaBytes = cipher.doFinal(maHoaBytes);
            return new String(giaiMaBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}