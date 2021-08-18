package com.example.da1_shoppingcart.model;

public class MacHang2 {
    String TenMacHang;
    String MaSanPham;
    String image;
    String GiaBan;
    String SoLuong;
    String MaMacHang;

    public MacHang2() {
    }

    @Override
    public String toString() {
        return "MacHang2{" +
                "TenMacHang='" + TenMacHang + '\'' +
                ", MaSanPham='" + MaSanPham + '\'' +
                ", image='" + image + '\'' +
                ", GiaBan='" + GiaBan + '\'' +
                ", SoLuong='" + SoLuong + '\'' +
                ", MaMacHang='" + MaMacHang + '\'' +
                '}';
    }

    public MacHang2(String tenMacHang,String maSanPham,String image,String giaBan,String soLuong) {
        TenMacHang = tenMacHang;
        MaSanPham = maSanPham;
        this.image = image;
        GiaBan = giaBan;
        SoLuong = soLuong;
    }

    public MacHang2(String tenMacHang,String maSanPham,String image,String giaBan,String soLuong,String maMacHang) {
        TenMacHang = tenMacHang;
        MaSanPham = maSanPham;
        this.image = image;
        GiaBan = giaBan;
        SoLuong = soLuong;
        MaMacHang = maMacHang;
    }

    public MacHang2(String tenMacHang,String image) {
        TenMacHang = tenMacHang;
        this.image = image;
    }

    public String getTenMacHang() {
        return TenMacHang;
    }

    public void setTenMacHang(String tenMacHang) {
        TenMacHang = tenMacHang;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(String giaBan) {
        GiaBan = giaBan;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getMaMacHang() {
        return MaMacHang;
    }

    public void setMaMacHang(String maMacHang) {
        MaMacHang = maMacHang;
    }
}
