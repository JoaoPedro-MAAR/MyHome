package br.com.edu.ifpb.pps.DTO;

import br.com.edu.ifpb.pps.model.Anuncio;
import br.com.edu.ifpb.pps.model.Usuario;

import java.time.LocalDate;

public class ContratoDTO {
    public String cabecalho;
    public String[] clausulas;

    public Usuario anunciante;
    public Usuario interessado;

    // Anúncio guarda o valor do imóvel
    public Anuncio anuncio;

    // Datas
    public LocalDate dataInicio;
    public LocalDate dataFim;          
    public LocalDate dataAssinatura;

    // Específico para aluguel
    public String frequenciaPagamento; // Ex: mensal, anual

    // Específico para compra e venda
    public String formaPagamento; // Ex: à vista, financiado

}
