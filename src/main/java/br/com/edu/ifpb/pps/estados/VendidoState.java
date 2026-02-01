package br.com.edu.ifpb.pps.estados;

import br.com.edu.ifpb.pps.model.Anuncio;

public class VendidoState implements EstadoAnuncio {

    private Anuncio anuncio;

    @Override
    public void setContext(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public void enviarParaModeracao() {
        avisarVenda();
    }

    public void aprovar() {
        avisarVenda();
    }

    public void reprovar() {
        avisarVenda();
    }

    public void vender() {
        avisarVenda();
    }

    public void suspender() {
        avisarVenda();
    }

    public void editar() {
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
