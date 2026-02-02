package br.com.edu.ifpb.pps.TemplateContrato;

import br.com.edu.ifpb.pps.DTO.ContratoDTO;

public class GeradorContratoVenda extends GeradorContrato {

    @Override
    protected String gerarCabecalho(ContratoDTO dados) {
        return "CONTRATO DE VENDA GERADO PELO SISTEMA MYHOME";
    }

    @Override
    protected void validarDadosEspecificos(ContratoDTO dados) {
        if (dados.formaPagamento == null || dados.formaPagamento.isEmpty()) {
            throw new IllegalArgumentException("Forma de pagamento não pode ser nula ou vazia para contrato de venda.");
        }

    }

    @Override
    protected void gerarClausulasEspecificas(Contrato contrato, ContratoDTO dados) {
        contrato.adicionarClausula("Cláusula 3 - DO PREÇO E FORMA DE PAGAMENTO: O imóvel objeto deste contrato é vendido pelo valor de R$ " + 
            dados.anuncio.getPreco() + ", a ser pago " + dados.formaPagamento + ".");
        
        contrato.adicionarClausula("Cláusula 4 - DA TRANSFERÊNCIA DE PROPRIEDADE: A transferência definitiva da propriedade do imóvel ao COMPRADOR " +
            "ocorrerá mediante o registro da escritura pública no Cartório de Registro de Imóveis competente, após a quitação integral do preço.");
        
        contrato.adicionarClausula("Cláusula 5 - DAS CONDIÇÕES DO IMÓVEL: O VENDEDOR declara que o imóvel encontra-se livre e desembaraçado de ônus, " +
            "dívidas, impostos ou quaisquer encargos, sendo entregue no estado em que se encontra, conforme vistoria realizada pelo COMPRADOR.");
    }
}