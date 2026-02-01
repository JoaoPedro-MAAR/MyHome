package br.com.edu.ifpb.pps.TemplateContrato;

import br.com.edu.ifpb.pps.DTO.ContratoDTO;

public class GeradorContratoVenda extends GeradorContrato {

    @Override
    protected void gerarClausulasEspecificas(Contrato contrato, ContratoDTO dados) {
        // Implementar cláusulas específicas para contrato de venda
        contrato.adicionarClausula("Cláusula específica de venda 1...");
        contrato.adicionarClausula("Cláusula específica de venda 2...");
    }
}