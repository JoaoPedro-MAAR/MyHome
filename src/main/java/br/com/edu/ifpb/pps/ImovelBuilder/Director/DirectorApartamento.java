package br.com.edu.ifpb.pps.ImovelBuilder.Director;

import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.ImovelBuilder.ApartamentoBuilder;
import br.com.edu.ifpb.pps.model.Imovel.Apartamento;

public class DirectorApartamento extends DiretorImovel<Apartamento, ApartamentoBuilder, ImovelDTO> {
    @Override
    public Apartamento criarComDados(ApartamentoBuilder imovelBuilder, ImovelDTO dto) {
        preencherCamposPadrao(imovelBuilder, dto);
        imovelBuilder.setTemElevador(dto.temElevador).setAndar(dto.andar).setTemCondominio(dto.temCondominio);
        return imovelBuilder.build();
    }
}
