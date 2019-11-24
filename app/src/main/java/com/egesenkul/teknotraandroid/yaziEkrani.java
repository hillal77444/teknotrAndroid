package com.egesenkul.teknotraandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    FloatingActionButton floatingActionButton;
    FloatingActionButton floatingActionButton2;

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

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Bu yazıyı okumanı tavsiye ediyorum " + dd.getLink();
                String shareSub = "TEKNOTRA";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
        TooltipCompat.setTooltipText(floatingActionButton, "Yazıyı Paylaş");

        floatingActionButton2 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);

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
                            floatingActionButton2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dd.getLink()));
                                    startActivity(browserIntent);
                                }
                            });
                            TooltipCompat.setTooltipText(floatingActionButton2, "İlgili Sayfaya Git");
                            JSONObject reader = null;
                            try {
                                reader = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject sys  = reader.getJSONObject("content");
                                JSONObject title = reader.getJSONObject("title");
                                rendered += "<center> <b>" + (title.getString("rendered"))+"</b> </center>";
                                if(!sys.getString("rendered").contains(".jpg")){
                                    GetFeaturedMedia();
                                }
                                rendered += sys.getString("rendered");

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
