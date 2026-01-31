package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class SuspensoState implements EstadoAnuncio {

    @Override
    public void editar(Anuncio anuncio) {
        anuncio.setEstado(new RascunhoState());
    }

    public void enviarParaModeracao(Anuncio anuncio) {
        System.out.println("Erro");
    }

    public void aprovar(Anuncio anuncio){
        System.out.println("Erro");
    }

    public void reprovar(Anuncio anuncio){
        System.out.println("Erro");
    }

    public void vender(Anuncio anuncio){
        System.out.println("Erro");
    }

    public void suspender(Anuncio anuncio){
        System.out.println("Erro");
    }

    @Override
    public String getNome() {
        return "Suspenso";
    }
}
