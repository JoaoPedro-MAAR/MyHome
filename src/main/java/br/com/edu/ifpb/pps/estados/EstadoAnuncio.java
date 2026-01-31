package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public interface EstadoAnuncio {

    void enviarParaModeracao(Anuncio anuncio);
    void aprovar(Anuncio anuncio);
    void reprovar(Anuncio anuncio);
    void suspender(Anuncio anuncio);
    void vender(Anuncio anuncio);
    void editar(Anuncio anuncio);

    String getNome();


}
