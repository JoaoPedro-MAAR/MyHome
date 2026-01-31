package br.com.edu.ifpb.pps.ImovelBuilder;

import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.model.Imovel.Apartamento;

public class ApartamentoBuilder extends ImovelBuilder<Apartamento>{


    public ApartamentoBuilder(){
        reset();
    }

    @Override
    public void reset() {
        result = new Apartamento();

    }

    @Override
    public ApartamentoBuilder setArea(Double area){
        result.setArea(area);
        return this;
    }

    @Override
    public ApartamentoBuilder setLocalizacao(Double[] localizacao){
        result.setLocalizacao(localizacao);
        return this;
    }

    @Override
    public ApartamentoBuilder setFinalidade(FinalidadeEnum finalidade){
        result.setFinalidade(finalidade);
        return this;
    }


    public ApartamentoBuilder setAndar(Integer andar){
        result.setAndar(andar);
        return this;
    }
    public ApartamentoBuilder setTemElevador(Boolean elevador){
        result.setTemElevador(elevador);
        return this;
    }
    public ApartamentoBuilder setTemCondominio(Boolean temCondominio){
        result.setTemCondominio(temCondominio);
        return this;
    }


    protected void validateFields() throws AssertionError{
        super.validateFields();
        if(result.getAndar() == null || result.getAndar() < 1){
            throw new AssertionError("Andar invalido");
        }
        if(result.getTemCondominio() == null){
            setTemCondominio(false);
        }
        if(result.getTemElevador() == null){
            setTemElevador(false);
        }
    }

}
