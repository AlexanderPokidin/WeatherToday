package com.pokidin.a.weathertoday;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;

public class PlaceFinderActivity extends AppCompatActivity {
    private static final String TAG = "SunriseSunsetActivityDebugRun";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 99;

    private boolean mLocationPermissionsGranted = false;
    private PlaceAutocompleteFragment mAutocompleteFragment;

    private TextView mTvCity;
    private Button mBtnRequest;

    private PlaceDetectionClient mPlaceDetectionClient;
    private FusedLocationProviderClient mLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_finder);

        mAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);
        mTvCity = findViewById(R.id.tv_city);
        mBtnRequest = findViewById(R.id.btn_request);


        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        mLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }
}
