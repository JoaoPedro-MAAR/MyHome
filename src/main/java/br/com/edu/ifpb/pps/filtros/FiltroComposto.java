package br.com.edu.ifpb.pps.filtros;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ifpb.pps.model.Anuncio;

public class FiltroComposto implements FiltroAnuncio {

    private List<FiltroAnuncio> filtros;

    public FiltroComposto(List<FiltroAnuncio> filtros) {
        this.filtros = filtros;
    }

    @Override
    public List<Anuncio> filtrar(List<Anuncio> conteudo) {
        List<Anuncio> resultados = new ArrayList<>();
        for (FiltroAnuncio filtro : filtros) {
            resultados.addAll(filtro.filtrar(conteudo));
        }
        return resultados;
    }

    public void adicionarFiltro(FiltroAnuncio filtro) {
        filtros.add(filtro);
    }

    public void removerFiltro(FiltroAnuncio filtro) {
        filtros.remove(filtro);
    }

    public List<FiltroAnuncio> getFiltros() {
        return filtros;
    }

    public void clearFiltros() {
        filtros.clear();
    }

}
