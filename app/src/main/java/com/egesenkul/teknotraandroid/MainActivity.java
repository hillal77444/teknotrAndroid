package com.egesenkul.teknotraandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tamEkranYap();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialization();
    }

    private void Initialization() {
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
}
