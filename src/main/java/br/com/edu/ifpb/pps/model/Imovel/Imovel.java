package br.com.edu.ifpb.pps.model.Imovel;

import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.Prototype.Prototype;
import br.com.edu.ifpb.pps.filtros.visitors.ImovelVisitor;

public abstract class Imovel implements Prototype {
    protected Double area;
    protected Double[] localizacao;
    protected FinalidadeEnum finalidade;

    public Imovel(){

    }

    public Imovel(Double area, Double[] localizacao, FinalidadeEnum finalidade){
        this.area = area;
        this.localizacao = localizacao;
        this.finalidade = finalidade;
    }

    public Imovel(Imovel other){
        this.area = other.getArea();
        this.localizacao = other.getLocalizacao();
        this.finalidade = other.getFinalidade();
    }

    private FinalidadeEnum getFinalidade() {
        return finalidade;
    }

    public Double[] getLocalizacao() {
        return localizacao;
    }

    public Double getArea(){
        return area;
    }

    public void setFinalidade(FinalidadeEnum s){
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
