package br.com.edu.ifpb.pps.ImovelBuilder.Director;

import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.ImovelBuilder.ImovelBuilder;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public interface DirectorInterface<T extends Imovel, B extends ImovelBuilder<T>, D extends ImovelDTO> {

    T criarPadrao(B imovelBuilder);

    T criarComDados(B imovelBuilder, D dto);
}