package br.com.edu.ifpb.pps.ImovelBuilder.Director;

import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.ImovelBuilder.ImovelBuilder;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public abstract class DiretorImovel<T extends Imovel, B extends ImovelBuilder<T>,
        D extends ImovelDTO> implements DirectorInterface<T, B, D> {


    @Override
    public  T criarPadrao(B imovelBuilder) {
        imovelBuilder.reset();
        return imovelBuilder.setArea(25.0).setFinalidade(FinalidadeEnum.ALUGUEL).build();    }

    protected void preencherCamposPadrao(B imovelBuilder, D dto){
        imovelBuilder.setLocalizacao(dto.localizacao);
        imovelBuilder.setArea(dto.area);
        imovelBuilder.setFinalidade(dto.finalidade);

    }

    @Override
    public abstract  T criarComDados(B imovelBuilder, D dto);
}