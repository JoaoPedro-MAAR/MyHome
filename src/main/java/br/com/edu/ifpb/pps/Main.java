package br.com.edu.ifpb.pps;

import br.com.edu.ifpb.pps.DTO.AnuncioDTO;
import br.com.edu.ifpb.pps.DTO.Imovel.ImovelDTO;
import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.Facade.ClientFacade;
import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.notificacao.NotificacaoEmail;
import br.com.edu.ifpb.pps.observador.ObservadorLog;
import br.com.edu.ifpb.pps.observador.ObservadorUsuario;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientFacade fachada = new ClientFacade();

        fachada.login("araujo.aragao@academico.ifpb.edu.br");
        System.out.println("Logado: " + fachada.getUsuarioLogado().getNome());

        System.out.println("Anúncios iniciais: " + fachada.getAllAnuncio().size());

        AnuncioDTO dtoCasa = new AnuncioDTO();
        dtoCasa.titulo = "Casa Fraude"; dtoCasa.preco = 5.0; dtoCasa.tipo = "CASA";
        ImovelDTO imovelCasa = new ImovelDTO();
        imovelCasa.area = 500.0; imovelCasa.localizacao = new Double[]{0.0,0.0}; imovelCasa.finalidade = FinalidadeEnum.VENDA; imovelCasa.qtdQuartos = 10; imovelCasa.temQuintal=true;
        fachada.criarAnuncio(dtoCasa, imovelCasa);

        System.out.println("Anuncios agora: " + fachada.getAllAnuncio().size());

        AnuncioDTO dtoApto = new AnuncioDTO();
        dtoApto.titulo = "Apto Luxo"; dtoApto.preco = 500000.0; dtoApto.tipo = "APARTAMENTO";
        ImovelDTO imovelApto = new ImovelDTO();
        imovelApto.area = 100.0; imovelApto.localizacao = new Double[]{0.0,0.0}; imovelApto.finalidade = FinalidadeEnum.VENDA; imovelApto.andar=5; imovelApto.temElevador=true; imovelApto.temCondominio=true;
        fachada.criarAnuncio(dtoApto, imovelApto);

        System.out.println("Anuncios agora: " + fachada.getAllAnuncio().size());
        System.out.println("Todos anuncios: " + fachada.getAllAnuncio());

        Anuncio aptoCriado = fachada.buscarAnuncioTitulo("Apto Luxo");
        Anuncio casaFraude = fachada.buscarAnuncioTitulo("Casa Fraude");




        System.out.println("\n=== Criando via Preset (Prototype) ===");
        AnuncioDTO anunDto = new AnuncioDTO();
        anunDto.titulo = "Casa do clone";
        anunDto.preco = 200000.0;
        System.out.println(fachada.getAllPresets());
        fachada.criarAnuncioApartirDePreset("CASA_PADRAO", anunDto);
        System.out.println("Anúncio criado via preset!");
        System.out.println("Anuncios agora: " + fachada.getAllAnuncio().size());
        System.out.println("Todos anuncios: " + fachada.getAllAnuncio());


        ImovelDTO iConfig = new ImovelDTO(); iConfig.area = 999.0; iConfig.localizacao=new Double[]{0.0,0.0}; iConfig.finalidade=FinalidadeEnum.VENDA; iConfig.qtdQuartos=5; iConfig.temQuintal=true;
        AnuncioDTO aConfig = new AnuncioDTO(); aConfig.titulo="Modelo Mansão"; aConfig.preco=1000000.0; aConfig.tipo="CASA";
        fachada.criarConfig("MANSAO_MODELO", aConfig, iConfig);
        System.out.println(fachada.getAllPresets());

        System.out.println("\n=\n=");

        fachada.setMeioDeNotificacao(new NotificacaoEmail());

        if (aptoCriado != null) {
            fachada.adicionarObservador(aptoCriado.getId(), new ObservadorLog());
            fachada.adicionarObservador(aptoCriado.getId(), new ObservadorUsuario());
        }

        if(casaFraude != null){
            fachada.adicionarObservador(casaFraude.getId(), new ObservadorLog());
            fachada.adicionarObservador(casaFraude.getId(), new ObservadorUsuario());
        }

        System.out.println("\n=== Processando Moderação ===");

        boolean resultadoApto = fachada.processarModeracao(aptoCriado.getId());
        System.out.println("Apto Luxo Aprovado? " + resultadoApto + " | Estado: " + aptoCriado.getEstado().getNome());

        boolean resultadoFraude = fachada.processarModeracao(casaFraude.getId());
        System.out.println("Casa Fraude Aprovada? " + resultadoFraude + " | Estado: " + casaFraude.getEstado().getNome());

        System.out.println("\n=== Realizando Venda ===");
        if (aptoCriado.getEstado().getNome().equals("Ativo")) {
            fachada.comprarAnuncio(aptoCriado.getId());
            System.out.println("Venda concluída. Contrato gerado.");
        }

        System.out.println("\n=\n=");
        System.out.println("\n=== 16. Teste de Diversos Filtros ===");
        System.out.println("--- Filtro: Preço entre 100k e 500k ---");
        List<Anuncio> filtradosPreco = fachada.filtrarAnuncios(null, 100000.0, 500000.0, null, null, null);
        for(Anuncio a : filtradosPreco) System.out.println(a.getTitulo() + " - R$ " + a.getPreco());

        System.out.println("--- Filtro: Título contendo 'Fraude' ---");
        List<Anuncio> filtradosTitulo = fachada.filtrarAnuncios("Fraude", null, null, null, null, null);
        for(Anuncio a : filtradosTitulo) System.out.println(a.getTitulo());

        System.out.println("--- Filtro: Tipo CASA ---");
        ArrayList<String> tipos = new ArrayList<>();
        tipos.add("CASA");
        List<Anuncio> filtradosTipo = fachada.filtrarAnuncios(null, null, null, tipos, null, null);
        for(Anuncio a : filtradosTipo) System.out.println(a.getTitulo() + " - " + a.getImovel().getClass().getSimpleName());
    }
}