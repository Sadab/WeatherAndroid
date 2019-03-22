package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.JsonObject;

import static com.example.weather.R.drawable.ic_favorite_black_24dp;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    int dark;
    final String baseUrl = "http://api.openweathermap.org/";
    String location = "Dhaka,bd";
    private static final String key = BuildConfig.ApiKey; //Your api key here
    private TextView weatherData, locationTxt;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherData = findViewById(R.id.short_description);
        locationTxt = findViewById(R.id.loc);
        imageView = findViewById(R.id.weatherImg);
        coordinatorLayout = findViewById(R.id.layout);
        dark = Color.parseColor("#263238");

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "White", Toast.LENGTH_LONG).show();
                coordinatorLayout.setBackgroundColor(Color.WHITE);
            }
        });

        findViewById(R.id.action_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Dark", Toast.LENGTH_LONG ).show();
                coordinatorLayout.setBackgroundColor(dark);

            }
        });

        //Log.d("Url: ", urlSet());
        locationTxt.setText(location);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);
        Call<WeatherModel> call = weatherInterface.getData(location, key, "metric");
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if(!response.isSuccessful()){
                    weatherData.setText("Code" + response.code());
                    return;
                }
                WeatherModel object = response.body();

                weatherData.setText(object.getMain().get("temp").toString().concat("°C"));

                //data.setText(object.getMain().toString());

                Log.d("HI", object.getMain().toString());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Log.d("Failed", t.getMessage());
            }
        });
    }

    /*private String urlSet(){
        return (baseUrl+location+"&APPID="+key+"&units=metric");
    }*/
}
