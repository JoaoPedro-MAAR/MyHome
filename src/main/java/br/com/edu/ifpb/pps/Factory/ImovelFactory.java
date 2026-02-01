package br.com.edu.ifpb.pps.Factory;

import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.ImovelBuilder.ApartamentoBuilder;
import br.com.edu.ifpb.pps.ImovelBuilder.CasaBuilder;
import br.com.edu.ifpb.pps.ImovelBuilder.Director.DirectorApartamento;
import br.com.edu.ifpb.pps.ImovelBuilder.Director.DirectorCasa;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

public class ImovelFactory {
    public static Imovel criar(String tipo, ImovelDTO dados) {
        if (tipo == null || dados == null) {
            throw new IllegalArgumentException("Tipo e Dados do imóvel são obrigatórios");
        }

        switch (tipo.trim().toUpperCase()) {
            case "CASA":
                return new DirectorCasa().criarComDados(new CasaBuilder(), dados);

            case "APARTAMENTO":
                return new DirectorApartamento().criarComDados(new ApartamentoBuilder(), dados);

            default:
                throw new IllegalArgumentException("Tipo de imóvel não suportado: " + tipo);
        }
    }
}