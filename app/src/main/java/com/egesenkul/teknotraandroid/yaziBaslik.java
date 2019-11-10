package com.egesenkul.teknotraandroid;

import java.util.Date;

public class yaziBaslik {

    private String baslik;
    private String tarih;
    private String resimAdresi;

    public yaziBaslik(String title,String createDate,String urlImage){
        baslik = title;
        tarih = createDate;
        resimAdresi = urlImage;
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
}


