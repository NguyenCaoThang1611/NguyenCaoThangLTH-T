package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HoaDon implements Serializable {

    private String maHD;
    private LocalDate ngayLap;
    private KhachHang khachHang;
    private List<ChiTietHoaDon> dsChiTiet;

    public HoaDon(String maHD, LocalDate ngayLap, KhachHang khachHang) {
    this.maHD = maHD;
    this.ngayLap = ngayLap;
    this.khachHang = khachHang;
    this.dsChiTiet = new ArrayList<>();
}

public String getMaHD() { return maHD; }
public LocalDate getNgayLap() { return ngayLap; }
public KhachHang getKhachHang() { return khachHang; }
public List<ChiTietHoaDon> getDsChiTiet() { return dsChiTiet; }

public void themChiTiet(ChiTietHoaDon ct) { dsChiTiet.add(ct); }

public double getTongTien() {
    return dsChiTiet.stream().mapToDouble(ChiTietHoaDon::getThanhTien).sum();
}

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Hóa đơn: ").append(maHD)
        .append(" | Ngày: ").append(ngayLap)
        .append(" | KH: ").append(khachHang.getTenKH())
        .append("\n");

    for (ChiTietHoaDon ct : dsChiTiet)
    sb.append("   - ").append(ct).append("\n");

    sb.append("Tổng tiền: ").append(getTongTien());
    return sb.toString();
}
}
