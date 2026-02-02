package br.com.edu.ifpb.pps.TemplateContrato;

import br.com.edu.ifpb.pps.DTO.ContratoDTO;

public class GeradorContratoAluguel extends GeradorContrato {

    @Override
    protected String gerarCabecalho(ContratoDTO dados) {
        return "CONTRATO DE ALUGUEL GERADO PELO SISTEMA MYHOME";
    }

    @Override
    protected void validarDadosEspecificos(ContratoDTO dados) {
        if (dados.dataInicio == null || dados.dataFim == null) {
            throw new IllegalArgumentException("Datas de início e fim do contrato devem ser fornecidas para contratos de aluguel.");
        }

        if (dados.dataFim.isBefore(dados.dataInicio)) {
            throw new IllegalArgumentException("Data de fim do contrato não pode ser anterior à data de início.");
        }

        if (dados.frequenciaPagamento == null || dados.frequenciaPagamento.isEmpty()) {
            throw new IllegalArgumentException("Frequência de pagamento deve ser especificada para contratos de aluguel.");
        }
    }

    @Override
    protected void gerarClausulasEspecificas(Contrato contrato, ContratoDTO dados) {
        contrato.adicionarClausula("Cláusula 3 - DO VALOR DO ALUGUEL: O LOCATÁRIO pagará ao LOCADOR o valor de R$ " + 
            dados.anuncio.getPreco() + ", com vencimento todo dia 10 de cada mês, de forma " + dados.frequenciaPagamento + ".");
        
        contrato.adicionarClausula("Cláusula 4 - DO PRAZO: O presente contrato vigorará pelo período de " +
            "iniciando-se em " + dados.dataInicio + " e terminando em " + dados.dataFim + ", podendo ser renovado mediante acordo entre as partes.");
        
        contrato.adicionarClausula("Cláusula 5 - DAS OBRIGAÇÕES DO LOCATÁRIO: O LOCATÁRIO se obriga a: a) Manter o imóvel em bom estado de conservação; " +
            "b) Pagar pontualmente o aluguel e demais encargos; c) Não realizar obras ou modificações sem autorização prévia do LOCADOR; " +
            "d) Restituir o imóvel nas mesmas condições em que o recebeu ao término do contrato.");
    }
}