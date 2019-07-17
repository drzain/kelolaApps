package com.sip.kelolaapp;

public class DataNote {

    String code,date,qty;

    public DataNote(String code, String date, String qty){

        this.code = code;
        this.date = date;
        this.qty = qty;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
