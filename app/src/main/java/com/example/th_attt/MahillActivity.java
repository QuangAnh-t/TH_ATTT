package com.example.th_attt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MahillActivity extends AppCompatActivity {

    private EditText nhapVanBan;
    private TextView ketQua;
    private View nhapLieuMaTran2x2, nhapLieuMaTran3x3, nhapLieuMaTran4x4;
    private int kichThuocMaTran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mahill);

        nhapVanBan = findViewById(R.id.nhapvanban);
        ketQua = findViewById(R.id.ketqua);

        nhapLieuMaTran2x2 = findViewById(R.id.matrix2x2Inputs);
        nhapLieuMaTran3x3 = findViewById(R.id.matrix3x3Inputs);
        nhapLieuMaTran4x4 = findViewById(R.id.matrix4x4Inputs);

        RadioGroup radioGroupMatrix = findViewById(R.id.radioGroupMatrix);
        radioGroupMatrix.setOnCheckedChangeListener((group, checkedId) -> {
            nhapLieuMaTran2x2.setVisibility(View.GONE);
            nhapLieuMaTran3x3.setVisibility(View.GONE);
            nhapLieuMaTran4x4.setVisibility(View.GONE);

            if (checkedId == R.id.radioButton2x2) {
                nhapLieuMaTran2x2.setVisibility(View.VISIBLE);
                kichThuocMaTran = 2;
            } else if (checkedId == R.id.radioButton3x3) {
                nhapLieuMaTran3x3.setVisibility(View.VISIBLE);
                kichThuocMaTran = 3;
            } else if (checkedId == R.id.radioButton4x4) {
                nhapLieuMaTran4x4.setVisibility(View.VISIBLE);
                kichThuocMaTran = 4;
            }
        });

        Button nutMaHoa = findViewById(R.id.mahoa);
        nutMaHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String vanBan = nhapVanBan.getText().toString();
               int[][] maTranKhoa = layMaTranNhap();
               String vanBanDaMaHoa = maHoa(vanBan, maTranKhoa);
               ketQua.setText("Kết quả mã hóa: " + vanBanDaMaHoa);
            }
        });

        Button nutGiaiMa = findViewById(R.id.giaima);
        nutGiaiMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vanBan = nhapVanBan.getText().toString();
                int[][] maTranKhoa = layMaTranNhap();
                String vanBanDaGiaiMa = giaiMa(vanBan, maTranKhoa);
                ketQua.setText("Kết quả giải mã: " + vanBanDaGiaiMa);
            }
        });
    }

    private int[][] layMaTranNhap() {
        int[][] maTranKhoa = new int[kichThuocMaTran][kichThuocMaTran];
        try {
            if (kichThuocMaTran == 2) {
                maTranKhoa[0][0] = layGiaTriEditText(R.id.matrix2x2_00);
                maTranKhoa[0][1] = layGiaTriEditText(R.id.matrix2x2_01);
                maTranKhoa[1][0] = layGiaTriEditText(R.id.matrix2x2_10);
                maTranKhoa[1][1] = layGiaTriEditText(R.id.matrix2x2_11);
            } else if (kichThuocMaTran == 3) {
                maTranKhoa[0][0] = layGiaTriEditText(R.id.matrix3x3_00);
                maTranKhoa[0][1] = layGiaTriEditText(R.id.matrix3x3_01);
                maTranKhoa[0][2] = layGiaTriEditText(R.id.matrix3x3_02);
                maTranKhoa[1][0] = layGiaTriEditText(R.id.matrix3x3_10);
                maTranKhoa[1][1] = layGiaTriEditText(R.id.matrix3x3_11);
                maTranKhoa[1][2] = layGiaTriEditText(R.id.matrix3x3_12);
                maTranKhoa[2][0] = layGiaTriEditText(R.id.matrix3x3_20);
                maTranKhoa[2][1] = layGiaTriEditText(R.id.matrix3x3_21);
                maTranKhoa[2][2] = layGiaTriEditText(R.id.matrix3x3_22);
            } else if (kichThuocMaTran == 4) {
                maTranKhoa[0][0] = layGiaTriEditText(R.id.matrix4x4_00);
                maTranKhoa[0][1] = layGiaTriEditText(R.id.matrix4x4_01);
                maTranKhoa[0][2] = layGiaTriEditText(R.id.matrix4x4_02);
                maTranKhoa[0][3] = layGiaTriEditText(R.id.matrix4x4_03);
                maTranKhoa[1][0] = layGiaTriEditText(R.id.matrix4x4_10);
                maTranKhoa[1][1] = layGiaTriEditText(R.id.matrix4x4_11);
                maTranKhoa[1][2] = layGiaTriEditText(R.id.matrix4x4_12);
                maTranKhoa[1][3] = layGiaTriEditText(R.id.matrix4x4_13);
                maTranKhoa[2][0] = layGiaTriEditText(R.id.matrix4x4_20);
                maTranKhoa[2][1] = layGiaTriEditText(R.id.matrix4x4_21);
                maTranKhoa[2][2] = layGiaTriEditText(R.id.matrix4x4_22);
                maTranKhoa[2][3] = layGiaTriEditText(R.id.matrix4x4_23);
                maTranKhoa[3][0] = layGiaTriEditText(R.id.matrix4x4_30);
                maTranKhoa[3][1] = layGiaTriEditText(R.id.matrix4x4_31);
                maTranKhoa[3][2] = layGiaTriEditText(R.id.matrix4x4_32);
                maTranKhoa[3][3] = layGiaTriEditText(R.id.matrix4x4_33);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập đúng định dạng số cho ma trận.", Toast.LENGTH_SHORT).show();
            return null;
        }
        return maTranKhoa;
    }

    private int layGiaTriEditText(int so) {
        EditText truongNhapLieu = findViewById(so);
        return Integer.parseInt(truongNhapLieu.getText().toString().trim());
    }

    private String maHoa(String vanBan, int[][] maTranKhoa) {
        vanBan = vanBan.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder vanBanDaMaHoa = new StringBuilder();
        
        while (vanBan.length() % kichThuocMaTran != 0) {
            vanBan += "X";
        }
        if (kichThuocMaTran == 2) {
            for (int i = 0; i < vanBan.length(); i += 2) {
               a = (i< vanBan.length()) ? (vanBan.charAt(i) - 'A') : 0;
               b = (i + 1< vanBan.length()) ? (vanBan.charAt(i + 1) - 'A') : 0;
               int kytumahoa1 = (a * maTranKhoa[0][0] + b * maTranKhoa[0][1]) % 26;
               int kytumahoa2 = (a * maTranKhoa[1][0] + b * maTranKhoa[1][1]) % 26;
               vanBanDaMaHoa.append((char) (kytumahoa1 + 'A'));
               vanBanDaMaHoa.append((char) (kytumahoa2 + 'A'));
            }
        }
        else if (kichThuocMaTran == 3) {
            for (int i = 0; i < vanBan.length(); i += 3) {
                a = (i< vanBan.length()) ? (vanBan.charAt(i) - 'A') : 0;
                b = (i + 1< vanBan.length()) ? (vanBan.charAt(i + 1) - 'A') : 0;
                c = (i + 2< vanBan.length()) ? (vanBan.charAt(i + 2) - 'A') : 0;
                int kytumahoa1 = (a * maTranKhoa[0][0] + b * maTranKhoa[0][1] + c * maTranKhoa[0][2]) % 26;
                int kytumahoa2 = (a * maTranKhoa[1][0] + b * maTranKhoa[1][1] + c * maTranKhoa[1][2]) % 26;
                int kytumahoa3 = (a * maTranKhoa[2][0] + b * maTranKhoa[2][1] + c * maTranKhoa[2][2]) % 26;
                vanBanDaMaHoa.append((char) (kytumahoa1 + 'A'));
                vanBanDaMaHoa.append((char) (kytumahoa2 + 'A'));
                vanBanDaMaHoa.append((char) (kytumahoa3 + 'A'));
            }   
        }
        else if (kichThuocMaTran == 4) {
            for (int i = 0; i < vanBan.length(); i += 4) {
                a = (i< vanBan.length()) ? (vanBan.charAt(i) - 'A') : 0;
                b = (i + 1< vanBan.length()) ? (vanBan.charAt(i + 1) - 'A') : 0;
                c = (i + 2< vanBan.length()) ? (vanBan.charAt(i + 2) - 'A') : 0;
                d = (i + 3< vanBan.length()) ? (vanBan.charAt(i + 3) - 'A') : 0;
                int kytumahoa1 = (a * maTranKhoa[0][0] + b * maTranKhoa[0][1] + c * maTranKhoa[0][2] + d * maTranKhoa[0][3]) % 26;
                int kytumahoa2 = (a * maTranKhoa[1][0] + b * maTranKhoa[1][1] + c * maTranKhoa[1][2] + d * maTranKhoa[1][3]) % 26;
                int kytumahoa3 = (a * maTranKhoa[2][0] + b * maTranKhoa[2][1] + c * maTranKhoa[2][2] + d * maTranKhoa[2][3]) % 26;
                int kytumahoa4 = (a * maTranKhoa[3][0] + b * maTranKhoa[3][1] + c * maTranKhoa[3][2] + d * maTranKhoa[3][3]) % 26;
                vanBanDaMaHoa.append((char) (kytumahoa1 + 'A'));
                vanBanDaMaHoa.append((char) (kytumahoa2 + 'A'));
                vanBanDaMaHoa.append((char) (kytumahoa3 + 'A'));
                vanBanDaMaHoa.append((char) (kytumahoa4 + 'A'));
            }
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
        int n = maTran.length;
        if (n == 2) {
            return (maTran[0][0] * maTran[1][1] - maTran[0][1] * maTran[1][0] + 26) % 26;
        } else if (n == 3) {
            return (maTran[0][0] * (maTran[1][1] * maTran[2][2] - maTran[1][2] * maTran[2][1]) - maTran[0][1] * (maTran[1][0] * maTran[2][2] - maTran[1][2] * maTran[2][0]) + maTran[0][2] * (maTran[1][0] * maTran[2][1] - maTran[1][1] * maTran[2][0])) % 26;
        } else if (n == 4) {
            return (maTran[0][0] * ((maTran[1][1] * maTran[2][2] * maTran[3][3] + maTran[1][2] * maTran[2][3] * maTran[3][1] + maTran[1][3] * maTran[2][1] * maTran[3][2]) - (maTran[1][3] * maTran[2][2] * maTran[3][1] + maTran[3][2] * maTran[2][3] * maTran[1][1] + maTran[3][3] * maTran[2][1] * maTran[1][2]))) - (maTran[0][1] * ((maTran[1][0] * maTran[2][2] * maTran[3][3] + maTran[1][2] * maTran[2][3] * maTran[3][0] + maTran[1][3] * maTran[2][0] * maTran[3][2]) - (maTran[1][3] * maTran[2][2] * maTran[3][0] + maTran[3][2] * maTran[2][3] * maTran[1][0] + maTran[3][3] * maTran[2][0] * maTran[1][2]))) + (maTran[0][2] * (maTran[1][0] * (maTran[2][1] * maTran[3][3] + maTran[1][1] * maTran[2][3] * maTran[3][0] + maTran[1][3] * maTran[2][0] * maTran[3][1]) - (maTran[1][3] * maTran[2][1] * maTran[3][0] + maTran[3][1] * maTran[2][3] * maTran[1][0] + maTran[3][3] * maTran[2][0] * maTran[1][1]))) - (maTran[0][3] * (maTran[1][0] * (maTran[2][1] * maTran[3][2] + maTran[1][1] * maTran[2][2] * maTran[3][0] + maTran[1][2] * maTran[2][0] * maTran[3][1]) - (maTran[1][2] * maTran[2][1] * maTran[3][0] + maTran[1][0] * maTran[2][2] * maTran[3][1] + maTran[1][1] * maTran[2][0] * maTran[3][2]))) % 26;
        }
        return 0;
    }

    private int[][] tinhMaTranPhuHop(int[][] maTran) {
        int n = maTran.length;
        int[][] maTranPhuHop = new int[n][n];
        if (n == 2) {
            maTranPhuHop[0][0] = maTran[1][1];
            maTranPhuHop[0][1] = -maTran[0][1];
            maTranPhuHop[1][0] = -maTran[1][0];
            maTranPhuHop[1][1] = maTran[0][0];
        } else if (n == 3) {
            maTranPhuHop[0][0] = (maTran[1][1] * maTran[2][2] - maTran[1][2] * maTran[2][1]) % 26;
            maTranPhuHop[0][1] = (maTran[0][2] * maTran[2][1] - maTran[0][1] * maTran[2][2]) % 26;
            maTranPhuHop[0][2] = (maTran[0][1] * maTran[1][2] - maTran[0][2] * maTran[1][1]) % 26;
            maTranPhuHop[1][0] = (maTran[1][2] * maTran[2][0] - maTran[1][0] * maTran[2][2]) % 26;
            maTranPhuHop[1][1] = (maTran[0][0] * maTran[2][2] - maTran[0][2] * maTran[2][0]) % 26;
            maTranPhuHop[1][2] = (maTran[0][2] * maTran[1][0] - maTran[0][0] * maTran[1][2]) % 26;
            maTranPhuHop[2][0] = (maTran[1][0] * maTran[2][1] - maTran[1][1] * maTran[2][0]) % 26;
            maTranPhuHop[2][1] = (maTran[0][1] * maTran[2][0] - maTran[0][0] * maTran[2][1]) % 26;
            maTranPhuHop[2][2] = (maTran[0][0] * maTran[1][1] - maTran[0][1] * maTran[1][0]) % 26;
        } else if (n == 4) {
            // Tính toán ma trận phụ hợp cho ma trận 4x4
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int[][] maTranCon = new int[n - 1][n - 1];
                    for (int k = 0; k < n; k++) {
                        if (k == i) continue;
                        for (int l = 0; l < n; l++) {
                            if (l == j) continue;
                            maTranCon[k < i ? k : k - 1][l < j ? l : l - 1] = maTran[k][l];
                        }
                    }
                    maTranPhuHop[i][j] = (int) Math.pow(-1, i + j) * tinhDinhThuc(maTranCon);
                }
            }
        }
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
