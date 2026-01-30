package br.com.edu.ifpb.pps.configuracao;

import java.util.Properties;

public class Configuracao {

    private static Configuracao configuracao;

    private Properties propriedades;

    private Configuracao() {
        
    }

    public static Configuracao getConfiguracao() {
        if (configuracao == null) {
            configuracao = new Configuracao();
        }
        return configuracao;
    }

    

}
