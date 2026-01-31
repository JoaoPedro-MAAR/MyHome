package br.com.edu.ifpb.pps.observador;

import br.com.edu.ifpb.pps.model.Anuncio;

public class ObservadorUsuario implements Observador {

    @Override
    public void atualizar(Anuncio anuncio) {
        String mensagem = "Anuncio: " + anuncio.getTitulo() + " mudou para o estado: " + anuncio.getEstado().getNome();
        anuncio.getAnunciante().getMeioDeNotificacao().enviar(mensagem, anuncio.getAnunciante().getEmail());
    }
}
