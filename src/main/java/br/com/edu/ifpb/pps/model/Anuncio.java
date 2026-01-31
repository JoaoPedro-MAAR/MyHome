package br.com.edu.ifpb.pps.model;

import br.com.edu.ifpb.pps.Prototype.Prototype;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public class Anuncio implements Prototype {

    private String titulo;
    private String descricao;
    private double preco;
    private Usuario anunciante;
    private Imovel imovel;

    public Anuncio(){}

    public Anuncio(String titulo, String descricao, double preco, Usuario anunciante, Imovel imovel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.anunciante = anunciante;
        this.imovel = imovel;
    }

    public Anuncio(Anuncio other){
        this.titulo = other.getTitulo();
        this.descricao = other.getDescricao();
        this.preco = other.getPreco();
        this.anunciante = other.getAnunciante();
        this.imovel = other.getImovel().copy();
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


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setAnunciante(Usuario anunciante) {
        this.anunciante = anunciante;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    @Override
    public Anuncio copy() {
        return new Anuncio(this);
    }
}
