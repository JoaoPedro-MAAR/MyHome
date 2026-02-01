package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.model.Anuncio;

public abstract class ModeracaoBase implements ModeracaoHandler {

    private ModeracaoHandler proximo;

    @Override
    public ModeracaoHandler setNext(ModeracaoHandler proximo) {
        this.proximo = proximo;
        return proximo;
    }

    @Override
    public boolean moderar(Anuncio Anuncio) {
        if (proximo != null) {
            return proximo.moderar(Anuncio);
        }
        return true;
    }
}
