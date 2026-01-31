//package br.com.edu.ifpb.pps.Prototype;
//
//import br.com.edu.ifpb.pps.ImovelBuilder.ApartamentoBuilder;
//import br.com.edu.ifpb.pps.ImovelBuilder.CasaBuilder;
//import br.com.edu.ifpb.pps.ImovelBuilder.Director.DiretorImovel;
//import br.com.edu.ifpb.pps.model.Anuncio;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class AnuncioRegistry {
//
//    private Map<String, Anuncio> armazem = new HashMap<>();
//    private final DiretorImovel diretor;
//
//    public AnuncioRegistry(DiretorImovel diretor) {
//        this.diretor = diretor;
//
//        Anuncio casaPadrao = new Anuncio();
//        CasaBuilder casaBuilder = new CasaBuilder();
//        diretor.fazerCasaPadrao(casaBuilder);
//        casaPadrao.setImovel(casaBuilder.build());
//        ApartamentoBuilder builder = new ApartamentoBuilder();
//        Anuncio aptoPadrao = new Anuncio();
//        diretor.fazerApartamentoPadrao(builder);
//        aptoPadrao.setImovel(builder.build());
//
//        adicionar("CASA_PADRAO", casaPadrao);
//        adicionar("APTO_PADRAO", aptoPadrao);
//    }
//
//    public void adicionar(String chave, Anuncio anuncio) {
//        if (chave == null || anuncio == null) {
//            throw new IllegalArgumentException("Chave e anúncio não podem ser nulos");
//        }
//        armazem.put(chave, anuncio);
//    }
//
//    public void remover(String chave) {
//        armazem.remove(chave);
//    }
//
//    public Anuncio buscar(String chave) {
//        Anuncio prototipo = armazem.get(chave);
//        if (prototipo == null) {
//            throw new IllegalArgumentException("Anúncio não encontrado: " + chave);
//        }
//        return prototipo.copy();
//    }
//}