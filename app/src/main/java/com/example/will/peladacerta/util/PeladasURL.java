package com.example.will.peladacerta.util;

/**
 * Created by rafaelcadena on 19/06/17.
 */

public class PeladasURL {
    private static final String ADDRESS = "https://pelada-certa.herokuapp.com";
//    private static final String ADDRESS = "http://172.16.211.14:3000";

    private static final String QUERY_LOGIN = PeladasURL.ADDRESS + "/users/sign_in.json";

    private static final String QUERY_LISTAR_PELADAS = PeladasURL.ADDRESS + "/peladas.json";

    private static final String QUERY_JOIN_TEAM = PeladasURL.ADDRESS + "/api/join_team.json";

    private static final String QUERY_UNJOIN_TEAM = PeladasURL.ADDRESS + "/api/unjoin_team.json";

    private static final String QUERY_LISTAR_TIMES = PeladasURL.ADDRESS + "/soccer_teams.json";


    public static String getQueryLogin() {
        return QUERY_LOGIN;
    }

    public static String getQueryListarPeladas() {
        return QUERY_LISTAR_PELADAS;
    }

    public static String getQueryJoinTeam() {
        return QUERY_JOIN_TEAM;
    }

    public static String getQueryUnjoinTeam() {
        return QUERY_UNJOIN_TEAM;
    }
}
