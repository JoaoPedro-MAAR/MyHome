package br.com.edu.ifpb.pps.Facade;

import br.com.edu.ifpb.pps.Banco.Banco;
import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Factory.AnuncioFactory;
import br.com.edu.ifpb.pps.Factory.ImovelFactory;
import br.com.edu.ifpb.pps.Registry.AnuncioRegistry;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;
import br.com.edu.ifpb.pps.model.Usuario;
import br.com.edu.ifpb.pps.notificacao.MeioDeNotificacao;

import java.util.ArrayList;
import java.util.List;

public class ClientFacade {
    private final Banco banco;
    private UserFacade userFacade;
    private AnuncioRegistry presetAnuncio;

    public ClientFacade() {
        this.banco = Banco.getInstance();
        userFacade = new UserFacade();

    }

    public ArrayList<Anuncio> getAllAnuncio(){
        return banco.getAllAnuncio();
    }

    public Boolean login(String email){
        return userFacade.login(email);
    };

    public void logout(){
        userFacade.logout();
    };

    public void setMeioDeNotificacao(MeioDeNotificacao meioDeNotificacao){
        userFacade.setMeioDeNotificacao(meioDeNotificacao);
    }

    private Imovel criarImovel(String tipo, ImovelDTO dtoImovel){
        Imovel imovel = ImovelFactory.criar(tipo, dtoImovel);
        return imovel;
    }

    public Anuncio buscarPorId(Integer id){
        return banco.buscarAnuncioId(id);
    }


    public void criarAnuncio(AnuncioDTO dtoAnuncio, ImovelDTO dtoImovel){

        dtoAnuncio.anunciante = this.userFacade.getCorrente();

        Imovel imovel = criarImovel(dtoAnuncio.tipo,dtoImovel);


        dtoAnuncio.imovel = imovel;

        salvarAnuncio(dtoAnuncio);

    }


    public Anuncio getanuncioConfigByChave(String chave){
        try{
        return AnuncioRegistry.buscar(chave);
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    public List<String> getAllPresets(){
        return AnuncioRegistry.listarPresets();
    }



    public void criarConfig(String chave,AnuncioDTO dtoAnuncio, ImovelDTO dtoImovel){
        dtoAnuncio.anunciante = null;
        Imovel imovel = criarImovel(dtoAnuncio.tipo,dtoImovel);
        dtoAnuncio.imovel = imovel;
        Anuncio anuncio = new Anuncio(dtoAnuncio);
        salvarConfig(chave, anuncio);
    }


    public List<Anuncio> listarAnuncioModeracao(){
        return banco.buscarPorEstadoNome("Pendente de Moderação");
    }

    public List<Anuncio> listartodosMenosUsuarioCorrente(){
        return banco.buscarPorNaoAnunciante(userFacade.getCorrente().getEmail());
    }

    public void aprovarAnuncio(Integer id){
        Anuncio anuncio = banco.buscarAnuncioId(id);
        anuncio.getEstado().aprovar();
    }

    public Usuario getUsuarioLogado(){
        return userFacade.getCorrente();
    }

    public void comprarAnuncio(Integer id){
        Anuncio anuncio = banco.buscarAnuncioId(id);
        anuncio.getEstado().vender();
        anuncio.setComprador(userFacade.getCorrente());
    }


    private void salvarConfig(String chave,Anuncio anuncio){
        AnuncioRegistry.adicionar(chave,anuncio);
    }

    private void salvarAnuncio(AnuncioDTO dtoAnuncio) {
        Anuncio anuncio = AnuncioFactory.criar(dtoAnuncio);
        banco.adicionarAnuncio(anuncio);
    }





}
