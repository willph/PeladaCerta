package com.example.will.peladacerta;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.will.peladacerta.models.PeladaModel;
import com.example.will.peladacerta.models.SoccerTeam;
import com.example.will.peladacerta.models.User;
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
import com.loopj.android.http.PersistentCookieStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import com.example.will.peladacerta.util.

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    SupportMapFragment mapa;
    private List<PeladaModel> listaPeladas;
    private User loggerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        if (getIntent().getSerializableExtra("loggerUser") != null) {
            loggerUser = (User) getIntent().getSerializableExtra("loggerUser");
        } else {
            loggerUser = new User();
        }

        SharedPreferences settings = getSharedPreferences("usuario_logado", 0);
//        settings.getString("usuario_id", "");
        Log.i("shared: ", settings.getString("usuario_id", ""));
        loggerUser.setId(Integer.valueOf(settings.getString("usuario_id", "")));


        mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 200);
        } else {
            mapa.getMapAsync(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.log_out) {
            (new PersistentCookieStore(this)).clear();
            SharedPreferences settings = getSharedPreferences("usuario_logado", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("usuario_id", "");
            editor.commit();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (item.getItemId() == R.id.refresh) {
            getLista();
//            generatePins(listaPeladas);

        }

        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        } else {
            mGoogleMap.setMyLocationEnabled(true);
        }

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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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


    public void getLista() {
        Network network = new Network(this, "");
        network.listarPeladas();
        network.setListener(new IASyncFetchListener() {
            @Override
            public void onComplete(JSONObject item) {

            }

            @Override
            public void onComplete(JSONArray jsonArray) {
                listaPeladas = new ArrayList<>();
                SoccerTeam hostTeam = new SoccerTeam();
                SoccerTeam guestTeam = new SoccerTeam();
                JSONObject hostJSON;
                JSONObject guestJSON;
                JSONArray jsonArrayUsers;
                List<User> listaUsersHost;
                List<User> listaUsersGuest;
                User user;


                for (int i = 0; i < jsonArray.length(); i++) {
                    try {

                        JSONObject item = jsonArray.getJSONObject(i);
                        PeladaModel peladaModel = new PeladaModel(item.getInt("id"), item.getString("title"),
                                item.getString("begin"), item.getString("address_full"),
                                item.getDouble("lat"), item.getDouble("lng"), "http://cyberdados.com/wp-content/images/2014/07/2iitkhx.jpg");

                        hostJSON = item.getJSONObject("host");
                        hostTeam.setId(hostJSON.getInt("id"));
                        hostTeam.setTeamName(hostJSON.getString("team_name"));
                        listaUsersHost = new ArrayList<>();
                        jsonArrayUsers = hostJSON.getJSONArray("users");

                        for (int j = 0; j < jsonArrayUsers.length(); j++) {
                            JSONObject userJson = jsonArrayUsers.getJSONObject(j);
                            user = new User();
                            user.setId(userJson.getInt("id"));
                            user.setNome(userJson.getString("nome"));
                            user.setPosition(userJson.getString("position"));
                            user.setBirthdate(userJson.getString("birthdate"));
                            user.setCpf(userJson.getString("cpf"));
                            user.setActive(userJson.getString("active"));
                            user.setCellPhone(userJson.getString("cell_phone"));
                            user.setHomePhone(userJson.getString("home_phone"));
                            user.setDescricao(userJson.getString("descricao"));

                            listaUsersHost.add(user);
                        }

                        guestJSON = item.getJSONObject("guest");
                        guestTeam.setId(guestJSON.getInt("id"));
                        guestTeam.setTeamName(guestJSON.getString("team_name"));
                        listaUsersGuest = new ArrayList<>();
                        jsonArrayUsers = guestJSON.getJSONArray("users");

                        for (int k = 0; k < jsonArrayUsers.length(); k++) {
                            JSONObject userJson = jsonArrayUsers.getJSONObject(k);
                            user = new User();
                            user.setId(userJson.getInt("id"));
                            user.setNome(userJson.getString("nome"));
                            user.setPosition(userJson.getString("position"));
                            user.setBirthdate(userJson.getString("birthdate"));
                            user.setCpf(userJson.getString("cpf"));
                            user.setActive(userJson.getString("active"));
                            user.setCellPhone(userJson.getString("cell_phone"));
                            user.setHomePhone(userJson.getString("home_phone"));
                            user.setDescricao(userJson.getString("descricao"));

                            listaUsersGuest.add(user);
                        }


                        hostTeam.setListaJogadores(listaUsersHost);
                        guestTeam.setListaJogadores(listaUsersGuest);

                        peladaModel.setHostTeam(hostTeam);
                        peladaModel.setGuestTeam(guestTeam);

                        listaPeladas.add(peladaModel);


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

    public void generatePins(List<PeladaModel> lista) {
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

    public void eventPin() {
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, PeladaDetalheActivity.class);
                intent.putExtra("peladaModel", listaPeladas.get(Integer.valueOf(marker.getSnippet())));
                intent.putExtra("loggerUser", loggerUser);
                startActivity(intent);
                return true;
            }
        });
    }
}
