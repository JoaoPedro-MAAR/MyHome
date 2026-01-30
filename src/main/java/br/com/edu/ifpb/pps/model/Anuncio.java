package br.com.edu.ifpb.pps.model;

public class Anuncio {

    private String titulo;
    private String descricao;
    private double preco;
    private Usuario anunciante;

    public Anuncio(String titulo, String descricao, double preco, Usuario anunciante) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.anunciante = anunciante;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public Usuario getAnunciante() {
        return anunciante;
    }

    
}
