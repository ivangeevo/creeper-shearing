package org.btwr.creeper_shearing.data;

import net.minecraft.world.entity.monster.Creeper;

import java.util.ServiceLoader;

public final class CreeperDataAttachment {

    private static final CreeperDataProvider PROVIDER = ServiceLoader.load(CreeperDataProvider.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No CreeperDataProvider registered for this platform"));

    public static CreeperData get(Creeper creeper) {
        return PROVIDER.get(creeper);
    }

    public static CreeperData getOrCreate(Creeper creeper) {
        return PROVIDER.getOrCreate(creeper);
    }

    public static void set(Creeper creeper, CreeperData data) {
        PROVIDER.set(creeper, data);
    }

    private CreeperDataAttachment() {}
}
