package br.com.edu.ifpb.pps.TemplateContrato;

import java.util.ArrayList;
import java.util.List;

public class Contrato {
    private String cabecalho;
    private List<String> clausulas = new ArrayList<>();
    private Integer id;

    public Contrato(Integer id, String cabecalho) {
        this.id = id;
        this.cabecalho = cabecalho;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public List<String> getClausulas() {
        return clausulas;
    }

    public Integer getId() {
        return id;
    }

    public void adicionarClausula(String clausula) {
        clausulas.add(clausula);
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

}
