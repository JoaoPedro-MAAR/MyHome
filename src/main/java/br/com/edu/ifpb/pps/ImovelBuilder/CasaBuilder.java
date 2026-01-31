package br.com.edu.ifpb.pps.ImovelBuilder;

import br.com.edu.ifpb.pps.model.Imovel.Casa;

public class CasaBuilder extends ImovelBuilder<Casa>{


    @Override
    public void reset() {
        result = new Casa();
    }

    public void setQtdQuartos(Integer qtdQuartos){
        result.setQtdQuartos(qtdQuartos);
    }
    public void setTemJardim(Boolean temJardim){
        result.setTemJardim(temJardim);
    }
    public void setTemQuintal(Boolean temQuintal){
        result.setTemQuintal(temQuintal);
    }
}
