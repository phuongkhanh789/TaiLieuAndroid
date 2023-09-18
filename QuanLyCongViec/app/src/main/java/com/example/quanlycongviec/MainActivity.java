package com.example.quanlycongviec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CongViecDatabase congViecDatabase;
    ListView lvCongViec;
    ArrayList arrayCongViec;
    CongViecAdapter congViecAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCongViec = (ListView) findViewById(R.id.lstviewCongViecId);
        arrayCongViec = new ArrayList<CongViec>();
        congViecAdapter = new CongViecAdapter(this,R.layout.list_item_layout,arrayCongViec);
        lvCongViec.setAdapter(congViecAdapter);
        congViecDatabase = new CongViecDatabase(this,"QuanLyCongViec.sqlite",null,1);



        String sqlDrop = "DROP TABLE IF EXISTS CongViec";
        congViecDatabase.QueryData(sqlDrop);

        String sql = "CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten nvarchar(80), ThoiGianThucHien nvarchar(80))";
        congViecDatabase.QueryData(sql);
        InitDataCongViec();
        LoadDataCongViec();
    }

    void InitDataCongViec() {
        String sqlinsert1 = "insert into CongViec values(null,'Làm bài tập Android','Thứ 7,CN hàng tuần')";
        String sqlinsert2 = "insert into CongViec values(null,'Viết báo cáo đồ án 2','Chiều thứ 2,3,4 hàng tuần')";
        String sqlinsert3 = "insert into CongViec values(null,'Học tiếng Anh','Tối Thứ 2,4,6 hàng tuần')";
        String sqlinsert4 = "insert into CongViec values(null,'Đi học chính khoá','Sáng thứ 2,3,4.Chiều T5,6 hàng tuần')";
        String sqlinsert5 = "insert into CongViec values(null,'Tìm công việc làm thêm','Sáng thứ 5,6 hàng tuần')";
        String sqlinsert6 = "insert into CongViec values(null,'Sinh hoạt câu lạc bộ Kỹ năng','Tối thứ 5 hàng tuần')";

        congViecDatabase.QueryData(sqlinsert1);
        congViecDatabase.QueryData(sqlinsert2);
        congViecDatabase.QueryData(sqlinsert3);
        congViecDatabase.QueryData(sqlinsert4);
        congViecDatabase.QueryData(sqlinsert5);
        congViecDatabase.QueryData(sqlinsert6);
    }

    private void LoadDataCongViec() {
        String sqlSelect = "SELECT * FROM CongViec";
        Cursor dataCongViec = congViecDatabase.QueryGetData(sqlSelect);
        arrayCongViec.clear();
        while (dataCongViec.moveToNext()){
            int Id = dataCongViec.getInt(0);
            String strTen = dataCongViec.getString(1);
            String strTGTH = dataCongViec.getString(2);
            arrayCongViec.add(new CongViec(Id,strTen,strTGTH));
        }
        congViecAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int itemId = item.getItemId();
        if(itemId==R.id.settinsId){
            Toast.makeText(MainActivity.this,"Bạn chọn mục cấu hình",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (itemId == R.id.CreateId) {
            // Handle the "Thêm Công việc mới" menu item click
            Intent intent = new Intent(MainActivity.this, CongViecMoi.class);
            startActivity(intent);
            return true;
        }
        else if(itemId==R.id.ExitId){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}