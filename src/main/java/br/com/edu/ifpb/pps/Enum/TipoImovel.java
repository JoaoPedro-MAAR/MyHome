package br.com.edu.ifpb.pps.Enum;

public enum TipoImovel {
    CASA,APTO,TERRENO,GALPAO;

    public static TipoImovel fromString(String tipo) {
        for (TipoImovel t : TipoImovel.values()) {
            if (t.name().equalsIgnoreCase(tipo)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tipo de imóvel inválido: " + tipo);
    }
}
