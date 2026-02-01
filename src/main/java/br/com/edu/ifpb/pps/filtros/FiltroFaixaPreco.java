package br.com.edu.ifpb.pps.filtros;

import java.util.List;

import br.com.edu.ifpb.pps.model.Anuncio;

public class FiltroFaixaPreco implements FiltroAnuncio {
    private double precoMinimo;
    private double precoMaximo;

    public FiltroFaixaPreco(double precoMinimo, double precoMaximo) {
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        return conteudo.stream()
                .filter(anuncio -> anuncio.getPreco() >= precoMinimo && anuncio.getPreco() <= precoMaximo)
                .toList();
    }

}
