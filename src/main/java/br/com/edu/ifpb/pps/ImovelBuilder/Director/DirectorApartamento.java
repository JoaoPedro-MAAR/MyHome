package br.com.edu.ifpb.pps.ImovelBuilder.Director;

import br.com.edu.ifpb.pps.DTO.Imovel.ApartamentoDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.CasaDTO;
import br.com.edu.ifpb.pps.ImovelBuilder.ApartamentoBuilder;
import br.com.edu.ifpb.pps.ImovelBuilder.CasaBuilder;
import br.com.edu.ifpb.pps.model.Imovel.Apartamento;
import br.com.edu.ifpb.pps.model.Imovel.Casa;

public class DirectorApartamento extends DiretorImovel<Apartamento, ApartamentoBuilder, ApartamentoDTO> {
    @Override
    public Apartamento criarComDados(ApartamentoBuilder imovelBuilder, ApartamentoDTO dto) {
        preencherCamposPadrao(imovelBuilder, dto);
        imovelBuilder.setTemElevador(dto.temElevador).setAndar(dto.andar).setTemCondominio(dto.temCondominio);
        return imovelBuilder.build();
    }
}
