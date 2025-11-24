package model;

import java.io.Serializable;

public class ChiTietHoaDon implements Serializable {

    private SanPham sanPham;
    private int soLuong;
    private double donGia;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon(SanPham sanPham, int soLuong) {
    this.sanPham = sanPham;
    this.soLuong = soLuong;
    this.donGia = sanPham.getGia();
}

public SanPham getSanPham() { return sanPham; }
public int getSoLuong() { return soLuong; }
public double getDonGia() { return donGia; }

public double getThanhTien() {
    return donGia * soLuong;
}

@Override
public String toString() {
    return sanPham.getTenSP() + " | SL: " + soLuong + " | Đơn giá: " + donGia + " | Thành tiền: " + getThanhTien();
}
}
