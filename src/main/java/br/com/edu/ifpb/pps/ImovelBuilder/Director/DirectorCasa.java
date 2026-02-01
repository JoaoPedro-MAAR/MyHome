package br.com.edu.ifpb.pps.ImovelBuilder.Director;

import br.com.edu.ifpb.pps.DTO.Imovel.CasaDTO;
import br.com.edu.ifpb.pps.ImovelBuilder.CasaBuilder;
import br.com.edu.ifpb.pps.model.Imovel.Casa;

public class DirectorCasa extends DiretorImovel<Casa, CasaBuilder, CasaDTO>{


    @Override
    public Casa criarComDados(CasaBuilder imovelBuilder, CasaDTO dto) {
        super.preencherCamposPadrao(imovelBuilder, dto);
        imovelBuilder.setQtdQuartos(dto.qtdQuartos).setTemJardim(dto.temJardim).setTemQuintal(dto.temQuintal);
        return imovelBuilder.build();
    }
}
