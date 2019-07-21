package com.sip.kelolaapp;

public class DataProses {

    String transaksi_no;
    String transaksi_date;
    String receive_qty;
    String createdTimeStamp;

    public DataProses(String transaksi_no,String transaksi_date, String receive_qty, String createdTimeStamp){
        this.transaksi_no = transaksi_no;
        this.transaksi_date = transaksi_date;
        this.receive_qty = receive_qty;
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

    public String getReceive_qty() {
        return receive_qty;
    }

    public void setReceive_qty(String receive_qty) {
        this.receive_qty = receive_qty;
    }

    public String getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(String createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}
