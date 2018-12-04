package com.example.aluno.corridaapp.service.response;

import java.io.Serializable;

public class CorridaResponse implements Serializable {

    private int id,valorInscricao,km,numroInscritos;
    private String nome, dataCorrida;
    private boolean finalizada;

    public CorridaResponse() {
    }

    public CorridaResponse(int id, int valorInscricao, int km, String nome, String dataCorrida, int numroInscritos, boolean finalizada) {
        this.id = id;
        this.valorInscricao = valorInscricao;
        this.km = km;
        this.nome = nome;
        this.dataCorrida = dataCorrida;
        this.numroInscritos = numroInscritos;
        this.finalizada = finalizada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValorInscricao() {
        return valorInscricao;
    }

    public void setValorInscricao(int valorInscricao) {
        this.valorInscricao = valorInscricao;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
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

    public int getNumroInscritos() {
        return numroInscritos;
    }

    public void setNumroInscritos(int numroInscritos) {
        this.numroInscritos = numroInscritos;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
}
