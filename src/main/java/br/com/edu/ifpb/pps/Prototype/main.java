//package br.com.edu.ifpb.pps.Prototype;
//
//import br.com.edu.ifpb.pps.ImovelBuilder.ApartamentoBuilder;
//import br.com.edu.ifpb.pps.ImovelBuilder.Director.DiretorImovel;
//import br.com.edu.ifpb.pps.model.Anuncio;
//
//public class main {
//    public static void main(String[] args) {
//        DiretorImovel dir = new DiretorImovel();
//        AnuncioRegistry anun = new AnuncioRegistry(dir);
//        Anuncio casa = anun.buscar("CASA_PADRAO");
//        System.out.println(casa.getImovel());
//        Anuncio apto = anun.buscar("APTO_PADRAO");
//        System.out.println(apto.getImovel());
//        ApartamentoBuilder aptoBuilder = new ApartamentoBuilder();
//        dir.fazerApartamentoPadrao(aptoBuilder);
//        aptoBuilder.setAndar(5).setTemElevador(false).setFinalidade("Compra");
//        Anuncio apto_probre = new Anuncio("Apto pobre","Pobre",220.000,null,aptoBuilder.build() );
//        anun.adicionar("APTO_POBRE",apto_probre);
//        Anuncio aptoPobreClone =  anun.buscar("APTO_POBRE");
//        System.out.println("--------------------------------------");
//        System.out.println(apto_probre.getImovel());
//        System.out.println("---------------------------------------");
//        System.out.println(aptoPobreClone.getImovel());
//
//    }
//}
