package com.example.th_attt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MahillActivity extends AppCompatActivity {

    private EditText nhapVanBan;
    private TextView ketQua;
    private View nhapLieuMaTran2x2;
    private int kichThuocMaTran = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mahill);

        nhapVanBan = findViewById(R.id.nhapvanban);
        ketQua = findViewById(R.id.ketqua);

        nhapLieuMaTran2x2 = findViewById(R.id.matrix2x2Inputs);

        Button nutMaHoa = findViewById(R.id.mahoa);
        nutMaHoa.setOnClickListener(v -> onNutMaHoaClick());

        Button nutGiaiMa = findViewById(R.id.giaima);
        nutGiaiMa.setOnClickListener(v -> onNutGiaiMaClick());
    }

    private int[][] layMaTranNhap() {
        int[][] maTranKhoa = new int[kichThuocMaTran][kichThuocMaTran];
        try {
            maTranKhoa[0][0] = layGiaTriEditText(R.id.matrix2x2_00);
            maTranKhoa[0][1] = layGiaTriEditText(R.id.matrix2x2_01);
            maTranKhoa[1][0] = layGiaTriEditText(R.id.matrix2x2_10);
            maTranKhoa[1][1] = layGiaTriEditText(R.id.matrix2x2_11);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập đúng định dạng số cho ma trận.", Toast.LENGTH_SHORT).show();
            return null;
        }
        return maTranKhoa;
    }

    private int layGiaTriEditText(int resId) {
        EditText truongNhapLieu = findViewById(resId);
        return Integer.parseInt(truongNhapLieu.getText().toString().trim());
    }

    private void onNutMaHoaClick() {
        String vanBan = nhapVanBan.getText().toString().trim();
        if (vanBan.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập văn bản để mã hóa.", Toast.LENGTH_SHORT).show();
            return;
        }

        int[][] maTranKhoa = layMaTranNhap();
        if (maTranKhoa != null) {
            String vanBanDaMaHoa = maHoa(vanBan, maTranKhoa);
            ketQua.setText("Kết quả mã hóa: " + vanBanDaMaHoa);
        }
    }

    private void onNutGiaiMaClick() {
        String vanBan = nhapVanBan.getText().toString().trim();
        if (vanBan.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập văn bản để giải mã.", Toast.LENGTH_SHORT).show();
            return;
        }

        int[][] maTranKhoa = layMaTranNhap();
        if (maTranKhoa != null) {
            String vanBanDaGiaiMa = giaiMa(vanBan, maTranKhoa);
            ketQua.setText("Kết quả giải mã: " + vanBanDaGiaiMa);
        }
    }

    private String maHoa(String vanBan, int[][] maTranKhoa) {
        vanBan = vanBan.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder vanBanDaMaHoa = new StringBuilder();
        int n = maTranKhoa.length;

        for (int i = 0; i < vanBan.length(); i += 2) {
            int a = (i < vanBan.length()) ? vanBan.charAt(i) - 'A' : 0;
            int b = (i + 1 < vanBan.length()) ? vanBan.charAt(i + 1) - 'A' : 0;

            int kyTuMaHoa1 = (a * maTranKhoa[0][0] + b * maTranKhoa[1][0]) % 26;
            int kyTuMaHoa2 = (a * maTranKhoa[0][1] + b * maTranKhoa[1][1]) % 26;

            vanBanDaMaHoa.append((char) (kyTuMaHoa1 + 'A'));
            vanBanDaMaHoa.append((char) (kyTuMaHoa2 + 'A'));
        }
        return vanBanDaMaHoa.toString();
    }

    private String giaiMa(String vanBan, int[][] maTranKhoa) {
        int[][] maTranKhoaNghichDao = tinhMaTranNghichDao(maTranKhoa);
        if (maTranKhoaNghichDao == null) {
            return "Không thể tính toán ma trận nghịch đảo.";
        }
        return maHoa(vanBan, maTranKhoaNghichDao);
    }

    private int[][] tinhMaTranNghichDao(int[][] maTran) {
        int n = maTran.length;
        int dinhThuc = tinhDinhThuc(maTran);
        if (dinhThuc == 0 || uocChungLonNhat(dinhThuc, 26) != 1) {
            return null;
        }

        int nghichDaoDinhThuc = timNghichDaoModulo(dinhThuc, 26);
        int[][] maTranPhuHop = tinhMaTranPhuHop(maTran);
        int[][] maTranNghichDao = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maTranNghichDao[i][j] = (maTranPhuHop[i][j] * nghichDaoDinhThuc % 26 + 26) % 26;
            }
        }
        return maTranNghichDao;
    }

    private int tinhDinhThuc(int[][] maTran) {
        return (maTran[0][0] * maTran[1][1] - maTran[0][1] * maTran[1][0] + 26) % 26;
    }

    private int[][] tinhMaTranPhuHop(int[][] maTran) {
        int[][] maTranPhuHop = new int[2][2];
        maTranPhuHop[0][0] = maTran[1][1];
        maTranPhuHop[0][1] = -maTran[0][1];
        maTranPhuHop[1][0] = -maTran[1][0];
        maTranPhuHop[1][1] = maTran[0][0];
        return maTranPhuHop;
    }

    private int timNghichDaoModulo(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    private int uocChungLonNhat(int a, int b) {
        return b == 0 ? a : uocChungLonNhat(b, a % b);
    }
}
