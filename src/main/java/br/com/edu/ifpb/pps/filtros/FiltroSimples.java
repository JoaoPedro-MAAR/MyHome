package br.com.edu.ifpb.pps.filtros;

import br.com.edu.ifpb.pps.model.Anuncio;

import java.util.List;
import java.util.function.Predicate;

public abstract class FiltroSimples implements FiltroAnuncio {
    protected String parametro;
    protected Predicate<String> criterio;

    public FiltroSimples(String parametro, Predicate<String> criterio) {
        this.parametro = parametro;
        this.criterio = criterio;
    }

    public abstract List<Anuncio> filtrar(List<Anuncio> conteudo);
}
