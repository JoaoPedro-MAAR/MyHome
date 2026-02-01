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
        
        if (dados.anuncio == null) {
            throw new IllegalArgumentException("Anúncio não pode ser nulo");
        }
        if (dados.interessado == null) {
            throw new IllegalArgumentException("Interessado não pode ser nulo");
        }
        if (dados.anunciante == null) {
            throw new IllegalArgumentException("Anunciante não pode ser nulo");
        }
    }

    protected Contrato criarContratoVazio(ContratoDTO dados) {
        Contrato contrato = new Contrato(dados.anuncio.getId(), "");
        contrato.setCabecalho(gerarCabecalho(dados));
        return contrato;
    }

    protected abstract String gerarCabecalho(ContratoDTO dados);

    protected void gerarClausulasPadrao(Contrato contrato, ContratoDTO dados) {
        // cláusulas comuns a qualquer contrato (foro, obrigações gerais, etc.)
        contrato.adicionarClausula("Cláusula 1 - Das partes: O presente contrato é celebrado entre " + 
            dados.anunciante.getNome() + ", doravante denominado ANUNCIANTE, e " + 
            dados.interessado.getNome() + ", doravante denominado INTERESSADO.");
        contrato.adicionarClausula("Cláusula 2 - - Do objeto: O objeto deste contrato é o imóvel anunciado, conforme descrito no anúncio de ID " + 
            dados.anuncio.getId() + ".");
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
