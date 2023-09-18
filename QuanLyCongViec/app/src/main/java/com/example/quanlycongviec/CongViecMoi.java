package com.example.quanlycongviec;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CongViecMoi extends AppCompatActivity {
    private EditText edtTenCongViec;
    private EditText edtThoiGianThucHien;
    private Button btnLuu;
    private Button btnDong;

    private CongViecDatabase congViecDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cong_viec_moi);

        // Ánh xạ các thành phần giao diện
        edtTenCongViec = findViewById(R.id.edittxtTenId);
        edtThoiGianThucHien = findViewById(R.id.edittxtTGThucHienId);
        btnLuu = findViewById(R.id.btnLuuId);
        btnDong = findViewById(R.id.btnDongId);

        // Khởi tạo đối tượng CongViecDatabase
        congViecDatabase = new CongViecDatabase(this, "QuanLyCongViec.sqlite", null, 1);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng activity hiện tại
                finish();
            }
        });
    }
}
