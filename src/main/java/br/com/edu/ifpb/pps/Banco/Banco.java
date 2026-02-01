package br.com.edu.ifpb.pps.Banco;

import br.com.edu.ifpb.pps.Seeder.ImportCSV;
import br.com.edu.ifpb.pps.configuracao.Configuracao;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private static Banco instance;
    private  ArrayList<Usuario> tb_usuarios;
    private  ArrayList<Anuncio> tb_anuncio;


    private Banco(){
        tb_usuarios = new ArrayList<>();
        tb_anuncio = new ArrayList<>();
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

    private void carregarDadosDoCSV() {
        Configuracao config = Configuracao.getInstance();
        ImportCSV importador = new ImportCSV();

        String pathUsers = config.getCaminhoArquivoUsuarios();
        String pathAnuncios = config.getCaminhoArquivoAnuncios();
        List<Usuario> usuariosImportados = new ArrayList<>();

        if (pathUsers != null) {
            usuariosImportados = importador.importarUsuarios(pathUsers);
            for(Usuario user : usuariosImportados){
                adicionarUsuario(user);
            }
        }

        if (pathAnuncios != null){
            List<Anuncio> anunciosImportados = importador.importarAnuncios(pathAnuncios, usuariosImportados);
            for(Anuncio anuncio : anunciosImportados){
                adicionarAnuncio(anuncio);
            }
        }

    }



    public  void adicionarUsuario(Usuario user){
        Integer lastId = 0;
        if(tb_usuarios.isEmpty()){
            lastId=0;
        }
        else if(tb_usuarios.getLast().getId() != null){
            lastId=tb_usuarios.getLast().getId();
        }
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
        Integer lastId = 0;
        if(tb_anuncio.isEmpty()){
            lastId=0;
        }
        else if(tb_anuncio.getLast().getId() != null){
            lastId=tb_anuncio.getLast().getId();
        }
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

    public List<Anuncio> buscarPorEstadoNome(String nome) {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        for (Anuncio anuncio : tb_anuncio) {
            if (anuncio.getEstado().getNome().equalsIgnoreCase(nome))
                anuncios.add(anuncio);
        }
        return anuncios;
    }

    public List<Anuncio> buscarPorNaoAnunciante(String email){
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        for (Anuncio anuncio : tb_anuncio) {
            if (!anuncio.getAnunciante().getEmail().equalsIgnoreCase(email))
                anuncios.add(anuncio);
        }
        return anuncios;
    }

    public Anuncio buscarAnuncioId(Integer id){
        for (Anuncio anuncio : tb_anuncio){
            if (anuncio.getId().equals(id))
                return anuncio;
        }
        return null;
    }

    public ArrayList<Anuncio> getAllAnuncio(){
        return tb_anuncio;
    }
}
