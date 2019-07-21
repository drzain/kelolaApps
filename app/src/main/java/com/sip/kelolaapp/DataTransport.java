package com.sip.kelolaapp;

public class DataTransport {

    String receive_no;
    String transaksi_no;
    String transaksi_date;
    String receive_qty;
    String recycleble_qty;
    String end_qty;

    public DataTransport(String receive_no, String transaksi_no, String transaksi_date, String receive_qty, String recycleble_qty, String end_qty){
        this.receive_no = receive_no;
        this.transaksi_no = transaksi_no;
        this.transaksi_date = transaksi_date;
        this.receive_qty = receive_qty;
        this.recycleble_qty = recycleble_qty;
        this.end_qty = end_qty;
    }

    public String getReceive_no() {
        return receive_no;
    }

    public void setReceive_no(String receive_no) {
        this.receive_no = receive_no;
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

    public String getRecycleble_qty() {
        return recycleble_qty;
    }

    public void setRecycleble_qty(String recycleble_qty) {
        this.recycleble_qty = recycleble_qty;
    }

    public String getEnd_qty() {
        return end_qty;
    }

    public void setEnd_qty(String end_qty) {
        this.end_qty = end_qty;
    }
}
