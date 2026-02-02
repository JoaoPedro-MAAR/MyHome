package br.com.edu.ifpb.pps.model;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.Prototype.Prototype;
import br.com.edu.ifpb.pps.estados.EstadoAnuncio;
import br.com.edu.ifpb.pps.estados.RascunhoState;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;
import br.com.edu.ifpb.pps.observador.Observador;

import java.util.ArrayList;
import java.util.List;

public class Anuncio implements Prototype {

    private Integer id;
    private String titulo;
    private double preco;
    private Usuario anunciante;
    private Imovel imovel;
    private Usuario comprador;

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
        this.observadores = new ArrayList<>();
        this.titulo = dto.titulo;
        this.preco = dto.preco;
        this.anunciante = dto.anunciante;
        this.imovel = dto.imovel.copy();
        this.setEstado(new RascunhoState());    }

    public Anuncio(Anuncio other){
        this.observadores = new ArrayList<>();
        this.id = other.id;
        this.titulo = other.getTitulo();
        this.preco = other.getPreco();
        this.anunciante = other.getAnunciante();
        this.imovel = other.getImovel().copy();
        this.setEstado(other.getEstado());
        this.comprador = other.getComprador();


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
        if (observadores == null){
            observadores = new ArrayList<>();
        }
        this.observadores.add(observador);
    }

    public void removeObservador(Observador observador){
        this.observadores.remove(observador);
    }

    public void notificar(){
        if (observadores!= null) {
            for (Observador observador : observadores) {
                observador.atualizar(this);
            }
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

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "estado=" + estado +
                ", comprador=" + comprador +
                ", imovel={" + imovel +
                "}, anunciante=" + anunciante +
                ", preco=" + preco +
                ", titulo='" + titulo + '\'' +
                ", id=" + id +
                '}';
    }
}
