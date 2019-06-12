package com.example.mapmanager;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap btsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_maps__fr__map);
        mapFragment.getMapAsync(this);

    }



    /**

     * Manipulates the map once available.

     * This callback is triggered when the map is ready to be used.

     * This is where we can add markers or lines, add listeners or move the camera. In this case,

     * we just add a marker near Sydney, Australia.

     * If Google Play services is not installed on the device, the user will be prompted to install

     * it inside the SupportMapFragment. This method will only be triggered once the user has

     * installed Google Play services and returned to the app.

     */

    @Override

    public void onMapReady(GoogleMap googleMap) {

        btsMap = googleMap;


        btsMap.getUiSettings().setZoomControlsEnabled(true);
        //btsMap.setMyLocationEnabled(true);
        btsMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng barcelona = new LatLng(41.38, 2.17);
        btsMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        btsMap.addMarker(new MarkerOptions().position(barcelona).title("BTS"));
        btsMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona, 14));

        btsMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                createMarkerOnSpot(latLng);
            }
        });

        btsMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return showToastAboutMarker(marker);
            }

        });

        btsMap.setOnInfoWindowClickListener(this);

    }

    private boolean showToastAboutMarker(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }


    private void createMarkerOnSpot(LatLng latLng) {
        btsMap.addMarker(new MarkerOptions()
                .snippet("This is an arbitrary marker")
                .position(latLng)
                .title(latLng.toString()));
    }



    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show();
    }

}
