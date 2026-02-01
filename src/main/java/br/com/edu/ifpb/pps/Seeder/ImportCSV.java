package br.com.edu.ifpb.pps.Seeder;

import br.com.edu.ifpb.pps.Banco.Banco;
import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ApartamentoDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.CasaDTO;
import br.com.edu.ifpb.pps.Factory.AnuncioFactory;
import br.com.edu.ifpb.pps.ImovelBuilder.ApartamentoBuilder;
import br.com.edu.ifpb.pps.ImovelBuilder.CasaBuilder;
import br.com.edu.ifpb.pps.ImovelBuilder.Director.DirectorApartamento;
import br.com.edu.ifpb.pps.ImovelBuilder.Director.DirectorCasa;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;
import br.com.edu.ifpb.pps.model.Usuario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportCSV {

    public List<Usuario> importarUsuarios(String path){
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha;
            boolean isCabecalho = true;

            while ((linha = br.readLine()) != null) {

                if (linha.trim().isEmpty()) {
                    continue;
                }

                if (isCabecalho) {
                    isCabecalho = false;
                    if (linha.toLowerCase().startsWith("email")) {
                        continue;
                    }
                }

                String[] dados = linha.split(";");

                if (dados.length >= 2) {
                    String email = dados[0].trim();
                    String nome = dados[1].trim();

                    Usuario usuario = new Usuario(nome, email);

                    usuarios.add(usuario);
                }
            }

        } catch (IOException e) {
        }

        return usuarios;
    }

    public List<Anuncio> importarAnuncios(String caminhoArquivo) {
        List<Anuncio> anuncios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean isCabecalho = true;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                if (isCabecalho) {
                    isCabecalho = false;
                    if (linha.toUpperCase().startsWith("TIPO")) continue;
                }

                String[] dados = linha.split(";");

                if (dados.length < 5) {
                    continue;
                }

                try {
                    String tipo = dados[0].trim().toUpperCase();
                    String titulo = dados[1].trim();
                    double preco = Double.parseDouble(dados[2].trim());
                    String emailUser = dados[3].trim();

                    Usuario anunciante = Banco.getInstance().buscarUsuario(emailUser);

                    if (anunciante == null) {
                        continue;
                    }

                    AnuncioDTO anuncioDTO = new AnuncioDTO();
                    anuncioDTO.titulo = titulo;
                    anuncioDTO.preco = preco;
                    anuncioDTO.anunciante = anunciante;
                    anuncioDTO.tipo = tipo;

                    switch (tipo) {
                        case "CASA":
                            anuncioDTO.imovel = montarCasa(dados);
                            break;
                        case "APARTAMENTO":
                            anuncioDTO.imovel = montarApartamento(dados);
                            break;
                        default:
                            continue;
                    }
                    try {
                        Anuncio anuncioPronto = AnuncioFactory.criar(anuncioDTO);
                        anuncios.add(anuncioPronto);
                    }
                    catch(IllegalArgumentException e){
                    }

                } catch (NumberFormatException e) {
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
        }

        return anuncios;
    }


    private Imovel montarCasa(String[] dados) {
        CasaDTO dto = new CasaDTO();
        dto.area = Double.parseDouble(dados[4].trim());
        dto.qtdQuartos = Integer.parseInt(dados[5].trim());
        dto.temQuintal = Boolean.parseBoolean(dados[6].trim());

        CasaBuilder builder = new CasaBuilder();
        DirectorCasa director = new DirectorCasa();

        return director.criarComDados(builder, dto);
    }

    private Imovel montarApartamento(String[] dados) {
        ApartamentoDTO dto = new ApartamentoDTO();
        dto.area = Double.parseDouble(dados[4].trim());
        dto.andar = Integer.parseInt(dados[5].trim());


        ApartamentoBuilder builder = new ApartamentoBuilder();
        DirectorApartamento director = new DirectorApartamento();

        return director.criarComDados(builder, dto);
    }
}




