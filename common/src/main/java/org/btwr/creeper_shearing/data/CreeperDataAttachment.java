package org.btwr.creeper_shearing.data;

import net.minecraft.world.entity.monster.Monster;
import org.btwr.creeper_shearing.data.impl.CreeperDataProvider;
import org.btwr.creeper_shearing.data.impl.DataKey;

import java.util.ServiceLoader;

public final class CreeperDataAttachment {

    private static final CreeperDataProvider PROVIDER = ServiceLoader.load(CreeperDataProvider.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No CreeperDataProvider registered for this platform"));

    public static <T> T get(Monster creeper, DataKey<T> key) {
        return PROVIDER.get(creeper, key);
    }

    public static <T> T getOrCreate(Monster creeper, DataKey<T> key) {
        return PROVIDER.getOrCreate(creeper, key);
    }

    public static <T> void set(Monster creeper, DataKey<T> key, T data) {
        PROVIDER.set(creeper, key, data);
    }

    private CreeperDataAttachment() {}
}
