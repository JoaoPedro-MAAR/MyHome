package br.com.edu.ifpb.pps.ImovelBuilder.Registry;

import br.com.edu.ifpb.pps.ImovelBuilder.ImovelBuilder;
import br.com.edu.ifpb.pps.model.Imovel.Imovel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class ImovelBuilderRegistry {

    private static final Map<String, Supplier<? extends ImovelBuilder<? extends Imovel>>> REGISTRY =
            new ConcurrentHashMap<>();

    private ImovelBuilderRegistry() {}

    public static void register(String tipo, Supplier<? extends ImovelBuilder<? extends Imovel>> supplier) {
        if (tipo == null || supplier == null) {
            throw new IllegalArgumentException("tipo e supplier nao podem ser null");
        }
        REGISTRY.put(tipo.toUpperCase(), supplier);
    }

    public static ImovelBuilder<?> create(String tipo) {
        Supplier<? extends ImovelBuilder<?>> supplier = REGISTRY.get(tipo);
        if (supplier == null) {
            throw new IllegalArgumentException("Nao ha builder registrado para: " + tipo);
        }
        return supplier.get();
    }

    public static List<String> getAllTipos() {
        return new ArrayList<>(REGISTRY.keySet());
    }
}