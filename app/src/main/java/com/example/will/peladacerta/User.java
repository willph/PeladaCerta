package com.example.will.peladacerta;

/**
 * Created by rafaeljcadena on 14/06/17.
 */

public class User {

    private String email;
    private String nome;
    private String cpf;
    private String descricao;
    private String position;
    private String cell_phone;
    private String home_phone;
    private String soccer_team; //team_name

    public User(){

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getSoccer_team() {
        return soccer_team;
    }

    public void setSoccer_team(String soccer_team) {
        this.soccer_team = soccer_team;
    }
}
