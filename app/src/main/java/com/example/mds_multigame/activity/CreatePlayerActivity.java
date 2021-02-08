package com.example.mds_multigame.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.mds_multigame.R;
import com.squareup.picasso.Picasso;


public class CreatePlayerActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE = 7;
    private static final int REQUEST_LOCALISATION_PERMISSION = 2001;
    private ActivityCreatePlayerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_player);
        binding.createPlayerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                //startActivityForResult(Intent.createChooser(intent,"Choix de la photo"), REQUEST_IMAGE);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
        binding.createPlayerLocalisationBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLocationAuthorized()) {
                    getUserLocation();
                } else {
                    ActivityCompat.requestPermissions(CreatePlayerActivity.this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, REQUEST_LOCALISATION_PERMISSION);
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCALISATION_PERMISSION && checkLocationAuthorized()) {
            getUserLocation();
        }
    }
    private void getUserLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        //here you receive the new location
                        locationManager.removeUpdates(this);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {
                    }
                    @Override
                    public void onProviderEnabled(String s) {
                    }
                    @Override
                    public void onProviderDisabled(String s) {
                    }
                });
    }
    private boolean checkLocationAuthorized(){
        return ActivityCompat.checkSelfPermission(CreatePlayerActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            Picasso.get().load(data.getData()).centerCrop().fit().into(binding.createPlayerImage);
        }
    }
}