package com.example.quanlycongviec;

public class CongViec {
    int Id;
    String TenCV;
    String ThoiGianThucHien;

    public CongViec(int id, String tenCV, String thoiGianThucHien) {
        Id = id;
        TenCV = tenCV;
        ThoiGianThucHien = thoiGianThucHien;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }

    public String getThoiGianThucHien() {
        return ThoiGianThucHien;
    }

    public void setThoiGianThucHien(String thoiGianThucHien) {
        ThoiGianThucHien = thoiGianThucHien;
    }

    @Override
    public String toString() {
        return "" + Id + "";
    }
}
