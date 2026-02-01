package br.com.edu.ifpb.pps.model;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.Prototype.Prototype;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public class Anuncio implements Prototype {

    private String titulo;
    private double preco;
    private Usuario anunciante;
    private Imovel imovel;

    public Anuncio(){}

    public Anuncio(String titulo, double preco, Usuario anunciante, Imovel imovel) {
        this.titulo = titulo;
        this.preco = preco;
        this.anunciante = anunciante;
        this.imovel = imovel;
    }

    public Anuncio(AnuncioDTO dto){
        this.titulo = dto.titulo;
        this.preco = dto.preco;
        this.anunciante = dto.anunciante;
        this.imovel = dto.imovel.copy();
    }

    public Anuncio(Anuncio other){
        this.titulo = other.getTitulo();
        this.preco = other.getPreco();
        this.anunciante = other.getAnunciante();
        this.imovel = other.getImovel().copy();
    }

    public String getTitulo() {
        return titulo;
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

    @Override
    public String toString() {
        return "Anuncio{" +
                "titulo='" + titulo + '\'' +
                ", preco=" + preco +
                ", anunciante=" + (anunciante != null ? anunciante : "N/A") +
                ", imovel=" + (imovel != null ? imovel.getClass().getSimpleName() : "N/A") +
                '}';
    }
}
