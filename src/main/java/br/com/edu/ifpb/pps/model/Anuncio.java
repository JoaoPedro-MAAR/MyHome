package br.com.edu.ifpb.pps.model;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifpb.pps.Prototype.Prototype;
import br.com.edu.ifpb.pps.estados.EstadoAnuncio;
import br.com.edu.ifpb.pps.estados.RascunhoState;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;
import br.com.edu.ifpb.pps.observador.Observador;

public class Anuncio implements Prototype<Anuncio>,EstadoAnuncio {

    private Integer id;
    private String titulo;
    private double preco;
    private Usuario anunciante;
    private Imovel imovel;

    private EstadoAnuncio estado;

    private List<Observador> observadores;

    public Anuncio(){
        this.observadores = new ArrayList<>();
        this.setEstado(new RascunhoState());
    }

    public Anuncio(String titulo, double preco, Usuario anunciante, Imovel imovel) {
        this.observadores = new ArrayList<>();
        this.titulo = titulo;
        this.preco = preco;
        this.anunciante = anunciante;
        this.imovel = imovel;

        this.setEstado(new RascunhoState());
    }

    public Anuncio(AnuncioDTO dto){
        this.titulo = dto.titulo;
        this.preco = dto.preco;
        this.anunciante = dto.anunciante;
        this.imovel = dto.imovel.copy();
        this.estado = new RascunhoState();
    }

    public Anuncio(Anuncio other){
        this.observadores = new ArrayList<>();
        this.id = null;
        this.titulo = other.getTitulo();
        this.preco = other.getPreco();
        this.anunciante = null;
        this.imovel = other.getImovel().copy();
        this.setEstado(new RascunhoState());


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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    public void setEstado(EstadoAnuncio estado) {
        this.estado = estado;
        this.estado.setContext(this);

        notificar();
    }

      public EstadoAnuncio getEstado() {
        return estado;
    }

    public void addObservador(Observador observador){
        observadores.add(observador);
    }

    public void notificar(){
        for(Observador observador : observadores){
            observador.atualizar(this);
        }
    }

    public void enviarParaModeracao(){
        this.estado.enviarParaModeracao();
    }

    public void aprovar(){
        this.estado.aprovar();
    }

    public void reprovar(){
        this.estado.reprovar();
    }

    public void suspender(){
        this.estado.suspender();
    }

    public void vender(){
        this.estado.vender();
    }

    public void editar(){
        this.estado.editar();
    }

}
