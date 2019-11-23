package com.egesenkul.teknotraandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    ArrayList<WordpressApi> dd;
    WordpressimageApi image;
    ArrayList<yaziBaslik> yazilar;
    int ddIndex;
    CustomAdapter customAdapter;
    NavigationView navigationView;
    boolean yeniYaziEkleniyor;
    int yaziSayfasi;
    ListView listView;

    GifImageView splashLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialization();

        tumYazilariAl("https://teknotra.com/wp-json/wp/v2/posts?page=");
    }

    private void tumYazilariAl(String url) {
        try{
            ddIndex = 0;
            final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
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
                final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_all);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.open_drawer,R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void listeyiYenile() {
        onNavigationItemSelected(navigationView.getCheckedItem());
    }

    private void ListeTiklama(int index){
        Intent intent = new Intent(MainActivity.this,yaziEkrani.class);
        intent.putExtra("id", ((int)(dd.get(index).getId()))+"");
        intent.putExtra("featured_media",((int)dd.get(index).getFeatured_media())+"");
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Tıklanan eleman " + (index+1) + ". eleman", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        yaziSayfasi = 1;
        yazilar = new ArrayList<yaziBaslik>();
        yazilar.clear();
        customAdapter.notifyDataSetChanged();
        splashLogo.setVisibility(View.VISIBLE);
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

    class CustomAdapter extends BaseAdapter{

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
