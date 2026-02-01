package br.com.edu.ifpb.pps.moderacao;

import java.util.List;

import br.com.edu.ifpb.pps.configuracao.Configuracao;
import br.com.edu.ifpb.pps.model.Anuncio;

public class ModeracaoTermo extends ModeracaoBase {

    @Override
    public boolean moderar(Anuncio anuncio) {
       List<String> termosProbidios = Configuracao.getInstance().getTermosProbidos();
       String textoAnalise = (anuncio.getTitulo() + " ");

       for (String termo : termosProbidios) {
            if (textoAnalise.contains(termo.trim().toLowerCase())) {
                System.out.println("Anúncio com termo proibido.");
                return false;
            }
       }

       return super.moderar(anuncio);
    }
}
