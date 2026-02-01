package br.com.edu.ifpb.pps.TemplateContrato;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import br.com.edu.ifpb.pps.DTO.ContratoDTO;

public abstract class GeradorContrato {
    public final Contrato gerarContrato(ContratoDTO dados) {
        validarDados(dados);
        Contrato contrato = criarContratoVazio(dados);
        gerarClausulasPadrao(contrato, dados);
        gerarClausulasEspecificas(contrato, dados);
        gerarArquivo(contrato);
        return contrato;
    }

    protected void validarDados(ContratoDTO dados) {
        // validações comuns (campos obrigatórios, datas, CPF/CNPJ etc.)
    }

    protected Contrato criarContratoVazio(ContratoDTO dados) {
        Contrato contrato = new Contrato(dados.anuncio.getId(), "");
        contrato.setCabecalho(gerarCabecalho(dados));
        return contrato;
    }

    protected String gerarCabecalho(ContratoDTO dados) {
        // texto comum de cabeçalho (pode ser sobrescrito se quiser)
        return "CONTRATO GERADO PELO SISTEMA MYHOME";
    }

    protected void gerarClausulasPadrao(Contrato contrato, ContratoDTO dados) {
        // cláusulas comuns a qualquer contrato (foro, obrigações gerais, etc.)
        contrato.adicionarClausula("Cláusula 1 - Relação entre as partes...");
        contrato.adicionarClausula("Cláusula 2 - Foro da comarca de ...");
    }

    protected abstract void gerarClausulasEspecificas(Contrato contrato, ContratoDTO dados);

    protected void gerarArquivo(Contrato contrato) {
        Path path = Paths.get("contratos", contrato.getId() + ".txt");
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(contrato.getCabecalho()).append(System.lineSeparator())
        .append(System.lineSeparator());
        for (String clausula : contrato.getClausulas()) {
            sb.append(clausula).append(System.lineSeparator()).append(System.lineSeparator());
        }

        try {
            Files.writeString(path, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
