package br.com.edu.ifpb.pps.TemplateContrato;

import br.com.edu.ifpb.pps.DTO.ContratoDTO;

public class GeradorContratoAluguel extends GeradorContrato {

    @Override
    protected void gerarClausulasEspecificas(Contrato contrato, ContratoDTO dados) {
        // Implementar cláusulas específicas para contrato de aluguel
        contrato.adicionarClausula("Cláusula específica de aluguel 1...");
        contrato.adicionarClausula("Cláusula específica de aluguel 2...");
    }
}