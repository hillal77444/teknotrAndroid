package com.egesenkul.teknotraandroid;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class yaziEkrani extends AppCompatActivity {

    WebView yaziIcerik;
    WordpressApi dd;
    String feature_media_id;
    WordpressimageApi featuredMedia;

    String rendered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yazi_ekrani);

        Intent intent = getIntent();
        String yaziID = intent.getStringExtra("id");
        feature_media_id = intent.getStringExtra("featured_media");

        rendered = "";
        yaziIcerik = (WebView) findViewById(R.id.yaziHTML);

        yaziIcerikAl(yaziID);
    }

    private void yaziIcerikAl(String id) {
        try{
            final RequestQueue queue = Volley.newRequestQueue(yaziEkrani.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://teknotra.com/wp-json/wp/v2/posts/"+id,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<WordpressApi>() {
                            }.getType();
                            dd = gson.fromJson(response, listType);
                            JSONObject reader = null;
                            try {
                                reader = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject sys  = reader.getJSONObject("content");
                                if(!sys.getString("rendered").contains(".jpg")){
                                    GetFeaturedMedia();
                                }
                                rendered = sys.getString("rendered");

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    yaziIcerik.loadData(rendered,"text/html", "UTF-8");
                                } else {
                                    yaziIcerik.loadData(rendered,"text/html", "UTF-8");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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

    private void GetFeaturedMedia() {
        try {
            final RequestQueue queue = Volley.newRequestQueue(yaziEkrani.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://teknotra.com/wp-json/wp/v2/media/" + feature_media_id,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<WordpressimageApi>() {
                            }.getType();
                            featuredMedia = gson.fromJson(response, listType);

                            yaziIcerik.loadData("<img style='height: auto; width: 100%; object-fit: contain'" +
                                    " src='"+featuredMedia.getSource_url()+"'/>" + rendered,"text/html", "UTF-8");
                            queue.stop();
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            queue.add(stringRequest);
        } catch (Exception ex) {
        }
    }
}
