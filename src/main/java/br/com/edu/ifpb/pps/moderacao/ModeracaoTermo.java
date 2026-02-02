package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.configuracao.Configuracao;
import br.com.edu.ifpb.pps.model.Anuncio;

import java.util.List;

public class ModeracaoTermo extends ModeracaoBase {

    @Override
    public boolean moderar(Anuncio anuncio) {
        List<String> termosProibidos = Configuracao.getInstance().getTermosProbidos();
        
        String textoAnalise = (anuncio.getTitulo() + " ");
        
        for (String termo : termosProibidos) {
            if (textoAnalise.contains(termo.trim().toLowerCase())) {
                System.out.println("Anúncio com termo proibido.");
                return false;
            }
        }

        return verificarProximo(anuncio);
    }
}
