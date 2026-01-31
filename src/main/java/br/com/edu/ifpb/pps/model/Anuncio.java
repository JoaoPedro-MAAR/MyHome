package br.com.edu.ifpb.pps.model;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifpb.pps.Prototype.Prototype;
import br.com.edu.ifpb.pps.estados.EstadoAnuncio;
import br.com.edu.ifpb.pps.estados.RascunhoState;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;
import br.com.edu.ifpb.pps.observador.Observador;

public class Anuncio implements Prototype {

    private String titulo;
    private String descricao;
    private double preco;
    private Usuario anunciante;
    private Imovel imovel;

    private EstadoAnuncio estado;

    private List<Observador> observadores;

    public Anuncio(){
        this.estado = new RascunhoState();
        this.observadores = new ArrayList<>();
    }

    public Anuncio(String titulo, String descricao, double preco, Usuario anunciante, Imovel imovel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.anunciante = anunciante;
        this.imovel = imovel;

        this.estado = new RascunhoState();
        this.observadores = new ArrayList<>();
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

    public void enviarParaModeracao(){
        this.estado.enviarParaModeracao(this);
    }

    public void aprovar(){
        this.estado.aprovar(this);
    }

    public void reprovar(){
        this.estado.reprovar(this);
    }

    public void suspender(){
        this.estado.suspender(this);
    }

    public void vender(){
        this.estado.vender(this);
    }

    public void editar(){
        this.estado.editar(this);
    }

    public void setEstado(EstadoAnuncio estado) {
        this.estado = estado;
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

}
