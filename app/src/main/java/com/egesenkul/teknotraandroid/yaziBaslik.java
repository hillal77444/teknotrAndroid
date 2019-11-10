package com.egesenkul.teknotraandroid;

import java.util.Date;

public class yaziBaslik {

    private String baslik;
    private Date tarih;
    private String resimAdresi;

    public yaziBaslik(String title,Date createDate,String urlImage){
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

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getResimAdresi() {
        return resimAdresi;
    }

    public void setResimAdresi(String resimAdresi) {
        this.resimAdresi = resimAdresi;
    }
}


