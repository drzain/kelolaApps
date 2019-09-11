package com.sip.kelolaapp;

public class DataBerita {

    String title, pubDate, newslink, gambar;

    public DataBerita(String title, String pubDate, String newslink, String gambar){
        this.title = title;
        this.pubDate = pubDate;
        this.newslink = newslink;
        this.gambar = gambar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getNewslink() {
        return newslink;
    }

    public void setNewslink(String newslink) {
        this.newslink = newslink;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
