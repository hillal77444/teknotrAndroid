package com.egesenkul.teknotraandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tamEkranYap();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void tamEkranYap(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }
}
