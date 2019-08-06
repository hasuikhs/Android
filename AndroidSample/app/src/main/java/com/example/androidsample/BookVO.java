package com.example.androidsample;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

public class BookVO {
    private String bimgurl;
    private String btitle;
    private String bauthor;
    private String bprice;
    private Drawable drawable;      // 도서이미지에 대한 drawable 객체

    public BookVO() {
    }

    public BookVO(String bimgurl, String btitle, String bauthor, String bprice) {
        this.bimgurl = bimgurl;
        this.btitle = btitle;
        this.bauthor = bauthor;
        this.bprice = bprice;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getBimgurl() {
        return bimgurl;
    }

    public void setBimgurl(String bimgurl) {
        this.bimgurl = bimgurl;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public String getBprice() {
        return bprice;
    }

    public void setBprice(String bprice) {
        this.bprice = bprice;
    }

    public void drawableFromURL(){
        Drawable d = null;
        try {
            InputStream is =(InputStream)new URL(bimgurl).getContent();
            d = Drawable.createFromStream(is, "NAME");
            this.drawable = d;
        }catch (Exception e){
            Log.i("Error", e.toString());
        }
    }
}
