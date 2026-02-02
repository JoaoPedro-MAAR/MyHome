package br.com.edu.ifpb.pps.filtros;

import br.com.edu.ifpb.pps.model.Anuncio;

import java.util.List;

public class FiltroFaixaPreco implements FiltroAnuncio {
    private Double precoMinimo;
    private Double precoMaximo;

    public FiltroFaixaPreco(Double precoMinimo, Double precoMaximo) {
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        return conteudo.stream()
            .filter(anuncio -> {
                double preco = anuncio.getPreco();
                boolean atendeMiniimo = (precoMinimo == null) || (preco >= precoMinimo);
                boolean atendeMaximo = (precoMaximo == null) || (preco <= precoMaximo);
                return atendeMiniimo && atendeMaximo;
            })
            .toList();
    }

}
