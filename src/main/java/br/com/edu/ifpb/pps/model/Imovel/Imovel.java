package br.com.edu.ifpb.pps.model.Imovel;

import br.com.edu.ifpb.pps.Prototype.Prototype;
import br.com.edu.ifpb.pps.filtros.visitors.ImovelVisitor;

public abstract class Imovel implements Prototype<Imovel> {
    protected Double area;
    protected Double[] localizacao;
    protected String finalidade;

    public Imovel(){

    }

    public Imovel(Double area, Double[] localizacao, String finalidade){
        this.area = area;
        this.localizacao = localizacao;
        this.finalidade = finalidade;
    }

    public Imovel(Imovel other){
        this.area = other.getArea();
        this.localizacao = other.getLocalizacao();
        this.finalidade = other.getFinalidade();
    }

    private String getFinalidade() {
        return finalidade;
    }

    public Double[] getLocalizacao() {
        return localizacao;
    }

    public Double getArea(){
        return area;
    }

    public void setFinalidade(String s){
        finalidade = s;
    }

    public void setLocalizacao(Double[] localizacao){
        this.localizacao = localizacao;
    }

    public void setArea(Double area){
        this.area = area;
    }

    public abstract Imovel copy();

    public abstract void aceitar(ImovelVisitor visitante);

}
