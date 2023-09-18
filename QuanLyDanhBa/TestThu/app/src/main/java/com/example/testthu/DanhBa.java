package com.example.testthu;

public class DanhBa {
    int Id;                // Mã danh bạ
    String Ten;           // Tên danh bạ
    String SoDienThoai;   // Số điện thoại danh bạ

    public DanhBa(int id, String ten, String soDienThoai){
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

    public String toString(){
        return  "" + Id + "";
    }
}
