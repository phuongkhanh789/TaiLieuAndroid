package com.example.qunlkhchhng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    KhachHangDatabase khachHangDatabase;
    ListView lvKhachHang;
    ArrayList arrayKhachHang;
    KhachHangAdapter khachHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvKhachHang = (ListView) findViewById(R.id.lstviewKhachHangId);
        arrayKhachHang = new ArrayList<KhachHang>();
        khachHangAdapter = new KhachHangAdapter(this, R.layout.list_item_layout, arrayKhachHang);
        lvKhachHang.setAdapter(khachHangAdapter);
        khachHangDatabase = new KhachHangDatabase(this, "QuanLyKhachHang.sqlite", null, 1);

        String sqlDrop = "DROP TABLE IF EXISTS KhachHang"; // Use "IF EXISTS" to avoid errors if the table doesn't exist
        khachHangDatabase.QueryData(sqlDrop);

        String sql = "CREATE TABLE IF NOT EXISTS KhachHang(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten nvarchar(80), SoDienThoai nvarchar(15))";
        khachHangDatabase.QueryData(sql);

        InitDataKhachHang();

        LoadDataKhachHang(); // Add this line to load data into the ListView


    }

    void InitDataKhachHang() {
        String sqlinsert1 = "insert into KhachHang values(null, 'Anh Vinh','0913666888')";
        String sqlinsert2 = "insert into KhachHang values(null, 'Chị Hương','0933222555')";
        String sqlinsert3 = "insert into KhachHang values(null, 'Nguyễn Văn Khánh','0983999777')";

        khachHangDatabase.QueryData(sqlinsert1);
        khachHangDatabase.QueryData(sqlinsert2);
        khachHangDatabase.QueryData(sqlinsert3);
    }
    public void LoadDataKhachHang(){
        String sqlSelect = "SELECT * FROM KhachHang";
        Cursor dataKhachHang = khachHangDatabase.QueryGetData(sqlSelect);
        arrayKhachHang.clear();
        while (dataKhachHang.moveToNext()){
            int Id = dataKhachHang.getInt(0);
            String strTen = dataKhachHang.getString(1);
            String strSoDT = dataKhachHang.getString(2);
            arrayKhachHang.add(new KhachHang(Id,strTen,strSoDT));
        }
        khachHangAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int itemId = item.getItemId();
        if(itemId == R.id.settingsId) {
            Toast.makeText(MainActivity.this, "Bạn chọn mục cấu hình", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(itemId == R.id.ExitId) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}