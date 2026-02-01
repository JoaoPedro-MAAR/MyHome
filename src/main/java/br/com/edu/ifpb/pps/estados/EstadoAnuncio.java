package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public interface EstadoAnuncio {

    void setContext(Anuncio anuncio);

    void enviarParaModeracao();
    void aprovar();
    void reprovar();
    void suspender();
    void vender();
    void editar();

    String getNome();
}
