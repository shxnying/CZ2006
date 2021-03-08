package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.internal.location.zzz;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Locale;

public class GoogleMap extends AppCompatActivity {
    //Initialize variable
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
//    // Initialize the SDK
//    Places.initialize(getApplicationContext(), apiKey);
//
//    // Create a new PlacesClient instance
//    PlacesClient placesClient = Places.createClient(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        //assign variable
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        //initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();
        // Check permission
        if (ActivityCompat.checkSelfPermission(GoogleMap.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //when permission granted
            //Call method
            getCurrentLocation();

        } else {
            //When permission denied
            //Request permission
            ActivityCompat.requestPermissions(GoogleMap.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    private void locationRequest() {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(20 * 1000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        Log.d("location", "backup");
                        Log.d("location", String.valueOf(location.getLatitude()));
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {

                                //Initalize Lat Lng
                                LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());
                                //Create marker options
                                MarkerOptions options = new MarkerOptions().position(latlng).title("I am here");
                                //Zoom map
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));
                                //add marker on map
                                googleMap.addMarker(options);
                            }
                        });
//                        wayLatitude = location.getLatitude();
//                        wayLongitude = location.getLongitude();
//                        txtLocation.setText(String.format(Locale.US, "%s -- %s", wayLatitude, wayLongitude));
                    }
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }
    private void getCurrentLocation() {
        //initialize task location
        Log.d("location", "odmaoda0");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        Log.d("location", task.toString());
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //When success
//                Log.d("location",  location.toString());
                Log.d("location", String.valueOf(location == null));
                if(location != null){
                    //Sync map
                    Log.d("location", "Ran here");
                    Log.d("location", String.valueOf(location.getLatitude()));
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {

                            //Initalize Lat Lng
                            LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());
                            //Create marker options
                            MarkerOptions options = new MarkerOptions().position(latlng).title("I am here");
                            //Zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,10));
                            //add marker on map
                            googleMap.addMarker(options);
                        }
                    });
                }
                else {
                    locationRequest();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //When permission granted
                //Call method
                getCurrentLocation();
            }
        }
    }
}