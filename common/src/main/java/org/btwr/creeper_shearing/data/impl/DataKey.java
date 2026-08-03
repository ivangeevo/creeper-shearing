package org.btwr.creeper_shearing.data.impl;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public final class DataKey<T> {
    private final ResourceLocation id;
    private final Supplier<T> defaultFactory;

    public DataKey(ResourceLocation id, Supplier<T> defaultFactory) {
        this.id = id;
        this.defaultFactory = defaultFactory;
    }

    public ResourceLocation id() {
        return id;
    }

    public T createDefault() {
        return defaultFactory.get();
    }
}
