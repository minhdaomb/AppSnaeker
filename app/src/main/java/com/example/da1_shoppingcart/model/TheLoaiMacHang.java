package com.example.da1_shoppingcart.model;

public class TheLoaiMacHang {
    public String maMatHang,tenMatHang,moTa;

    public TheLoaiMacHang() {
    }
    @Override
    public String toString(){return maMatHang+"|"+tenMatHang+"";}

    public TheLoaiMacHang(String maMatHang,String tenMatHang,String moTa) {
        this.maMatHang = maMatHang;
        this.tenMatHang = tenMatHang;
        this.moTa = moTa;
    }

    public String getMaMatHang() {
        return maMatHang;
    }

    public void setMaMatHang(String maMatHang) {
        this.maMatHang = maMatHang;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
