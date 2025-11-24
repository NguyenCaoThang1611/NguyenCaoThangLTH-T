package model;

import java.io.Serializable;

public class KhachHang implements Serializable {

    private String maKH;
    private String tenKH;
    private String sdt;
    private String email;

    public KhachHang() {}

    public KhachHang(String maKH, String tenKH, String sdt, String email) {
    this.maKH = maKH;
    this.tenKH = tenKH;
    this.sdt = sdt;
    this.email = email;
}

public String getMaKH() { return maKH; }
public void setMaKH(String maKH) { this.maKH = maKH; }

public String getTenKH() { return tenKH; }
public void setTenKH(String tenKH) { this.tenKH = tenKH; }

public String getSdt() { return sdt; }
public void setSdt(String sdt) { this.sdt = sdt; }

public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }

@Override
public String toString() {
    return maKH + " | " + tenKH + " | " + sdt + " | " + email;
}
}
