package br.com.edu.ifpb.pps.ImovelBuilder.Director;

import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.ImovelBuilder.CasaBuilder;
import br.com.edu.ifpb.pps.model.Imovel.Casa;

public class DirectorCasa extends DiretorImovel<Casa, CasaBuilder, ImovelDTO>{


    @Override
    public Casa criarComDados(CasaBuilder imovelBuilder, ImovelDTO dto) {
        super.preencherCamposPadrao(imovelBuilder, dto);
        imovelBuilder.setQtdQuartos(dto.qtdQuartos).setTemJardim(dto.temJardim).setTemQuintal(dto.temQuintal);
        return imovelBuilder.build();
    }
}
