package com.example.quanlylophoc;

public class LopHoc {
    int Id;
    String Ten;
    int SoSV;

    public LopHoc(int id, String ten, int soSV) {
        Id = id;
        Ten = ten;
        SoSV = soSV;
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

    public int getSoSV() {
        return SoSV;
    }

    public void setSoSV(int soSV) {
        SoSV = soSV;
    }

    @Override
    public String toString() {
        return "" + Id + "";
    }
}
