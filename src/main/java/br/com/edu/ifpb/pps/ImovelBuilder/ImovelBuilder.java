package br.com.edu.ifpb.pps.ImovelBuilder;

import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public abstract class ImovelBuilder<T extends Imovel> {


    protected T result;


    public abstract void reset();
    public ImovelBuilder setLocalizacao(Double[] localizacao){
        result.setLocalizacao(localizacao);
        return this;
    }
    public ImovelBuilder setArea(Double area){
        result.setArea(area);
        return this;
    }
    public ImovelBuilder setFinalidade(String finalidade){
        result.setFinalidade(finalidade);
        return this;
    }

    public T build(){
        return this.result;
    };

}
