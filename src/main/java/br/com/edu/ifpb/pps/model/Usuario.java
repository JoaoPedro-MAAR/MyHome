package br.com.edu.ifpb.pps.model;

import br.com.edu.ifpb.pps.notificacao.MeioDeNotificacao;

public class Usuario {
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void enviarNotificacao(String mensagem){
        if (this.meioDeNotificacao != null){
            this.meioDeNotificacao.enviar(mensagem, this.email);
        } else {
            System.out.println("Erro");
        }
    }

    public MeioDeNotificacao getMeioDeNotificacao() {
        return meioDeNotificacao;
    }

    public void setMeioDeNotificacao(MeioDeNotificacao meioDeNotificacao) {
        this.meioDeNotificacao = meioDeNotificacao;
    }
}
