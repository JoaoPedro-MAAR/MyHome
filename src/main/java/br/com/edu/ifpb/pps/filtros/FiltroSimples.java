package br.com.edu.ifpb.pps.filtros;

import java.util.List;
import java.util.function.Predicate;

import br.com.edu.ifpb.pps.model.Anuncio;

public abstract class FiltroSimples implements FiltroAnuncio {
    protected String parametro;
    protected Predicate<String> criterio;

    public FiltroSimples(String parametro, Predicate<String> criterio) {
        this.parametro = parametro;
        this.criterio = criterio;
    }

    public abstract List<Anuncio> filtrar(List<Anuncio> conteudo);
}
