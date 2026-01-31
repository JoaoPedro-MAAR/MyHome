package br.com.edu.ifpb.pps.ImovelBuilder;

import br.com.edu.ifpb.pps.model.Imovel.Apartamento;

public class ApartamentoBuilder extends ImovelBuilder<Apartamento>{



    @Override
    public void reset() {
        result = new Apartamento();

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

}
