package br.com.edu.ifpb.pps.observador;

import java.time.LocalDateTime;

import br.com.edu.ifpb.pps.model.Anuncio;

public class ObservadorLog implements Observador{

    @Override
    public void atualizar(Anuncio anuncio) {
        System.out.println("LOG: ["+ LocalDateTime.now() + "] " + anuncio.getTitulo() + "mudou para o estado: " + anuncio.getEstado().getNome());
    }
}
