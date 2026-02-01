package br.com.edu.ifpb.pps.model.Imovel;

import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.filtros.visitors.ImovelVisitor;

public class Apartamento extends Imovel{
    protected Integer andar;
    protected Boolean temCondominio;
    protected Boolean temElevador;



    public Apartamento(Double area, Double[] localizacao, FinalidadeEnum finalidade, int andar, Boolean temCondominio, Boolean temElevador) {
        super(area, localizacao, finalidade);
        this.andar = andar;
        this.temCondominio = temCondominio;
        this.temElevador = temElevador;

    }

    public Apartamento(Apartamento other){
        super(other);
        this.andar = other.getAndar();
        this.temCondominio = other.getTemCondominio();
        this.temElevador = other.getTemElevador();

    }

    public Apartamento() {

    }


    @Override
    public Imovel copy() {
        return new Apartamento(this);
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public Boolean getTemCondominio() {
        return temCondominio;
    }

    public void setTemCondominio(Boolean temCondominio) {
        this.temCondominio = temCondominio;
    }

    public Boolean getTemElevador() {
        return temElevador;
    }

    public void setTemElevador(Boolean temElevador) {
        this.temElevador = temElevador;
    }

    @Override
    public String toString() {
        return "Apartamento{" +
                "area=" + area +
                ", finalidade='" + finalidade + '\'' +
                ", localizacao=" + (
                localizacao != null
                        ? "[" + localizacao[0] + ", " + localizacao[1] + "]"
                        : "N/A"
        ) +
                ", andar=" + andar +
                ", temCondominio=" + temCondominio +
                ", temElevador=" + temElevador +
                '}';

    }

    public void aceitar(ImovelVisitor visitante) {
        visitante.visitar(this);
    }
}
