package com.sip.kelolaapp;

public class DataUnload {

    String transport_no;
    String transport_date;
    String transport_qty;
    String nopol;

    public DataUnload(String transport_no,String transport_date,String transport_qty,String nopol){
        this.transport_no = transport_no;
        this.transport_date = transport_date;
        this.transport_qty = transport_qty;
        this.nopol = nopol;
    }

    public String getTransport_no() {
        return transport_no;
    }

    public void setTransport_no(String transport_no) {
        this.transport_no = transport_no;
    }

    public String getTransport_date() {
        return transport_date;
    }

    public void setTransport_date(String transport_date) {
        this.transport_date = transport_date;
    }

    public String getTransport_qty() {
        return transport_qty;
    }

    public void setTransport_qty(String transport_qty) {
        this.transport_qty = transport_qty;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }
}
