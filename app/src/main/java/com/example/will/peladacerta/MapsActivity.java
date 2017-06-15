package com.example.will.peladacerta;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.will.peladacerta.util.GPSTracker;
import com.example.will.peladacerta.util.IASyncFetchListener;
import com.example.will.peladacerta.util.Network;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import com.example.will.peladacerta.util.

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    SupportMapFragment mapa;
    private List<PeladaModel> listaPeladas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
        }
        else {
            mapa.getMapAsync(this);
        }



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.setMyLocationEnabled(true);
        GPSTracker gps = new GPSTracker(this);
        LatLng latLng = new LatLng(gps.getLatitude(), gps.getLongitude());
        gps.stopUsingGPS();
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.0f));
        getLista();
//        mGoogleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(latLng.latitude, latLng.longitude))
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pino)));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if (grantResults.length == 2
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    mapa.getMapAsync(this);
                }
            }
        }
    }

    public void getLista(){
        Network network = new Network(this, "");
        network.listarPeladas();
        network.setListener(new IASyncFetchListener() {
            @Override
            public void onComplete(JSONObject item) {

            }

            @Override
            public void onComplete(JSONArray jsonArray) {
                listaPeladas = new ArrayList<>();
                for(int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject item = jsonArray.getJSONObject(i);
                        listaPeladas.add(new PeladaModel(item.getInt("id"), item.getString("title"),
                                item.getString("begin"), item.getString("address_full"),
                                item.getDouble("lat"), item.getDouble("lng"), "http://cyberdados.com/wp-content/images/2014/07/2iitkhx.jpg"));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                generatePins(listaPeladas);
            }

            @Override
            public void onError(Throwable error) {

            }

            @Override
            public void onError(Throwable error, JSONObject errorResponse) {

            }
        });


    }

    public void generatePins(List<PeladaModel> lista){
        mGoogleMap.clear();
        int i = 0;
        for (PeladaModel item : lista) {

            mGoogleMap.addMarker(new MarkerOptions()
            .position(new LatLng(item.getLat(), item.getLng()))
            .snippet(String.valueOf(i))
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ball_icon)));
            i++;
        }
        eventPin();
    }

    public void eventPin(){
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, PeladaDetalheActivity.class);
                intent.putExtra("lista", listaPeladas.get(Integer.valueOf(marker.getSnippet())));
                startActivity(intent);
                return true;
            }
        });
    }
}
