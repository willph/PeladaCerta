package com.example.will.peladacerta.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rafaeljcadena on 14/06/17.
 */

public class PeladaModel implements Serializable {

    private int id;
    private String title;
    private String begin;
    private SoccerTeam hostTeam;
    private SoccerTeam guestTeam;
    private String addressFull;
    private double lat;
    private double lng;
    private String imgUrl;


    public PeladaModel(int id, String title, String begin, String addressFull, double lat, double lng, String imgUrl) {
        this.id = id;
        this.title = title;
        this.begin = begin;
        this.addressFull = addressFull;
        this.lat = lat;
        this.lng = lng;
        this.imgUrl = imgUrl;
    }



    public PeladaModel(int id, String title, String begin, SoccerTeam hostTeam, SoccerTeam guestTeam, String addressFull, double lat, double lng, String imgUrl) {
        this.id = id;
        this.title = title;
        this.begin = begin;
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
        this.addressFull = addressFull;
        this.lat = lat;
        this.lng = lng;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public SoccerTeam getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(SoccerTeam hostTeam) {
        this.hostTeam = hostTeam;
    }

    public SoccerTeam getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(SoccerTeam guestTeam) {
        this.guestTeam = guestTeam;
    }

    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
