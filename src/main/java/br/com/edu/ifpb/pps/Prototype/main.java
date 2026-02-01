package br.com.edu.ifpb.pps.Prototype;

import br.com.edu.ifpb.pps.Enum.FinalidadeEnum;
import br.com.edu.ifpb.pps.ImovelBuilder.ApartamentoBuilder;
import br.com.edu.ifpb.pps.Registry.AnuncioRegistry;
import br.com.edu.ifpb.pps.model.Anuncio;

public class main {
    public static void main(String[] args) {
        Anuncio casa = AnuncioRegistry.buscar("CASA_PADRAO");
        System.out.println(casa.getImovel());
        Anuncio apto = AnuncioRegistry.buscar("APTO_PADRAO");
        System.out.println(apto.getImovel());
        ApartamentoBuilder aptoBuilder = new ApartamentoBuilder();
        aptoBuilder.setAndar(5).setTemElevador(false).setFinalidade(FinalidadeEnum.ALUGUEL).setArea(200.0).setLocalizacao( new Double[]{ -7.11, -34.87 });
        Anuncio apto_probre = new Anuncio("Apto pobre",220.000,null,aptoBuilder.build() );
        AnuncioRegistry.adicionar("APTO_POBRE",apto_probre);
        Anuncio aptoPobreClone =  AnuncioRegistry.buscar("APTO_POBRE");
        System.out.println(apto_probre);
        System.out.println("---------------------------------------");
        System.out.println(aptoPobreClone);
        System.out.println("--------------------------------------");
        System.out.println(apto_probre.getImovel());
        System.out.println("---------------------------------------");
        System.out.println(aptoPobreClone.getImovel());

    }
}
