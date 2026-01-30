package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.model.Anuncio;

public interface ModeracaoHandler {

    ModeracaoHandler setNext(ModeracaoHandler proximo);
    boolean moderar(Anuncio anuncio);
}
