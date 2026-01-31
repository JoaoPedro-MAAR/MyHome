package br.com.edu.ifpb.pps.ImovelBuilder;

import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public abstract class ImovelBuilder<T extends Imovel> {


    protected T result;


    public abstract void reset();

    public ImovelBuilder<T> setLocalizacao(Double[] localizacao){
        result.setLocalizacao(localizacao);
        return this;
    }
    public ImovelBuilder<T> setArea(Double area){
        result.setArea(area);
        return this;
    }
    public ImovelBuilder<T> setFinalidade(FinalidadeEnum finalidade){
        result.setFinalidade(finalidade);
        return this;
    }

    public T build(){
        validateFields();
        T finalResult = this.result;
        reset();
        return finalResult;
    };

    protected  void validateFields() throws AssertionError{
        if(result.getArea() == null || result.getArea() < 0){
            throw new AssertionError("Area menor que zero");
        }
        if(result.getLocalizacao() == null){
            throw new AssertionError("Localizacao nula");
        }
    };

}
