package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import static com.example.weather.R.drawable.ic_favorite_black_24dp;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    int dark;
    final String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
    String location = "Dhaka,bd";
    private static final String key = BuildConfig.ApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = findViewById(R.id.layout);
        dark = Color.parseColor("#263238");

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked fab", Toast.LENGTH_LONG).show();
                coordinatorLayout.setBackgroundColor(Color.WHITE);
            }
        });

        findViewById(R.id.action_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_LONG ).show();
                coordinatorLayout.setBackgroundColor(dark);

            }
        });

        Log.d("Url: ", urlSet());
    }

    private String urlSet(){
        return (baseUrl+location+"&APPID="+key+"&units=metric");
    }
}
