package com.example.will.peladacerta;

import java.util.Date;

/**
 * Created by rafaeljcadena on 14/06/17.
 */

public class PeladaModel {

    private int id;
    private String title;
    private String begin;
    private String host_team;
    private String guest_team;
    private String address_full;
    private double lat;
    private double lng;
    private String imgUrl;


    public PeladaModel(int id, String title, String begin, String address_full, double lat, double lng, String imgUrl) {
        this.id = id;
        this.title = title;
        this.begin = begin;
        this.address_full = address_full;
        this.lat = lat;
        this.lng = lng;
        this.imgUrl = imgUrl;
    }



    public PeladaModel(int id, String title, String begin, String host_team, String guest_team, String address_full, double lat, double lng, String imgUrl) {
        this.id = id;
        this.title = title;
        this.begin = begin;
        this.host_team = host_team;
        this.guest_team = guest_team;
        this.address_full = address_full;
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

    public String getHost_team() {
        return host_team;
    }

    public void setHost_team(String host_team) {
        this.host_team = host_team;
    }

    public String getGuest_team() {
        return guest_team;
    }

    public void setGuest_team(String guest_team) {
        this.guest_team = guest_team;
    }

    public String getAddress_full() {
        return address_full;
    }

    public void setAddress_full(String address_full) {
        this.address_full = address_full;
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
