package br.com.edu.ifpb.pps.moderacao;

import java.util.List;

import br.com.edu.ifpb.pps.configuracao.Configuracao;
import br.com.edu.ifpb.pps.model.Anuncio;

public class ModeracaoTermo extends ModeracaoBase {

    @Override
    public boolean moderar(Anuncio anuncio) {
        List<String> termosProibidos = Configuracao.getInstance().getTermosProbidos();
        
        String textoAnalise = (anuncio.getTitulo() + " " + anuncio.getDescricao()).toLowerCase();
        
        for (String termo : termosProibidos) {
            if (textoAnalise.contains(termo.trim().toLowerCase())) {
                System.out.println("Anúncio com termo proibido.");
                return false;
            }
        }

        return verificarProximo(anuncio);
    }
}
