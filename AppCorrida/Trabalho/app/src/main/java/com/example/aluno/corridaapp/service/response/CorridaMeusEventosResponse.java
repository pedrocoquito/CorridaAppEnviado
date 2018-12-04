package com.example.aluno.corridaapp.service.response;

import java.io.Serializable;

public class CorridaMeusEventosResponse implements Serializable {

    private int id,km,posicao;
    private String nome, dataCorrida;
    private boolean finalizada, cancelada;

    public CorridaMeusEventosResponse() {
    }

    public CorridaMeusEventosResponse(int id, int km, int posicao, String nome, String dataCorrida, boolean finalizada, boolean cancelada) {
        this.id = id;
        this.km = km;
        this.posicao = posicao;
        this.nome = nome;
        this.dataCorrida = dataCorrida;
        this.finalizada = finalizada;
        this.cancelada = cancelada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCorrida() {
        return dataCorrida;
    }

    public void setDataCorrida(String dataCorrida) {
        this.dataCorrida = dataCorrida;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
}
