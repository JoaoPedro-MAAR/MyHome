package br.com.edu.ifpb.pps.filtros;

import br.com.edu.ifpb.pps.model.Anuncio;

import java.util.List;

public class FiltroLocalizacao implements FiltroAnuncio {
    private Double[] coordenadas;

    public FiltroLocalizacao(Double[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        return conteudo.stream()
                .filter(a -> a.getImovel().getLocalizacao().equals(coordenadas))
                .toList();
    }
}
