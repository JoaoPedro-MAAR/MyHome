package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.configuracao.Configuracao;
import br.com.edu.ifpb.pps.model.Anuncio;

import java.util.List;

public class ModeracaoTermo extends ModeracaoBase {

    @Override
    public boolean moderar(Anuncio anuncio) {
       List<String> termosProbidios = Configuracao.getInstance().getTermosProbidos();
       String textoAnalise = (anuncio.getTitulo() + " ");

       for (String termo : termosProbidios) {
            if (textoAnalise.contains(termo.trim().toLowerCase())) {
                return false;
            }
       }

       return super.moderar(anuncio);
    }
}
