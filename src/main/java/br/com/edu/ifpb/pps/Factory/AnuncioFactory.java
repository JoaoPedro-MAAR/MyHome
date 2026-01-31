package br.com.edu.ifpb.pps.Factory;

import br.com.edu.ifpb.pps.DTO.Imovel.AnuncioDTO;
import br.com.edu.ifpb.pps.ImovelBuilder.Registry.ImovelBuilderRegistry;
import br.com.edu.ifpb.pps.model.Anuncio;

public class AnuncioFactory {
    private AnuncioFactory() {}

    public static Anuncio criar(AnuncioDTO dto) {
        if (dto == null) throw new IllegalArgumentException("DTO nao pode ser null");

        if (dto.titulo == null || dto.titulo.isBlank())
            throw new IllegalArgumentException("Titulo obrigatorio");

        if (dto.preco == null || dto.preco <= 0)
            throw new IllegalArgumentException("Preco obrigatorio e deve ser > 0");

        if (dto.tipo == null || dto.tipo.isBlank())
            throw new IllegalArgumentException("Tipo do imovel obrigatorio");

        if (!ImovelBuilderRegistry.exists(dto.tipo))
            throw new IllegalArgumentException("Tipo de imovel nao registrado: " + dto.tipo);

        if (dto.imovel == null)
            throw new IllegalArgumentException("Imovel obrigatorio");



        return new Anuncio(dto);
    }
}