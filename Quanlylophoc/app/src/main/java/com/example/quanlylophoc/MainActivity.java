package com.example.quanlylophoc;

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
    LopHocDatabase lopHocDatabase;
    ListView lvLopHoc;
    ArrayList<LopHoc>arrayLopHoc;
    LopHocAdapter lopHocAdapter;
    Button btnLoad, btnThem, btnXoa, btnSua, btnDong;
    EditText editTextId, editTextTen, editTextSoSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLopHoc = findViewById(R.id.lstviewLopHocId);
        arrayLopHoc = new ArrayList<>();
        lopHocAdapter = new LopHocAdapter(this, R.layout.list_item_layout, arrayLopHoc);
        lvLopHoc.setAdapter(lopHocAdapter);

        lopHocDatabase = new LopHocDatabase(this, "QuanLyLopHoc.sqlite", null, 1);

        String sqlDrop = "DROP TABLE IF EXISTS LopHoc";
        lopHocDatabase.QueryData(sqlDrop);

        String sql = "CREATE TABLE IF NOT EXISTS LopHoc(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten NVARCHAR(80), SoSV INTEGER)";
        lopHocDatabase.QueryData(sql);

        InitDataLopHoc();

        InitWidgets();

        btnLoad = findViewById(R.id.btnLoadId);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDataDanhBa();
            }
        });

        btnThem = findViewById(R.id.btnThemId);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strId = editTextId.getText().toString();
                String strTen = editTextTen.getText().toString();
                String strSoSV = editTextSoSV.getText().toString();

                if (strId.isEmpty() || strTen.isEmpty() || strSoSV.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin trước khi thêm!", Toast.LENGTH_SHORT).show();
                } else {
                    int Id = Integer.parseInt(strId);
                    if (isIdExists(Id)) {
                        Toast.makeText(MainActivity.this, "ID đã tồn tại.Vui lòng nhập một ID khác!", Toast.LENGTH_SHORT).show();
                    } else {
                        InsertLopHoc(Id, strTen, strSoSV);
                        LoadDataDanhBa();
                        ClearData();
                        Toast.makeText(MainActivity.this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnSua = findViewById(R.id.btnSuaId);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strId = editTextId.getText().toString();
                String strTen = editTextTen.getText().toString();
                String strSoSV = editTextSoSV.getText().toString();

                if (strId.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID trước khi sửa!", Toast.LENGTH_SHORT).show();
                } else if (strTen.isEmpty() || strSoSV.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin trước khi sửa!", Toast.LENGTH_SHORT).show();
                } else {
                    int Id = Integer.parseInt(strId);
                    if (isIdExists(Id)) {
                        UpdateLopHoc(Id, strTen, strSoSV);
                        LoadDataDanhBa();
                        ClearData();
                        Toast.makeText(MainActivity.this, "Đã sửa thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "ID không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnXoa = findViewById(R.id.btnXoaId);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strId = editTextId.getText().toString();
                if (strId.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ID trước khi xoá!", Toast.LENGTH_SHORT).show();
                } else {
                    int Id = Integer.valueOf(strId);
                    if (isIdExists(Id)) {
                        DeleteLopHoc(Id);
                        LoadDataDanhBa();
                        ClearData();
                        Toast.makeText(MainActivity.this, "Đã xoá thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "ID không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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
        editTextSoSV = findViewById(R.id.edittxtSoSVId);
    }

    private void InitDataLopHoc() {
        String sqlCheckData = "SELECT COUNT(*) FROM LopHoc";
        Cursor cursor = lopHocDatabase.QueryGetData(sqlCheckData);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if(count==0){
            String sqlinsert1 = "insert into LopHoc values(null,'Lập trình Java',35)";
            String sqlinsert2 = "insert into LopHoc values(null,'Thiết kế Web','43')";
            String sqlinsert3 = "insert into LopHoc values(null,'Đồ hoạ đa phương tiện','60')";

            lopHocDatabase.QueryData(sqlinsert1);
            lopHocDatabase.QueryData(sqlinsert2);
            lopHocDatabase.QueryData(sqlinsert3);
        }
    }
    public void LoadDataDanhBa(){
        String sqlSelect = "SELECT * FROM LopHoc";
        Cursor dataLopHoc = lopHocDatabase.QueryGetData(sqlSelect);
        arrayLopHoc.clear();
        while (dataLopHoc.moveToNext()){
            int Id = dataLopHoc.getInt(0);
            String strTen = dataLopHoc.getString(1);
            int strSoDT = dataLopHoc.getInt(2);

            arrayLopHoc.add(new LopHoc(Id,strTen,strSoDT));
        }
        lopHocAdapter.notifyDataSetChanged();
    }
    public void InsertLopHoc(int Id, String strTen, String strSoSV){
        String sqlInsert = "INSERT INTO LopHoc VALUES(" + Id + ",'" +strTen + "','" + strSoSV + "')";
        lopHocDatabase.QueryData(sqlInsert);
    }
    public void UpdateLopHoc(int Id, String strTen, String strSoSV){
        String sqlUpdate = "UPDATE LopHoc SET Ten='" +strTen + "',SoSV='" + strSoSV + "' WHERE Id=" + Id;
        lopHocDatabase.QueryData(sqlUpdate);
    }
    public void DeleteLopHoc(int Id){
        String sqlDelete = "DELETE FROM LopHoc WHERE Id=" + Id;
        lopHocDatabase.QueryData(sqlDelete);
    }
    private boolean isIdExists(int Id){
        String sqlCheckId = "SELECT * FROM LopHoc WHERE Id =" +Id;
        Cursor cursor = lopHocDatabase.QueryGetData(sqlCheckId);
        boolean exists = cursor.moveToNext();
        cursor.close();
        return exists;
    }
    public void ClearData(){
        editTextId.setText("");
        editTextTen.setText("");
        editTextSoSV.setText("");
    }

}