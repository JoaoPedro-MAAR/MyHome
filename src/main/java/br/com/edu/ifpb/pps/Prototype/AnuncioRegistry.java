package br.com.edu.ifpb.pps.Prototype;

import br.com.edu.ifpb.pps.DTO.Imovel.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ApartamentoDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.CasaDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.ImovelBuilder.ApartamentoBuilder;
import br.com.edu.ifpb.pps.ImovelBuilder.CasaBuilder;
import br.com.edu.ifpb.pps.ImovelBuilder.Director.DirectorApartamento;
import br.com.edu.ifpb.pps.ImovelBuilder.Director.DirectorCasa;
import br.com.edu.ifpb.pps.ImovelBuilder.Registry.ImovelBuilderRegistry;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Imovel.Apartamento;
import br.com.edu.ifpb.pps.model.Imovel.Casa;

import java.util.HashMap;
import java.util.Map;

public final class AnuncioRegistry {
    private static final AnuncioRegistry REGISTRY = new AnuncioRegistry();
    private static final Map<String, Anuncio> armazem = new HashMap<>();

    static {
        carregarTemplates();
    }

    private static void carregarTemplates() {

        CasaDTO casaImovelDto = new CasaDTO();
        casaImovelDto.area = 120.0;
        casaImovelDto.localizacao = new Double[]{ -7.12, -34.88 };
        casaImovelDto.finalidade = FinalidadeEnum.ALUGUEL;
        casaImovelDto.qtdQuartos = 3;
        casaImovelDto.temQuintal = true;
        casaImovelDto.temJardim = false;

        DirectorCasa directorCasa = new DirectorCasa();
        Casa casaImovelPadrao = directorCasa.criarComDados(  (CasaBuilder) ImovelBuilderRegistry.create("CASA")  ,casaImovelDto);

        AnuncioDTO casaPadraoDto = new AnuncioDTO();
        casaPadraoDto.titulo = "Casa padrão";
        casaPadraoDto.anunciante = null;
        casaPadraoDto.tipo = "CASA";
        casaPadraoDto.preco = 20000.0;
        casaPadraoDto.imovel = casaImovelPadrao;

        Anuncio casaPadrao = new Anuncio(casaPadraoDto);
        adicionar("CASA_PADRAO", casaPadrao);


        ApartamentoDTO aptoImovelDto = new ApartamentoDTO();
        aptoImovelDto.area = 60.0;
        aptoImovelDto.localizacao = new Double[]{ -7.11, -34.87 };
        aptoImovelDto.finalidade = FinalidadeEnum.ALUGUEL;
        aptoImovelDto.andar = 2;
        aptoImovelDto.temElevador = true;
        aptoImovelDto.temCondominio = true;

        DirectorApartamento directorApto = new DirectorApartamento();
        Apartamento aptoImovelPadrao = (Apartamento) directorApto.criarComDados((ApartamentoBuilder)ImovelBuilderRegistry.create("APARTAMENTO"),aptoImovelDto);

        AnuncioDTO aptoPadraoDto = new AnuncioDTO();
        aptoPadraoDto.titulo = "Apartamento padrão";
        aptoPadraoDto.anunciante = null;
        aptoPadraoDto.tipo = "APARTAMENTO";
        aptoPadraoDto.preco = 15000.0;
        aptoPadraoDto.imovel = aptoImovelPadrao;

        Anuncio aptoPadrao = new Anuncio(aptoPadraoDto);
        adicionar("APTO_PADRAO", aptoPadrao);
    }
    public static AnuncioRegistry getRegistry() {
        return REGISTRY;
    }


    public static void adicionar(String chave, Anuncio anuncio) {
        if (chave == null || anuncio == null) {
            throw new IllegalArgumentException("Chave e anúncio não podem ser nulos");
        }
        armazem.put(chave, anuncio);
    }

    public static void remover(String chave) {
        armazem.remove(chave);
    }

    public static Anuncio buscar(String chave) {
        Anuncio prototipo = armazem.get(chave);
        if (prototipo == null) {
            throw new IllegalArgumentException("Anúncio não encontrado: " + chave);
        }
        return prototipo.copy();
    }
}