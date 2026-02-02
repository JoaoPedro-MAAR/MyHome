package br.com.edu.ifpb.pps.TemplateContrato;

import br.com.edu.ifpb.pps.DTO.ContratoDTO;
import br.com.edu.ifpb.pps.configuracao.Configuracao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class GeradorContrato {
    public final Contrato gerarContrato(ContratoDTO dados) {
        validarDados(dados);
        validarDadosEspecificos(dados);
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
        if (dados.anuncio.getComprador() == null) {
            throw new IllegalArgumentException("Interessado não pode ser nulo");
        }
        if (dados.anuncio.getAnunciante() == null) {
            throw new IllegalArgumentException("Anunciante não pode ser nulo");
        }
    }

    protected abstract void validarDadosEspecificos(ContratoDTO dados);

    protected Contrato criarContratoVazio(ContratoDTO dados) {
        Contrato contrato = new Contrato(dados.anuncio.getId(), "");
        contrato.setCabecalho(gerarCabecalho(dados));
        return contrato;
    }

    protected abstract String gerarCabecalho(ContratoDTO dados);

    protected void gerarClausulasPadrao(Contrato contrato, ContratoDTO dados) {
        
        contrato.adicionarClausula("Cláusula 1 - Das partes: O presente contrato é celebrado entre " + 
            dados.anuncio.getAnunciante().getNome() + ", doravante denominado ANUNCIANTE, e " + 
            dados.anuncio.getComprador().getNome() + ", doravante denominado INTERESSADO.");
        contrato.adicionarClausula("Cláusula 2 - - Do objeto: O objeto deste contrato é o imóvel anunciado, conforme descrito no anúncio de ID " + 
            dados.anuncio.getId() + ".");
    }

    protected abstract void gerarClausulasEspecificas(Contrato contrato, ContratoDTO dados);

    protected void gerarArquivo(Contrato contrato) {
        Configuracao config = Configuracao.getInstance();
        Path path = Paths.get(config.getCaminhoContratos(), contrato.getId() + ".txt");
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
