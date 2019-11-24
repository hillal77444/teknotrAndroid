package com.egesenkul.teknotraandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class mainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<WordpressApi> dd;
    WordpressimageApi image;
    ArrayList<yaziBaslik> yazilar;
    int ddIndex;
    boolean yeniYaziEkleniyor;
    int yaziSayfasi;
    ListView listView;
    CustomAdapter customAdapter;
    private AdView adView;
    GifImageView splashLogo;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Initialization();

        MobileAds.initialize(this,"ca-app-pub-8924915962354588~2158487187");
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?page=");
    }

    private void Initialization() {
        splashLogo = (GifImageView)findViewById(R.id.imageView2);
        ddIndex=0;
        yaziSayfasi = 1;
        yeniYaziEkleniyor = false;
        yazilar = new ArrayList<yaziBaslik>();
        listView = (ListView)findViewById(R.id.ListView);
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListeTiklama(position);
            }
        });
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                yazilar = new ArrayList<yaziBaslik>();
                yazilar.clear();
                customAdapter.notifyDataSetChanged();
                splashLogo.setVisibility(View.VISIBLE);
                listeyiYenile();
                pullToRefresh.setRefreshing(false);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(view.getLastVisiblePosition() == yazilar.size()-1 && yazilar != null && yazilar.size() != 0
                        && !yeniYaziEkleniyor){
                    listView.setVisibility(View.GONE);
                    splashLogo.setVisibility(View.VISIBLE);
                    yeniYaziEkleniyor = true;
                    yaziSayfasi++;
                    yaziEkle(navigationView.getCheckedItem());
                }
            }
        });
        navigationView.setCheckedItem(R.id.nav_all);
    }

    private void tumYazilariAl(String url) {
        try{
            ddIndex = 0;
            final RequestQueue queue = Volley.newRequestQueue(mainMenu.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url+yaziSayfasi,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<ArrayList<WordpressApi>>() {
                            }.getType();
                            dd = gson.fromJson(response, listType);

                            ResimleriAl();
                            queue.stop();
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            queue.add(stringRequest);
        }
        catch (Exception ex){

        }
    }

    private void ResimleriAl() {
        try{
            final RequestQueue queue = Volley.newRequestQueue(mainMenu.this);
            if((int)dd.get(ddIndex).getFeatured_media() == 0){
                yaziBaslik temp = new yaziBaslik(dd.get(ddIndex).getSlug().replace("-"," "),dd.get(ddIndex).getDate(),"-1");
                yazilar.add(temp);
                ddIndex++;
                if(ddIndex < dd.size()-1)
                    ResimleriAl();
                else
                    return;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://teknotra.com/wp-json/wp/v2/media/"+ ((int)dd.get(ddIndex).getFeatured_media()),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<WordpressimageApi>() {
                            }.getType();
                            image = gson.fromJson(response, listType);
                            yaziBaslik temp = new yaziBaslik(dd.get(ddIndex).getSlug().replace("-"," "),dd.get(ddIndex).getDate(),image.getSource_url());
                            yazilar.add(temp);
                            queue.stop();
                            if(ddIndex<dd.size()-1){
                                ddIndex++;
                                ResimleriAl();
                            }
                            else{
                                Log.e("OK","liste tamam "+yazilar.size());
                                customAdapter.notifyDataSetChanged();
                                splashLogo.setVisibility(View.GONE);
                                if(listView.getVisibility() == View.GONE){
                                    listView.setVisibility(View.VISIBLE);
                                }
                                yeniYaziEkleniyor = false;
                                return;
                            }
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error",error.getMessage());
                }
            });
            queue.add(stringRequest);
        }
        catch (Exception ex){
            Log.e("error",ex.getMessage());
            customAdapter.notifyDataSetChanged();
            splashLogo.setVisibility(View.GONE);
            if(listView.getVisibility() == View.GONE){
                listView.setVisibility(View.VISIBLE);
            }
            yeniYaziEkleniyor = false;
        }
    }

    public void yaziEkle(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.nav_all:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?page=");
                break;
            case R.id.nav_car:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=14&page=");
                break;
            case R.id.nav_mobile:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=7&page=");
                break;
            case R.id.software_development:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=4&page=");
                break;
            case R.id.social_media:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=9&page=");
                break;
            case R.id.game:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=117&page=");
                break;
            case R.id.other:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=11&page=");
                break;
            case R.id.nav_computer:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=150&page=");
                break;
            case R.id.web:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.teknotra.com"));
                startActivity(browserIntent);
                break;
        }
    }

    private void listeyiYenile() {
        onNavigationItemSelected(navigationView.getCheckedItem());
    }

    private void ListeTiklama(int index){
        Intent intent = new Intent(mainMenu.this,yaziEkrani.class);
        intent.putExtra("id", ((int)(dd.get(index).getId()))+"");
        intent.putExtra("featured_media",((int)dd.get(index).getFeatured_media())+"");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Uygulamayı Kapatmak")
                    .setMessage("Uygulamayı kapatmak istediğinize emin misiniz?")
                    .setPositiveButton("Evet", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("Hayır", null)
                    .show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        yaziSayfasi = 1;
        yazilar = new ArrayList<yaziBaslik>();
        yazilar.clear();
        customAdapter.notifyDataSetChanged();
        splashLogo.setVisibility(View.VISIBLE);
        switch (item.getItemId()){
            case R.id.nav_all:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?page=");
                break;
            case R.id.nav_car:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=14&page=");
                break;
            case R.id.nav_mobile:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=7&page=");
                break;
            case R.id.software_development:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=4&page=");
                break;
            case R.id.social_media:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=9&page=");
                break;
            case R.id.game:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=117&page=");
                break;
            case R.id.other:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=11&page=");
                break;
            case R.id.nav_computer:
                tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?categories=150&page=");
                break;
            case R.id.nav_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Bu uygulamayı indirmeni tavsiye ediyorum " + "https://play.google.com/store/apps/details?id=com.egesenkul.teknotraandroid";
                String shareSub = "TEKNOTRA";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
            case R.id.web:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.teknotra.com"));
                startActivity(browserIntent);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String TarihFormatiniDuzenle(String tarih)
    {
        String tempTarih = tarih.replace("T","-").replace(":","-");
        String[] temp = tempTarih.split("-");
        return temp[2]+"."+temp[1]+"."+temp[0]+" "+temp[3]+":"+temp[4];
    }

    public String SlugToTitle(String slug){
        String buyukString = slug.charAt(0)+"";
        buyukString = buyukString.toUpperCase();
        buyukString+=slug.substring(1);
        String sonuc=buyukString.charAt(0)+"";
        for(int i=1;i<buyukString.length();i++){
            if(buyukString.charAt(i-1)==' '){
                sonuc += (buyukString.charAt(i)+"").toUpperCase();
            }
            else{
                sonuc += buyukString.charAt(i);
            }
        }
        return sonuc;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(yazilar != null)
                return yazilar.size();
            else
                return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout,null);

            ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);
            TextView baslik = (TextView)convertView.findViewById(R.id.textView_baslik);
            TextView tarih = (TextView)convertView.findViewById(R.id.textView_tarih);

            baslik.setText(SlugToTitle(yazilar.get(position).getBaslik()));
            tarih.setText(TarihFormatiniDuzenle(yazilar.get(position).getTarih()));

            if(yazilar.get(position).getResimAdresi() != "-1")
                Picasso.get().load(yazilar.get(position).getResimAdresi()).into(imageView);

            return convertView;
        }
    }

}
