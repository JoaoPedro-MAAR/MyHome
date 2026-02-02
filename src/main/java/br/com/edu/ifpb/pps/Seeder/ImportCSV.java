package br.com.edu.ifpb.pps.Seeder;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.Factory.AnuncioFactory;
import br.com.edu.ifpb.pps.Factory.ImovelFactory;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;
import br.com.edu.ifpb.pps.model.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportCSV {

    public List<Usuario> importarUsuarios(String path) {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            boolean isCabecalho = true;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                if (isCabecalho) {
                    isCabecalho = false;
                    continue;
                }

                String[] dados = linha.split(";");

                if (dados.length >= 2) {
                    String email = dados[0].trim();
                    String nome = dados[1].trim();

                    Usuario usuario = new Usuario(nome, email);

                    if (dados.length >= 3) {
                        boolean admin = Boolean.parseBoolean(dados[2].trim());
                        usuario.setAdmin(admin);
                    } else {
                        usuario.setAdmin(false);
                    }

                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public List<Anuncio> importarAnuncios(String caminhoArquivo, List<Usuario> todosUsuarios) {
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

                    Usuario anunciante = null;
                    for (Usuario u : todosUsuarios) {
                        if (u.getEmail().equalsIgnoreCase(emailUser)) {
                            anunciante = u;
                            break;
                        }
                    }
                    if (anunciante == null) {
                        continue;
                    }

                    AnuncioDTO anuncioDTO = new AnuncioDTO();
                    anuncioDTO.titulo = titulo;
                    anuncioDTO.preco = preco;
                    anuncioDTO.anunciante = anunciante;
                    anuncioDTO.tipo = tipo;
                    ImovelDTO dto;
                    switch (tipo) {
                        case "CASA":
                             dto = montarCasa(dados);
                            break;
                        case "APARTAMENTO":
                             dto = montarApartamento(dados);
                            break;
                        default:
                            continue;
                    }
                    Imovel imovel = ImovelFactory.criar(anuncioDTO.tipo, dto);
                    anuncioDTO.imovel=imovel;
                    try {
                        Anuncio anuncioPronto = AnuncioFactory.criar(anuncioDTO);
                        anuncios.add(anuncioPronto);
                    }
                    catch(IllegalArgumentException e){
                        e.printStackTrace();
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        return anuncios;
    }


    private ImovelDTO montarCasa(String[] dados) {
        ImovelDTO dto = new ImovelDTO();
        Double[] localizacao = new Double[]{Double.parseDouble(dados[5]),Double.parseDouble(dados[6])};
        dto.localizacao = localizacao;
        dto.area = Double.parseDouble(dados[4].trim());
        dto.qtdQuartos = Integer.parseInt(dados[8].trim());
        dto.finalidade = FinalidadeEnum.valueOf(dados[7].toUpperCase().trim());
        dto.temQuintal = Boolean.parseBoolean(dados[9].trim());
        dto.temJardim = Boolean.parseBoolean(dados[10].trim());
        return dto;
    }

    private ImovelDTO montarApartamento(String[] dados) {
        ImovelDTO dto = new ImovelDTO();
        Double[] localizacao = new Double[]{Double.parseDouble(dados[5]),Double.parseDouble(dados[6])};
        dto.localizacao = localizacao;
        dto.area = Double.parseDouble(dados[4].trim());
        dto.andar = Integer.parseInt(dados[8].trim());
        dto.finalidade = FinalidadeEnum.valueOf(dados[7].toUpperCase().trim());
        dto.temElevador = Boolean.parseBoolean(dados[9].trim());
        dto.temCondominio = Boolean.parseBoolean(dados[10].trim());
        return dto;
    }
}




