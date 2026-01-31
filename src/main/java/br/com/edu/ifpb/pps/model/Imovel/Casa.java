package br.com.edu.ifpb.pps.model.Imovel;

public class Casa extends Imovel{
    protected Boolean temQuintal;
    protected Integer qtdQuartos;
    protected Boolean temJardim;


    public Casa(Double area, Double[] localizacao, String finalidade, Boolean temQuintal,Integer qtdQuartos, Boolean temJardim) {
        super(area, localizacao, finalidade);
        this.temQuintal = temQuintal;
        this.qtdQuartos = qtdQuartos;
        this.temJardim = temJardim;
    }

    public Casa(Casa other) {
        super(other);
        this.temQuintal = other.getTemQuintal();
        this.qtdQuartos = other.getQtdQuartos();
        this.temJardim = other.getTemJardim();

    }

    public Casa() {

    }

    @Override
    public Imovel copy() {
        return new Casa(this);
    }

    public Boolean getTemQuintal() {
        return temQuintal;
    }

    public void setTemQuintal(Boolean temQuintal) {
        this.temQuintal = temQuintal;
    }

    public Integer getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(Integer qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public Boolean getTemJardim() {
        return temJardim;
    }

    public void setTemJardim(Boolean temJardim) {
        this.temJardim = temJardim;
    }
}
