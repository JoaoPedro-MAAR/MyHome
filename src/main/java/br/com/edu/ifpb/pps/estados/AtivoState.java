package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class AtivoState implements EstadoAnuncio {

    @Override
    public void vender(Anuncio anuncio) {
        anuncio.setEstado(new VendidoState());
    }

    @Override
    public void suspender(Anuncio anuncio) {
        anuncio.setEstado(new SuspensoState());
    }


     public void editar(Anuncio anuncio) {
        anuncio.setEstado(new RascunhoState());
    }

    public void enviarParaModeracao(Anuncio anuncio) {
        System.out.println("Erro");
    }
   
    public void aprovar(Anuncio anuncio) {
        System.out.println("Erro");
    }

    public void reprovar(Anuncio anuncio) {
        System.out.println("Erro");
    }

    @Override
    public String getNome() {
        return "Ativo";
    }

}   
