package com.example.weather;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

public class Location {
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;

    public void buildLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(10);
    }

    public void buildLocationCallback(){
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (android.location.Location location: locationResult.getLocations()){
                    String lat = Double.toString(location.getLatitude());
                    String longi = Double.toString(location.getLongitude());
                    Log.d("Lat", lat);
                    Log.d("Long", longi);
                }

            }
        };
    }
}
