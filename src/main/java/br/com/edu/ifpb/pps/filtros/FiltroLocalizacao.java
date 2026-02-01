package br.com.edu.ifpb.pps.filtros;

import java.util.List;

import br.com.edu.ifpb.pps.model.Anuncio;

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
