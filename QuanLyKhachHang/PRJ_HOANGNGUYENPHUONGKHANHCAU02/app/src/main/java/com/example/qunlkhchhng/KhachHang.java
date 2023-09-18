package com.example.qunlkhchhng;

public class KhachHang {
    int Id;
    String Ten;
    String SoDienThoai;

    public KhachHang(int id, String ten, String soDienThoai) {
        Id = id;
        Ten = ten;
        SoDienThoai = soDienThoai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    @Override
    public String toString() {
        return "" + Id + "";
    }
}
