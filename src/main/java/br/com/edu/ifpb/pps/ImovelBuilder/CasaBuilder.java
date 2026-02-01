package br.com.edu.ifpb.pps.ImovelBuilder;

import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.model.Imovel.Casa;

public class CasaBuilder extends ImovelBuilder<Casa>{


    public CasaBuilder(){
        reset();
    }

    @Override
    public void reset() {
        result = new Casa();
    }


    @Override
    public CasaBuilder setArea(Double area){
        result.setArea(area);
        return this;
    }

    @Override
    public CasaBuilder setLocalizacao(Double[] localizacao){
        result.setLocalizacao(localizacao);
        return this;
    }

    @Override
    public CasaBuilder setFinalidade(FinalidadeEnum finalidade){
        result.setFinalidade(finalidade);
        return this;
    }


    public CasaBuilder setQtdQuartos(Integer qtdQuartos){
        result.setQtdQuartos(qtdQuartos);
        return this;
    }
    public CasaBuilder setTemJardim(Boolean temJardim){
        result.setTemJardim(temJardim);
        return this;
    }
    public CasaBuilder setTemQuintal(Boolean temQuintal){
        result.setTemQuintal(temQuintal);
        return this;
    }


    protected void validateFields() throws AssertionError{
        super.validateFields();
        if (result.getQtdQuartos() != null && result.getQtdQuartos() < 0){
            throw new AssertionError("O nunmero de quartos é invalido");
        }
        if(result.getTemJardim() == null){
            setTemJardim(false);
        }
        if(result.getTemQuintal() == null){
            setTemQuintal(false);
        }
    }
}
