package br.com.edu.ifpb.pps.Facade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifpb.pps.Banco.Banco;
import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.ContratoDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.Enum.TipoImovel;
import br.com.edu.ifpb.pps.Factory.AnuncioFactory;
import br.com.edu.ifpb.pps.Factory.ImovelFactory;
import br.com.edu.ifpb.pps.Registry.AnuncioRegistry;
import br.com.edu.ifpb.pps.TemplateContrato.GeradorContratoAluguel;
import br.com.edu.ifpb.pps.TemplateContrato.GeradorContratoVenda;
import br.com.edu.ifpb.pps.filtros.FiltroAnuncio;
import br.com.edu.ifpb.pps.filtros.FiltroFaixaPreco;
import br.com.edu.ifpb.pps.filtros.FiltroTipoImovel;
import br.com.edu.ifpb.pps.filtros.FiltroTitulo;
import br.com.edu.ifpb.pps.filtros.FiltroLocalizacao;
import br.com.edu.ifpb.pps.filtros.FiltroPossuiCondominio;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Usuario;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;
import br.com.edu.ifpb.pps.notificacao.MeioDeNotificacao;

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
        this.gerarContrato(id);
    }


    private void salvarConfig(String chave,Anuncio anuncio){
        AnuncioRegistry.adicionar(chave,anuncio);
    }

    private void salvarAnuncio(AnuncioDTO dtoAnuncio) {
        Anuncio anuncio = AnuncioFactory.criar(dtoAnuncio);
        banco.adicionarAnuncio(anuncio);
    }

    public List<Anuncio> buscarAnunciosComFiltro(List<FiltroAnuncio> filtrosObrigatorios, List<FiltroAnuncio> filtrosOpcionais){
        banco.clearFiltros();
        for (FiltroAnuncio filtro : filtrosObrigatorios){
            banco.addFiltroObrigatorio(filtro);
        }
        for (FiltroAnuncio filtro : filtrosOpcionais){
            banco.addFiltroOpcional(filtro);
        }
        
        return banco.getAnunciosFiltrados();
    }

    public List<Anuncio> filtrarAnuncios(String titulo, Double precoMin, Double precoMax, ArrayList<String> tipoImovel, Double[] localizacao, Boolean temCondominio) {
        List<FiltroAnuncio> filtrosObrigatorios = new ArrayList<>();
        List<FiltroAnuncio> filtrosOpcionais = new ArrayList<>();
        
        if (titulo != null) {
            filtrosObrigatorios.add(new FiltroTitulo(titulo));
        }
        
        if (precoMin != null && precoMax != null) {
            filtrosObrigatorios.add(new FiltroFaixaPreco(precoMin, precoMax));
        }
        
        if (tipoImovel != null) {
            ArrayList<TipoImovel> tiposImovelEnum = new ArrayList<>();
            for (String tipo : tipoImovel) {
                tiposImovelEnum.add(TipoImovel.fromString(tipo));
            }
            filtrosOpcionais.add(new FiltroTipoImovel(tiposImovelEnum));
        }

        if (localizacao != null) {
            filtrosOpcionais.add(new FiltroLocalizacao(localizacao));
        }

        if (temCondominio != null) {
            filtrosOpcionais.add(new FiltroPossuiCondominio(temCondominio));
        }
        
        return buscarAnunciosComFiltro(filtrosObrigatorios, filtrosOpcionais);
    }

    public void gerarContrato(Integer idAnuncio) {
        Anuncio anuncio = banco.buscarAnuncioId(idAnuncio);

        if (anuncio.getImovel().getFinalidade().equals(FinalidadeEnum.ALUGUEL)) {
            GeradorContratoAluguel gerador = new GeradorContratoAluguel();
            ContratoDTO contrato = new ContratoDTO();

            contrato.anuncio = anuncio;
            contrato.dataInicio = LocalDateTime.now().toLocalDate();
            contrato.dataFim = LocalDateTime.now().toLocalDate().plus(java.time.Period.ofMonths(1));
            contrato.frequenciaPagamento = "Mensal";

            gerador.gerarContrato(contrato);

        } else if (anuncio.getImovel().getFinalidade().equals(FinalidadeEnum.VENDA)) {
            GeradorContratoVenda gerador = new GeradorContratoVenda();
            ContratoDTO contrato = new ContratoDTO();
            
            contrato.anuncio = anuncio;
            contrato.formaPagamento = "À vista";

            gerador.gerarContrato(contrato);
        } else {
            System.out.println("Finalidade do imóvel desconhecida. Não foi possível gerar o contrato.");
        }
    }
}
