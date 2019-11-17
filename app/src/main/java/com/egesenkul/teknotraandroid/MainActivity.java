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
import android.view.Window;
import android.view.WindowManager;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    ArrayList<WordpressApi> dd;
    WordpressimageApi image;
    ArrayList<yaziBaslik> yazilar;
    int ddIndex;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tamEkranYap();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialization();

        tumYazilariAl();
    }

    private void tumYazilariAl() {
        try{
            final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://teknotra.com/wp-json/wp/v2/posts",
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
                                if(ddIndex<dd.size()){
                                    ddIndex++;
                                    ResimleriAl();
                                }
                                else{
                                    Log.e("OK","liste tamam "+yazilar.size());
                                    customAdapter.notifyDataSetChanged();
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
            }
    }

    private void Initialization() {
        ddIndex=0;
        yazilar = new ArrayList<yaziBaslik>();
        ListView listView = (ListView)findViewById(R.id.ListView);
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
                listeyiYenile();
                pullToRefresh.setRefreshing(false);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_all);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.open_drawer,R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void listeyiYenile() {
        tumYazilariAl();
    }

    private void ListeTiklama(int index){
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

    protected void tamEkranYap(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_all:
                Toast.makeText(getApplicationContext(), "Hepsi", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_car:
                Toast.makeText(getApplicationContext(), "Araba", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_mobile:
                Toast.makeText(getApplicationContext(), "Mobil", Toast.LENGTH_LONG).show();
                break;
            case R.id.software_development:
                Toast.makeText(getApplicationContext(), "Yazılım Geliştirme", Toast.LENGTH_LONG).show();
                break;
            case R.id.social_media:
                Toast.makeText(getApplicationContext(), "Sosyal Medya", Toast.LENGTH_LONG).show();
                break;
            case R.id.game:
                Toast.makeText(getApplicationContext(), "Oyun", Toast.LENGTH_LONG).show();
                break;
            case R.id.other:
                Toast.makeText(getApplicationContext(), "Diğer", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_computer:
                Toast.makeText(getApplicationContext(), "Bilgisayar", Toast.LENGTH_LONG).show();
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

            Picasso.get().load(yazilar.get(position).getResimAdresi()).into(imageView);

            return convertView;
        }
    }
}
