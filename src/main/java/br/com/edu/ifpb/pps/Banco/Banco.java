package br.com.edu.ifpb.pps.Banco;

import br.com.edu.ifpb.pps.Seeder.ImportCSV;
import br.com.edu.ifpb.pps.configuracao.Configuracao;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private static Banco instance;
    private  List<Usuario> tb_usuarios;
    private  List<Anuncio> tb_anuncio;


    private Banco(){
        tb_usuarios = new ArrayList<>();
        tb_anuncio = new ArrayList<>();
    }

    private void carregarDadosDoCSV() {
        Configuracao config = Configuracao.getInstance();
        ImportCSV importador = new ImportCSV();

        String pathUsers = config.getCaminhoArquivoUsuarios();
        String pathAnuncios = config.getCaminhoArquivoAnuncios();

        if (pathUsers != null) {
            List<Usuario> usuariosImportados = importador.importarUsuarios(pathUsers);
            tb_usuarios.addAll(usuariosImportados);
        }

        if (pathAnuncios != null){
            List<Anuncio> anunciosImportados = importador.importarAnuncios(pathAnuncios);
            tb_anuncio.addAll(anunciosImportados);
        }

    }

    public static Banco getInstance(){
        if(instance == null){
            instance = new Banco();
            try {
                instance.carregarDadosDoCSV();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return instance;
    }

    public  void adicionarUsuario(Usuario user){
        Integer lastId = tb_usuarios.getLast().getId();
        user.setId(lastId+1);
        tb_usuarios.add(user);
    }

    public  Usuario buscarUsuario(String email){
        for (Usuario user : tb_usuarios){
            if (user.getEmail().equalsIgnoreCase(email))
                return user;
        }
        return null;
    }

    public  void adicionarAnuncio(Anuncio anuncio){
        Integer lastId = tb_anuncio.getLast().getId();
        anuncio.setId(lastId+1);
        tb_anuncio.add(anuncio);
    }

    public  Anuncio buscarAnuncioTitulo(String titulo){

        for (Anuncio anuncio : tb_anuncio){
            if (anuncio.getTitulo().equalsIgnoreCase(titulo))
                return anuncio;
        }
        return null;
    }
}
