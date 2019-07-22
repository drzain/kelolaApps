package com.sip.kelolaapp;

public class DataUmkm {

    String umkm_no;
    String umkm_date;
    String umkm_qty;

    public DataUmkm(String umkm_no,String umkm_date, String umkm_qty){
        this.umkm_no = umkm_no;
        this.umkm_date = umkm_date;
        this.umkm_qty = umkm_qty;
    }

    public String getUmkm_no() {
        return umkm_no;
    }

    public void setUmkm_no(String umkm_no) {
        this.umkm_no = umkm_no;
    }

    public String getUmkm_date() {
        return umkm_date;
    }

    public void setUmkm_date(String umkm_date) {
        this.umkm_date = umkm_date;
    }

    public String getUmkm_qty() {
        return umkm_qty;
    }

    public void setUmkm_qty(String umkm_qty) {
        this.umkm_qty = umkm_qty;
    }
}
