package br.com.edu.ifpb.pps.model;

import br.com.edu.ifpb.pps.notificacao.MeioDeNotificacao;

public class Usuario {
    private String nome;
    private String email;

    private MeioDeNotificacao meioDeNotificacao;


    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public MeioDeNotificacao getMeioDeNotificacao() {
        return meioDeNotificacao;
    }

    public void setMeioDeNotificacao(MeioDeNotificacao meioDeNotificacao) {
        this.meioDeNotificacao = meioDeNotificacao;
    }
}
