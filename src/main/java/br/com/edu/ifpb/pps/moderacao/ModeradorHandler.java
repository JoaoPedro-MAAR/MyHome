package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.model.Anuncio;

public interface ModeradorHandler {

    ModeradorHandler setProximo(ModeradorHandler proximo);
    boolean moderar(Anuncio anuncio);
}
