package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class RascunhoState implements EstadoAnuncio {

    @Override
    public void enviarParaModeracao(Anuncio anuncio) {
        anuncio.setEstado(new PendenteModeracaoState());
    }

    public void aprovar(Anuncio anuncio){
        System.out.println("Erro");
    }

    public void reprovar(Anuncio anuncio){
        System.out.println("Erro");
    }

    public void suspender(Anuncio anuncio){
        System.out.println("Erro");
    }

    public void vender(Anuncio anuncio){
        System.out.println("Erro");
    }

    public void editar(Anuncio anuncio){
        System.out.println("Erro");
    }

    @Override
    public String getNome() {
        return "Rascunho";
    }
}
