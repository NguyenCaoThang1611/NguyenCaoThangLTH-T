import model.*;
import service.CuaHangService;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static CuaHangService sv = new CuaHangService();

    public static void main(String[] args) {

    while (true) {
    System.out.println("\n===== PHẦN MỀM QUẢN LÝ CỬA HÀNG ĐIỆN THOẠI =====");
    System.out.println("1. Quản lý sản phẩm");
    System.out.println("2. Quản lý khách hàng");
    System.out.println("3. Quản lý hóa đơn");
    System.out.println("0. Thoát");
    System.out.print("Chọn chức năng: ");
    String c = sc.nextLine();

    switch (c) {
    case "1": menuSanPham(); break;
    case "2": menuKhachHang(); break;
    case "3": menuHoaDon(); break;
    case "0": System.out.println("Đã thoát!"); return;
    default: System.out.println("Sai lựa chọn!");
    }
}
}

// ================= MENU SẢN PHẨM =================
static void menuSanPham() {
    while (true) {
        System.out.println("\n--- QUẢN LÝ SẢN PHẨM ---");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Sửa sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Tìm theo tên");
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");
        String c = sc.nextLine();

        switch (c) {
            case "1":
                for (SanPham sp : sv.getDsSanPham()) System.out.println(sp);
                break;

            case "2":
                System.out.print("Mã SP: "); String ma = sc.nextLine();
                System.out.print("Tên SP: "); String ten = sc.nextLine();
                System.out.print("Loại (ĐT/PK): "); String loai = sc.nextLine();
                System.out.print("Giá: "); double gia = Double.parseDouble(sc.nextLine());
                System.out.print("Số lượng: "); int sl = Integer.parseInt(sc.nextLine());
                if (sv.themSanPham(new SanPham(ma, ten, loai, gia, sl)))
                    System.out.println("Thêm thành công!");
                else System.out.println("Mã SP đã tồn tại!");
                break;

            case "3":
                System.out.print("Nhập mã SP cần sửa: "); String mas = sc.nextLine();
                SanPham sp = sv.timSanPhamTheoMa(mas);
                if (sp == null) { System.out.println("Không tìm thấy!"); break; }

                System.out.print("Tên mới ("+sp.getTenSP()+"): ");
                String t = sc.nextLine(); if (!t.isEmpty()) sp.setTenSP(t);

                System.out.print("Loại mới ("+sp.getLoai()+"): ");
                String l = sc.nextLine(); if (!l.isEmpty()) sp.setLoai(l);

                System.out.print("Giá mới ("+sp.getGia()+"): ");
                String g = sc.nextLine(); if (!g.isEmpty()) sp.setGia(Double.parseDouble(g));

                System.out.print("Tồn kho mới ("+sp.getSoLuong()+"): ");
                String k = sc.nextLine(); if (!k.isEmpty()) sp.setSoLuong(Integer.parseInt(k));

                sv.capNhatSanPham(sp);
                System.out.println("Đã cập nhật!");
                break;

            case "4":
                System.out.print("Nhập mã SP cần xóa: ");
                if (sv.xoaSanPham(sc.nextLine())) System.out.println("Đã xóa!");
                else System.out.println("Không tìm thấy!");
                break;

            case "5":
                System.out.print("Nhập tên sản phẩm: ");
                for (SanPham s : sv.timSanPhamTheoTen(sc.nextLine())) System.out.println(s);
                break;

            case "0": return;
        }
    }
}

// ================= MENU KHÁCH HÀNG =================
static void menuKhachHang() {
    while (true) {
        System.out.println("\n--- QUẢN LÝ KHÁCH HÀNG ---");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm khách hàng");
        System.out.println("3. Xóa khách hàng");
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");
        String c = sc.nextLine();

        switch (c) {
            case "1":
                for (KhachHang kh : sv.getDsKhachHang()) System.out.println(kh);
                break;

            case "2":
                System.out.print("Mã KH: "); String ma = sc.nextLine();
                System.out.print("Tên KH: "); String ten = sc.nextLine();
                System.out.print("SĐT: "); String sdt = sc.nextLine();
                System.out.print("Email: "); String email = sc.nextLine();
                if (sv.themKhachHang(new KhachHang(ma, ten, sdt, email)))
                    System.out.println("Thêm thành công!");
                else System.out.println("Mã KH đã tồn tại!");
                break;

            case "3":
                System.out.print("Nhập mã KH cần xóa: ");
                if (sv.xoaKhachHang(sc.nextLine())) System.out.println("Đã xóa!");
                else System.out.println("Không tìm thấy!");
                break;

            case "0": return;
        }
    }
}

// ================= MENU HÓA ĐƠN =================
static void menuHoaDon() {
    while (true) {
        System.out.println("\n--- QUẢN LÝ HÓA ĐƠN ---");
        System.out.println("1. Tạo hóa đơn mới");
        System.out.println("2. Thêm sản phẩm vào hóa đơn");
        System.out.println("3. Xem hóa đơn");
        System.out.println("0. Quay lại");
        System.out.print("Chọn: ");
        String c = sc.nextLine();

        switch (c) {
            case "1":
                System.out.print("Mã hóa đơn: ");
                String ma = sc.nextLine();
                System.out.print("Mã khách hàng: ");
                String makh = sc.nextLine();
                HoaDon hd = sv.taoHoaDon(ma, makh);
                if (hd == null) System.out.println("KH không tồn tại!");
                else System.out.println("Tạo hóa đơn thành công!");
                break;

            case "2":
                System.out.print("Mã hóa đơn: "); String mahd = sc.nextLine();
                System.out.print("Mã SP: "); String masp = sc.nextLine();
                System.out.print("Số lượng: "); int sl = Integer.parseInt(sc.nextLine());
                if (sv.themSPVaoHoaDon(mahd, masp, sl))
                    System.out.println("Đã thêm vào hóa đơn!");
                else System.out.println("Lỗi (Hết hàng hoặc sai mã)!");
                break;

            case "3":
                System.out.print("Nhập mã hóa đơn: ");
                HoaDon h = sv.timHoaDon(sc.nextLine());
                if (h == null) System.out.println("Không tồn tại!");
                else System.out.println(h);
                break;

            case "0": return;
        }
    }
}
}
