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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MahillActivity extends AppCompatActivity {

    private EditText nhapvanban; // Ô nhập văn bản từ người dùng
    private TextView ketqua; // TextView để hiển thị kết quả
    private RadioGroup matrixSelectionGroup; // Nhóm các nút radio cho phép chọn loại ma trận
    private View matrix2x2Inputs, matrix3x3Inputs; // Các layout chứa ô nhập liệu cho ma trận 2x2 và 3x3
    private int matrixSize = 2; // Mặc định là ma trận 2x2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Bật chế độ toàn màn hình
        setContentView(R.layout.mahill);

        // Thiết lập vùng đệm cho phần view chính khi hiển thị
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nhapvanban = findViewById(R.id.nhapvanban); // Liên kết đến ô nhập văn bản
        ketqua = findViewById(R.id.ketqua); // Liên kết đến ô hiển thị kết quả

        matrixSelectionGroup = findViewById(R.id.matrixSelectionGroup); // Nhóm các nút chọn ma trận
        matrix2x2Inputs = findViewById(R.id.matrix2x2Inputs); // Layout chứa ô nhập cho ma trận 2x2
        matrix3x3Inputs = findViewById(R.id.matrix3x3Inputs); // Layout chứa ô nhập cho ma trận 3x3

        // Thiết lập sự kiện khi thay đổi lựa chọn loại ma trận
        matrixSelectionGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio2x2) { // Nếu chọn ma trận 2x2
                matrixSize = 2;
                matrix2x2Inputs.setVisibility(View.VISIBLE); // Hiển thị ô nhập ma trận 2x2
                matrix3x3Inputs.setVisibility(View.GONE); // Ẩn ô nhập ma trận 3x3
            } else if (checkedId == R.id.radio3x3) { // Nếu chọn ma trận 3x3
                matrixSize = 3;
                matrix2x2Inputs.setVisibility(View.GONE); // Ẩn ô nhập ma trận 2x2
                matrix3x3Inputs.setVisibility(View.VISIBLE); // Hiển thị ô nhập ma trận 3x3
            }
        });

        // Thiết lập sự kiện khi nhấn nút mã hóa
        Button mahoa = findViewById(R.id.mahoa);
        mahoa.setOnClickListener(v -> onEncryptButtonClick());

        // Thiết lập sự kiện khi nhấn nút giải mã
        Button giaima = findViewById(R.id.giaima);
        giaima.setOnClickListener(v -> onDecryptButtonClick());
    }

    // Hàm lấy giá trị ma trận nhập từ người dùng
    private int[][] getMatrixInput() {
        int[][] keyMatrix;
        try {
            if (matrixSize == 2) {
                keyMatrix = new int[][]{
                        {Integer.parseInt(((EditText) findViewById(R.id.matrix2x2_00)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.matrix2x2_01)).getText().toString())},
                        {Integer.parseInt(((EditText) findViewById(R.id.matrix2x2_10)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.matrix2x2_11)).getText().toString())}
                };
            } else {
                keyMatrix = new int[][]{
                        {Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_00)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_01)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_02)).getText().toString())},
                        {Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_10)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_11)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_12)).getText().toString())},
                        {Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_20)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_21)).getText().toString()), Integer.parseInt(((EditText) findViewById(R.id.matrix3x3_22)).getText().toString())}
                };
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập đúng định dạng số cho ma trận.", Toast.LENGTH_SHORT).show();
            return null;
        }
        return keyMatrix;
    }

    // Hàm mã hóa
    private void onEncryptButtonClick() {
        String text = nhapvanban.getText().toString().trim(); // Lấy văn bản từ ô nhập
        if (text.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập văn bản để mã hóa.", Toast.LENGTH_SHORT).show();
            return;
        }

        int[][] keyMatrix = getMatrixInput(); // Lấy ma trận khóa từ ô nhập
        if (keyMatrix != null) {
            String encryptedText = encrypt(text, keyMatrix); // Gọi hàm mã hóa
            ketqua.setText("Kết quả mã hóa: " + encryptedText); // Hiển thị kết quả mã hóa
        }
    }

    // Hàm giải mã
    private void onDecryptButtonClick() {
        String text = nhapvanban.getText().toString().trim(); // Lấy văn bản từ ô nhập
        if (text.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập văn bản để giải mã.", Toast.LENGTH_SHORT).show();
            return;
        }

        int[][] keyMatrix = getMatrixInput(); // Lấy ma trận khóa từ ô nhập
        if (keyMatrix != null) {
            String decryptedText = decrypt(text, keyMatrix); // Gọi hàm giải mã
            ketqua.setText("Kết quả giải mã: " + decryptedText); // Hiển thị kết quả giải mã
        }
    }

    // Hàm thực hiện mã hóa
    private String encrypt(String text, int[][] keyMatrix) {
        text = text.toUpperCase().replaceAll("[^A-Z]", ""); // Loại bỏ ký tự không phải chữ cái và chuyển sang chữ hoa
        StringBuilder encryptedText = new StringBuilder();

        // Xử lý các ký tự của văn bản theo kích thước ma trận
        for (int i = 0; i < text.length(); i += matrixSize) {
            int[] textVector = new int[matrixSize];

            for (int j = 0; j < matrixSize; j++) {
                textVector[j] = (i + j < text.length()) ? text.charAt(i + j) - 'A' : 0; // Padding with 0
            }

            // Nhân ma trận khóa với vector văn bản
            for (int row = 0; row < matrixSize; row++) {
                int sum = 0;
                for (int col = 0; col < matrixSize; col++) {
                    sum += keyMatrix[row][col] * textVector[col];
                }
                encryptedText.append((char) ((sum % 26) + 'A'));
            }
        }
        return encryptedText.toString();
    }

    // Hàm thực hiện giải mã
    private String decrypt(String text, int[][] keyMatrix) {
        int[][] inverseKeyMatrix = calculateInverseMatrix(keyMatrix);
        if (inverseKeyMatrix == null) {
            return "Không thể tính toán ma trận nghịch đảo.";
        }

        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += matrixSize) {
            int[] textVector = new int[matrixSize];

            for (int j = 0; j < matrixSize; j++) {
                textVector[j] = (i + j < text.length()) ? text.charAt(i + j) - 'A' : 0; // Padding with 0
            }

            // Nhân ma trận nghịch đảo với vector văn bản
            for (int row = 0; row < matrixSize; row++) {
                int sum = 0;
                for (int col = 0; col < matrixSize; col++) {
                    sum += inverseKeyMatrix[row][col] * textVector[col];
                }
                decryptedText.append((char) ((sum % 26 + 26) % 26 + 'A')); // Ensure positive modulo
            }
        }
        return decryptedText.toString();
    }

    // Hàm tính toán ma trận nghịch đảo của ma trận khóa
    private int[][] calculateInverseMatrix(int[][] matrix) {
        if (matrix.length == 2) {
            int det = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % 26;
            if (det < 0) det += 26; // Đảm bảo định thức dương
            int inverseDet = modInverse(det, 26); // Tính nghịch đảo modulo

            if (inverseDet == -1) return null; // Không tồn tại nghịch đảo

            return new int[][]{
                    {(matrix[1][1] * inverseDet) % 26, (-matrix[0][1] * inverseDet) % 26},
                    {(-matrix[1][0] * inverseDet) % 26, (matrix[0][0] * inverseDet) % 26}
            };
        } else if (matrix.length == 3) {
            // Tính định thức cho ma trận 3x3
            int det = matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) -
                    matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]) +
                    matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]);

            if (det < 0) det += 26; // Đảm bảo định thức dương
            int inverseDet = modInverse(det, 26); // Tính nghịch đảo modulo

            if (inverseDet == -1) return null; // Không tồn tại nghịch đảo

            // Tính ma trận adjugate
            int[][] adjugate = {
                    {(matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) * inverseDet % 26,
                            (matrix[0][2] * matrix[2][1] - matrix[0][1] * matrix[2][2]) * inverseDet % 26,
                            (matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1]) * inverseDet % 26},
                    {(matrix[1][2] * matrix[2][0] - matrix[1][0] * matrix[2][2]) * inverseDet % 26,
                            (matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0]) * inverseDet % 26,
                            (matrix[0][2] * matrix[1][0] - matrix[0][0] * matrix[1][2]) * inverseDet % 26},
                    {(matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]) * inverseDet % 26,
                            (matrix[0][1] * matrix[2][0] - matrix[0][0] * matrix[2][1]) * inverseDet % 26,
                            (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) * inverseDet % 26}
            };

            // Trả về ma trận nghịch đảo
            return adjugate;
        }
        return null;
    }

    // Hàm tính nghịch đảo modulo
    private int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1; // Không có nghịch đảo
    }

}
