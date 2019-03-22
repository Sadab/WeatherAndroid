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
import com.squareup.picasso.Picasso;

import static com.example.weather.R.drawable.ic_favorite_black_24dp;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    int dark;
    final String baseUrl = "http://api.openweathermap.org/";
    final String baseImageUrl = "http://openweathermap.org/img/w/";
    String location = "Dhaka,bd";
    String iconName = " ";
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

        locationTxt.setText(location);
        getMainModel();
        getWeatherModel();

    }

    private void getMainModel(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);
        Call<MainModel> call = weatherInterface.getMainModelData(location, key, "metric");
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if(!response.isSuccessful()){
                    weatherData.setText("Code" + response.code());
                    return;
                }
                MainModel object = response.body();

                weatherData.setText(object.getMain().get("temp").toString().concat("°C"));

                //data.setText(object.getMain().toString());

                Log.d("Main", object.getMain().toString());
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Log.d("Failed: ", t.getMessage());
            }
        });
    }

    private void getWeatherModel(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);
        Call<WeatherModel> call = weatherInterface.getWeatherModelData(location, key, "metric");
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if(!response.isSuccessful()){
                    locationTxt.setText("Code" + response.code());
                    return;
                }

                WeatherModel weatherModel = response.body();

                iconName = weatherModel.getWeather().get(0).getAsJsonObject().get("icon").toString().replaceAll("\"","");
                Log.d("Weather icon", iconName);
                Picasso.get().load(baseImageUrl+iconName.trim()+".png").into(imageView);


            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Log.d("Failed: ", t.getMessage());
            }
        });



    }
}
