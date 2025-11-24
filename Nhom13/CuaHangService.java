package service;

import model.*;
import utils.FileUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuaHangService {

    private List<SanPham> dsSanPham;
    private List<KhachHang> dsKhachHang;
    private List<HoaDon> dsHoaDon;

    private final String FILE_SP = "data/sanpham.dat";
    private final String FILE_KH = "data/khachhang.dat";
    private final String FILE_HD = "data/hoadon.dat";

    public CuaHangService() {
        dsSanPham = (List<SanPham>) FileUtil.docFile(FILE_SP);
        dsKhachHang = (List<KhachHang>) FileUtil.docFile(FILE_KH);
        dsHoaDon   = (List<HoaDon>) FileUtil.docFile(FILE_HD);

        if (dsSanPham == null) dsSanPham = new ArrayList<>();
        if (dsKhachHang == null) dsKhachHang = new ArrayList<>();
        if (dsHoaDon == null) dsHoaDon = new ArrayList<>();
    }

    public void luu() {
        FileUtil.ghiFile(FILE_SP, dsSanPham);
        FileUtil.ghiFile(FILE_KH, dsKhachHang);
        FileUtil.ghiFile(FILE_HD, dsHoaDon);
    }

    // ==================== SẢN PHẨM ==========================
    public boolean themSanPham(SanPham sp) {
    if (timSanPhamTheoMa(sp.getMaSP()) != null)
    return false;
    dsSanPham.add(sp);
    luu();
    return true;
}

public SanPham timSanPhamTheoMa(String ma) {
    return dsSanPham.stream().filter(sp -> sp.getMaSP().equalsIgnoreCase(ma)).findFirst().orElse(null);
}

public List<SanPham> timSanPhamTheoTen(String ten) {
    List<SanPham> kq = new ArrayList<>();
    for (SanPham sp : dsSanPham)
    if (sp.getTenSP().toLowerCase().contains(ten.toLowerCase()))
        kq.add(sp);
    return kq;
}

public boolean xoaSanPham(String ma) {
    SanPham sp = timSanPhamTheoMa(ma);
    if (sp == null) return false;
    dsSanPham.remove(sp);
    luu();
    return true;
}

public boolean capNhatSanPham(SanPham sp) {
    SanPham old = timSanPhamTheoMa(sp.getMaSP());
    if (old == null) return false;
    old.setTenSP(sp.getTenSP());
    old.setLoai(sp.getLoai());
    old.setGia(sp.getGia());
    old.setSoLuong(sp.getSoLuong());
    luu();
    return true;
}

public List<SanPham> getDsSanPham() { return dsSanPham; }

// ==================== KHÁCH HÀNG ==========================
public boolean themKhachHang(KhachHang kh) {
    if (timKhachHangTheoMa(kh.getMaKH()) != null)
        return false;
    dsKhachHang.add(kh);
    luu();
    return true;
}

public KhachHang timKhachHangTheoMa(String ma) {
    return dsKhachHang.stream().filter(kh -> kh.getMaKH().equalsIgnoreCase(ma)).findFirst().orElse(null);
}

public boolean xoaKhachHang(String ma) {
    KhachHang kh = timKhachHangTheoMa(ma);
    if (kh == null) return false;
    dsKhachHang.remove(kh);
    luu();
    return true;
}

public List<KhachHang> getDsKhachHang() { return dsKhachHang; }

// ==================== HÓA ĐƠN ==========================
public HoaDon taoHoaDon(String maHD, String maKH) {
    KhachHang kh = timKhachHangTheoMa(maKH);
    if (kh == null) return null;

    HoaDon hd = new HoaDon(maHD, LocalDate.now(), kh);
    dsHoaDon.add(hd);
    luu();
    return hd;
}

public boolean themSPVaoHoaDon(String maHD, String maSP, int soLuong) {
    HoaDon hd = timHoaDon(maHD);
    SanPham sp = timSanPhamTheoMa(maSP);

    if (hd == null || sp == null) return false;
    if (sp.getSoLuong() < soLuong) return false;

    // trừ kho
    sp.setSoLuong(sp.getSoLuong() - soLuong);

    ChiTietHoaDon ct = new ChiTietHoaDon(sp, soLuong);
    hd.themChiTiet(ct);
    luu();
    return true;
}

public HoaDon timHoaDon(String maHD) {
    return dsHoaDon.stream().filter(h -> h.getMaHD().equalsIgnoreCase(maHD)).findFirst().orElse(null);
}

public List<HoaDon> getDsHoaDon() { return dsHoaDon; }
}
