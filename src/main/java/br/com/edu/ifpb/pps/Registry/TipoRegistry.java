package br.com.edu.ifpb.pps.Registry;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

public final class TipoRegistry {


    private static final Set<String> TIPOS_REGISTRADOS = new HashSet<>();

    static {
        register("CASA");
        register("APARTAMENTO");
    }

    private TipoRegistry() {}

    public static void register(String tipo) {
        if (tipo != null) {
            TIPOS_REGISTRADOS.add(tipo.toUpperCase());
        }
    }

    public static boolean exists(String tipo) {
        return tipo != null && TIPOS_REGISTRADOS.contains(tipo.toUpperCase());
    }

    public static Set<String> getAllTipos() {
        return Collections.unmodifiableSet(TIPOS_REGISTRADOS);
    }
}