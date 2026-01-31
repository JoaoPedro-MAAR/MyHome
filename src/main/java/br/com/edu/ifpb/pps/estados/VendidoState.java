package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class VendidoState implements EstadoAnuncio {

    
    public void enviarParaModeracao(Anuncio anuncio) {
        avisarVenda();
    }

    public void aprovar(Anuncio anuncio) {
        avisarVenda();
    }

    public void reprovar(Anuncio anuncio) {
        avisarVenda();
    }

    public void vender(Anuncio anuncio) {
        avisarVenda();
    }

    public void suspender(Anuncio anuncio) {
        avisarVenda();
    }

    public void editar(Anuncio anuncio) {
        avisarVenda();
    }

    private void avisarVenda() {
        System.out.println("Anuncio Vendido");
    }

    @Override
    public String getNome() {
        return "Vendido";
    }

}
