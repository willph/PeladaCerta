package com.example.will.peladacerta.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rafaelcadena on 16/06/17.
 */

public class SoccerTeam implements Serializable {


    private int id;
    private String teamName;
    private List<User> listaJogadores;

//    public SoccerTeam(int id, String team_name) {
//        this.id = id;
//        this.team_name = team_name;
//    }
//
//    public SoccerTeam(int id, String team_name, List<User> listaJogadores) {
//        this.id = id;
//        this.team_name = team_name;
//        this.listaJogadores = listaJogadores;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_name() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<User> getListaJogadores() {
        return listaJogadores;
    }

    public void setListaJogadores(List<User> listaJogadores) {
        this.listaJogadores = listaJogadores;
    }

    public boolean hasUserById(User user) {
        boolean has = false;
        for(User item : listaJogadores){
            if (item.getId() == user.getId()){
                has = true;
                return has;
            }
        }
        return has;
    }
}
