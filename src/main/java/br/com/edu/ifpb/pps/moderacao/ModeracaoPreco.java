package br.com.edu.ifpb.pps.moderacao;

import br.com.edu.ifpb.pps.model.Anuncio;

public class ModeracaoPreco extends ModeracaoBase {

    @Override
    public boolean moderar(Anuncio anuncio) {
        if (anuncio.getPreco() <= 10) {
            System.out.println("Anúncio com preço inválido");
            return false;
        }

        return super.moderar(anuncio);
    }
} 
