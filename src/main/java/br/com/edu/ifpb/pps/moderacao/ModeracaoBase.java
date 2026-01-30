package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.model.Anuncio;

public abstract class ModeracaoBase implements ModeracaoHandler {

    private ModeracaoHandler proximo;

    @Override
    public ModeracaoHandler setNext(ModeracaoHandler proximo) {
        this.proximo = proximo;
        return proximo;
    }

    protected boolean verificarProximo(Anuncio Anuncio) {
        if (proximo == null) {
            return true;
        }
        return proximo.moderar(Anuncio);
    }
}
