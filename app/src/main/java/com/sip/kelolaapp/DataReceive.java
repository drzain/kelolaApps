package com.sip.kelolaapp;

public class DataReceive {

    String transaksi_no;
    String transaksi_date;
    String tipe_order;
    String waste_qty;
    String createdTimeStamp;

    public DataReceive(String transaksi_no,String transaksi_date, String tipe_order, String waste_qty, String createdTimeStamp){

        this.transaksi_no = transaksi_no;
        this.transaksi_date = transaksi_date;
        this.tipe_order = tipe_order;
        this.waste_qty = waste_qty;
        this.createdTimeStamp = createdTimeStamp;

    }

    public String getTransaksi_no() {
        return transaksi_no;
    }

    public void setTransaksi_no(String transaksi_no) {
        this.transaksi_no = transaksi_no;
    }

    public String getTransaksi_date() {
        return transaksi_date;
    }

    public void setTransaksi_date(String transaksi_date) {
        this.transaksi_date = transaksi_date;
    }

    public String getTipe_order() {
        return tipe_order;
    }

    public void setTipe_order(String tipe_order) {
        this.tipe_order = tipe_order;
    }

    public String getWaste_qty() {
        return waste_qty;
    }

    public void setWaste_qty(String waste_qty) {
        this.waste_qty = waste_qty;
    }

    public String getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(String createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}
