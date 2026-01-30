package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.model.Anuncio;

public abstract class ModeradorBase implements ModeradorHandler {

    private ModeradorHandler proximo;

    @Override
    public ModeradorHandler setProximo(ModeradorHandler proximo) {
        this.proximo = proximo;
        return proximo;
    }

    protected boolean verificarProximo(Anuncio Anuncio) {
        return proximo.moderar(Anuncio);
    }
}
