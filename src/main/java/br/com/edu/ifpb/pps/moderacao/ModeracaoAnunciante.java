package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.model.Anuncio;

public class ModeracaoAnunciante extends ModeracaoBase {

    @Override
    public boolean moderar(Anuncio anuncio) {
       if (anuncio.getAnunciante() == null) {
            System.out.println("Anúncio não possui anunciante.");
           return false;
       }

       return super.moderar(anuncio);
    }
}
