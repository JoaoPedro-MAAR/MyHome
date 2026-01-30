package br.com.edu.ifpb.pps.configuracao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Configuracao {

    private static Configuracao instance;
    private Properties propiedades;

    private Configuracao(){
        propiedades = new Properties();
        carregarArquivoProperties();
    }

    public static Configuracao getInstance(){
        if (instance == null){
            instance = new Configuracao();
        }
        return instance;
    }

    public void carregarArquivoProperties(){
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("configuracao.properties")) {
            if (input == null){
                return;
            }
            propiedades.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getTermosProbidos(){
        String texto = propiedades.getProperty("termosProibidos");
        if (texto != null && !texto.isEmpty()){
            return Arrays.asList(texto.split(","));
        }
        return new ArrayList<>();
    }

    public double getTaxaComissao(){
        String valor = propiedades.getProperty("taxaComissao", "0.0");
        return Double.parseDouble(valor);
    }

    public int getLimiteUploadFotos(){
        String valor = propiedades.getProperty("limiteUploadFotos", "0");
        return Integer.parseInt(valor);
    }

}
