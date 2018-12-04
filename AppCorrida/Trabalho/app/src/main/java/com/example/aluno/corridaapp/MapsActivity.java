package com.example.aluno.corridaapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng inicio = new LatLng(-21.818180, -43.376423);

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        final MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(inicio).title("Início").snippet("Início");

        mMap.addMarker(markerOptions);
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add( inicio );

        mMap.addPolyline( polylineOptions );

    }
}