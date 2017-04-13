package com.example.will.peladacerta;

/**
 * Created by aluno on 12/04/2017.
 */

public class Pelada {
    int id;
    String titulo;
    String descricao;
    String rua;
    String numero;
    String bairro;
    String cep;
    String complemento;
    int vagasDisponiveis;
    int vagasOcupadas;

    public Pelada(int id, String titulo, String descricao, String rua, String numero, String bairro, String cep, String complemento, int vagaDisponiveis, int vagaOcupadas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.vagasDisponiveis = vagaDisponiveis;
        this.vagasOcupadas = vagaOcupadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagaDisponiveis) {
        this.vagasDisponiveis = vagaDisponiveis;
    }

    public int getVagasOcupadas() {
        return vagasOcupadas;
    }

    public void setVagasOcupadas(int vagaOcupadas) {
        this.vagasOcupadas = vagaOcupadas;
    }
}
