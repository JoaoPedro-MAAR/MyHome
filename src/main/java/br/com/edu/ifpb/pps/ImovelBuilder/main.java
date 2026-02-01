package br.com.edu.ifpb.pps.ImovelBuilder;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ApartamentoDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.CasaDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.Factory.AnuncioFactory;
import br.com.edu.ifpb.pps.ImovelBuilder.Director.DirectorApartamento;
import br.com.edu.ifpb.pps.ImovelBuilder.Director.DirectorCasa;
import br.com.edu.ifpb.pps.Registry.TipoRegistry;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Imovel.Apartamento;
import br.com.edu.ifpb.pps.model.Imovel.Casa;
import br.com.edu.ifpb.pps.model.Usuario;

public class main {

    public static void main(String[] args) {

        System.out.println("Tipos:");

        System.out.println(TipoRegistry.getAllTipos());

        Usuario user1 = new Usuario("Uno","1@1.com" );
        Usuario user2 = new Usuario("Duos", "2@2.com");


        AnuncioDTO anuncioDto = new AnuncioDTO();
        anuncioDto.anunciante = user1;
        anuncioDto.preco=40.000;
        anuncioDto.tipo="CASA";
        anuncioDto.titulo="Ola minions";




        CasaDTO casaDto = new CasaDTO();


        casaDto.area = 180.0;
        casaDto.finalidade = FinalidadeEnum.VENDA;
        casaDto.localizacao = new Double[]{-3.73, -38.52};
        casaDto.qtdQuartos = 4;
        casaDto.temQuintal = true;

        CasaBuilder casaBuilder = new CasaBuilder();
        DirectorCasa diretorCasa = new DirectorCasa();
        Casa casa = diretorCasa.criarComDados(casaBuilder, casaDto);
        anuncioDto.imovel = casa;



        Anuncio anuncioCasa = AnuncioFactory.criar(anuncioDto);
        System.out.println(anuncioCasa);
        System.out.println(anuncioCasa.getImovel());


        ApartamentoDTO aptoDto = new ApartamentoDTO();

        aptoDto.area = 72.0;
        aptoDto.finalidade = FinalidadeEnum.ALUGUEL;
        aptoDto.localizacao = new Double[]{-3.74, -38.50};
        aptoDto.andar = 8;
        aptoDto.temElevador = true;




        ApartamentoBuilder aptoBuilder = new ApartamentoBuilder();
        DirectorApartamento diretorApto = new DirectorApartamento();
        Apartamento apto = diretorApto.criarComDados(aptoBuilder, aptoDto);

        AnuncioDTO anuncioAptoDTO = new AnuncioDTO();
        anuncioAptoDTO.imovel = apto;
        anuncioAptoDTO.tipo = "APARTAMENTO";
        anuncioAptoDTO.anunciante = user2;
        anuncioAptoDTO.titulo = "Apartamento Muito bom";
        anuncioAptoDTO.preco = 200.000;

        Anuncio anuncioApto = AnuncioFactory.criar(anuncioAptoDTO);
        System.out.println("Anuncio de apartamento");
        System.out.println(anuncioApto);
        System.out.println(anuncioApto.getImovel());

    }
}