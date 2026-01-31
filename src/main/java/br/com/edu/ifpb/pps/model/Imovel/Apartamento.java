package br.com.edu.ifpb.pps.model.Imovel;

public class Apartamento extends Imovel{
    protected int andar;
    protected Boolean temCondominio;
    protected Boolean temElevador;



    public Apartamento(Double area, Double[] localizacao, String finalidade, int andar, Boolean temCondominio, Boolean temElevador) {
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

    public int getAndar() {
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
}
