package com.example.da1_shoppingcart.model;

public class GioHang {
    String TenSanPham,GiaSanPham,MaSanPham;

    public GioHang() {
    }

    public GioHang(String tenSanPham,String giaSanPham,String maSanPham) {
        TenSanPham = tenSanPham;
        GiaSanPham = giaSanPham;
        MaSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getGiaSanPham() {
        return GiaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        GiaSanPham = giaSanPham;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        MaSanPham = maSanPham;
    }
}
