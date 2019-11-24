package com.egesenkul.teknotraandroid;

import java.util.Date;

public class yaziBaslik {

    private String baslik;
    private String tarih;
    private String resimAdresi;
    private String Id;
    private float featured_media;

    public yaziBaslik(String title,String createDate,String urlImage,String id,float fM){
        baslik = title;
        tarih = createDate;
        resimAdresi = urlImage;
        Id = id;
        featured_media = fM;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getResimAdresi() {
        return resimAdresi;
    }

    public void setResimAdresi(String resimAdresi) {
        this.resimAdresi = resimAdresi;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public float getFeatured_media() {
        return featured_media;
    }

    public void setFeatured_media(float featured_media) {
        this.featured_media = featured_media;
    }
}


