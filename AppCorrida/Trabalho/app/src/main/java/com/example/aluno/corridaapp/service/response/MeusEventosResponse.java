package com.example.aluno.corridaapp.service.response;

import java.io.Serializable;
import java.util.List;

public class MeusEventosResponse implements Serializable {

    private int id, pontuacao, classificacaoGeral;
    private String nome;
    private List<CorridaMeusEventosResponse> corridas;


    public MeusEventosResponse() {
    }

    public MeusEventosResponse(int id, int pontuacao, int classificacaoGeral, String nome, List<CorridaMeusEventosResponse> corridas) {
        this.id = id;
        this.pontuacao = pontuacao;
        this.classificacaoGeral = classificacaoGeral;
        this.nome = nome;
        this.corridas = corridas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getClassificacaoGeral() {
        return classificacaoGeral;
    }

    public void setClassificacaoGeral(int classificacaoGeral) {
        this.classificacaoGeral = classificacaoGeral;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CorridaMeusEventosResponse> getCorridas() {
        return corridas;
    }

    public void setCorridas(List<CorridaMeusEventosResponse> corridas) {
        this.corridas = corridas;
    }
}
