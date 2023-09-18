package com.example.testthu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DanhBaDatabase danhBaDatabase;
    ListView lvDanhBa;
    ArrayList<DanhBa> arrayDanhBa;
    DanhBaAdapter danhBaAdapter;
    Button btnLoad, btnThem, btnXoa, btnSua, btnDong;
    EditText editTextId, editTextTen, editTextSoDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDanhBa = findViewById(R.id.lstviewDanhBaId);
        arrayDanhBa = new ArrayList<>();
        danhBaAdapter = new DanhBaAdapter(this, R.layout.list_item_layout, arrayDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);

        // Tạo DATABASE
        danhBaDatabase = new DanhBaDatabase(this, "QuanLyDanhBa.sqlite", null, 1);

//        // Xóa Bảng
//        String sqlDrop = "DROP TABLE IF EXISTS DanhBa";
//        danhBaDatabase.QueryData(sqlDrop);

        // Tạo Bảng DanhBa
        String sql = "CREATE TABLE IF NOT EXISTS DanhBa(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(80), SoDienThoai NVARCHAR(15))";
        danhBaDatabase.QueryData(sql);

        // Nhập dữ liệu cho Table
        InitDataDanhBa();

        // Ánh xạ View
        InitWidgets();

        // Xử lý Load dữ liệu
        btnLoad = findViewById(R.id.btnLoadId);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDataDanhBa();
            }
        });

        // Xử lý thêm dữ liệu
        btnThem = (Button) findViewById(R.id.btnThemId);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strId = editTextId.getText().toString();
                String strTen = editTextTen.getText().toString();
                String strSoDT = editTextSoDT.getText().toString();

                if (strId.isEmpty() || strTen.isEmpty() || strSoDT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin trước khi thêm!", Toast.LENGTH_SHORT).show();
                } else {
                    int Id = Integer.parseInt(strId);

                    //Kiểm tra xem ID đã tồn tại hay chưa
                    if (isIdExists(Id)) {
                        Toast.makeText(MainActivity.this, "ID đã tồn tại. Vui lòng nhập một ID khác!", Toast.LENGTH_SHORT).show();
                    } else {
                        InsertDanhBa(Id, strTen, strSoDT);
                        LoadDataDanhBa();
                        ClearData();
                        // Hiển thị thông báo thêm dữ liệu thành công
                        Toast.makeText(MainActivity.this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Xử lý xóa dữ liệu
        btnXoa = (Button) findViewById(R.id.btnXoaId);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strId = editTextId.getText().toString();
                if (strId.isEmpty()) {
                    //Hiển thị thông báo cho người dùng về việc nhập ID trước khi xóa.
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID trước khi xóa!", Toast.LENGTH_SHORT).show();
                } else {
                    int Id = Integer.valueOf(strId);

                    if (isIdExists(Id)) {
                        DeleteDanhBa(Id);
                        LoadDataDanhBa();
                        ClearData();
                        Toast.makeText(MainActivity.this, "Đã xoá thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        //Hiển thị thông báo cho người dùng biết rằng ID không tồn tại trong cơ sở dữ liệu
                        Toast.makeText(MainActivity.this, "ID không tồn tại trong danh bạ!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Xử lý cập nhật dữ liệu
        btnSua = (Button) findViewById(R.id.btnSuaId);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strId = editTextId.getText().toString();
                String strTen = editTextTen.getText().toString();
                String strSoDT = editTextSoDT.getText().toString();

                if (strId.isEmpty()) {
                    //Hiển thị thông báo cho người dùng về việc nhập ID trước khi sửa.
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID trước khi sửa!", Toast.LENGTH_SHORT).show();
                } else if (strTen.isEmpty() || strSoDT.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin trước khi sửa!", Toast.LENGTH_SHORT).show();
                } else {
                    int Id = Integer.parseInt(strId);


                    if (isIdExists(Id)) {
                        UpdateDanhBa(Id, strTen, strSoDT);
                        LoadDataDanhBa();
                        ClearData();
                        // Hiển thị thông báo cập nhật dữ liệu thành công
                        Toast.makeText(MainActivity.this, "Đã sửa thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        //Hiển thị thông báo cho người dùng biết rằng ID không tồn tại trong cơ sở dữ liệu
                        Toast.makeText(MainActivity.this, "ID không tồn tại trong danh bạ!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Xử lý đóng
        btnDong = findViewById(R.id.btnDongId);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitWidgets() {
        editTextId = findViewById(R.id.edittxtIDId);
        editTextTen = findViewById(R.id.edittxtTenId);
        editTextSoDT = findViewById(R.id.edittxtSoDienThoaiId);
    }

    void InitDataDanhBa() {
        String sqlCheckData = "SELECT COUNT(*) FROM DanhBa";
        Cursor cursor = danhBaDatabase.QueryGetData(sqlCheckData);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        // Nếu không có bản ghi nào trong bảng, thêm dữ liệu mẫu
        if (count == 0) {
            String sqlinsert1 = "insert into DanhBa values(null, 'Alexander Dang', '0938556677')";
            String sqlinsert2 = "insert into DanhBa values(null, 'Filip Nguyen', '0933567788')";
            String sqlinsert3 = "insert into DanhBa values(null, 'Dang Van Lam', '0938667799')";

            danhBaDatabase.QueryData(sqlinsert1);
            danhBaDatabase.QueryData(sqlinsert2);
            danhBaDatabase.QueryData(sqlinsert3);
        }
    }

    public void LoadDataDanhBa() {
        String sqlSelect = "SELECT * FROM DanhBa";
        Cursor dataDanhBa = danhBaDatabase.QueryGetData(sqlSelect);
        arrayDanhBa.clear();
        while (dataDanhBa.moveToNext()) {
            int Id = dataDanhBa.getInt(0);
            String strTen = dataDanhBa.getString(1);
            String strSoDT = dataDanhBa.getString(2);

            arrayDanhBa.add(new DanhBa(Id, strTen, strSoDT));
        }
        danhBaAdapter.notifyDataSetChanged();
    }

    public void InsertDanhBa(int Id, String strTen, String strSoDT) {
        String sqlInsert = "INSERT INTO DanhBa VALUES(" + Id + ", '" + strTen + "', '" + strSoDT + "')";
        danhBaDatabase.QueryData(sqlInsert);
    }

    public void DeleteDanhBa(int Id) {
        String sqlDelete = "DELETE FROM DanhBa WHERE Id=" + Id;
        danhBaDatabase.QueryData(sqlDelete);
    }

    public void UpdateDanhBa(int Id, String strTen, String strSoDT) {
        String sqlUpdate = "UPDATE DanhBa SET Ten='" + strTen + "', SoDienThoai='" + strSoDT + "' WHERE Id=" + Id;
        danhBaDatabase.QueryData(sqlUpdate);
    }

    private boolean isIdExists(int Id) {
        String sqlCheckId = "SELECT * FROM DanhBa WHERE Id ="  + Id;
        Cursor cursor = danhBaDatabase.QueryGetData(sqlCheckId);
        boolean exists = cursor.moveToNext();
        cursor.close();
        return exists;
    }
    //Kiểm tra tên có tồn tại hay không
    //private boolean isTenExists(String ten) {
        //String sqlCheckTen = "SELECT * FROM DanhBa WHERE Ten ='" + ten + "'";
        //Cursor cursor = danhBaDatabase.QueryGetData(sqlCheckTen);
        //boolean exists = cursor.moveToNext();
        //cursor.close();
        //return exists;
    //}
    //Kiểm tra số điện thoại
    //private boolean isSoDTExists(String soDT) {
        //String sqlCheckSoDT = "SELECT * FROM DanhBa WHERE SoDT ='" + soDT + "'";
        //Cursor cursor = danhBaDatabase.QueryGetData(sqlCheckSoDT);
        //boolean exists = cursor.moveToNext();
        //cursor.close();
        //return exists;
    //}

    public void ClearData(){
        editTextId.setText("");
        editTextTen.setText("");
        editTextSoDT.setText("");
    }

}