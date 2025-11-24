package model;

import java.io.Serializable;

public class SanPham implements Serializable {

    private String maSP;
    private String tenSP;
    private String loai;      // Điện thoại / Phụ kiện
    private double gia;
    private int soLuong;

    public SanPham() {}

    public SanPham(String maSP, String tenSP, String loai, double gia, int soLuong) {
    this.maSP = maSP;
    this.tenSP = tenSP;
    this.loai = loai;
    this.gia = gia;
    this.soLuong = soLuong;
}

public String getMaSP() { return maSP; }
public void setMaSP(String maSP) { this.maSP = maSP; }

public String getTenSP() { return tenSP; }
public void setTenSP(String tenSP) { this.tenSP = tenSP; }

public String getLoai() { return loai; }
public void setLoai(String loai) { this.loai = loai; }

public double getGia() { return gia; }
public void setGia(double gia) { this.gia = gia; }

public int getSoLuong() { return soLuong; }
public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

@Override
public String toString() {
    return maSP + " | " + tenSP + " | " + loai + " | " + gia + " | tồn: " + soLuong;
}
}
