package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.model.Anuncio;

public class ModeradorTermos extends ModeradorBase {

    @Override
    public boolean moderar(Anuncio anuncio) {
        return true;
    }
}
