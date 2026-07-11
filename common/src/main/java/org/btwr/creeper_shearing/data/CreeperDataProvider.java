package org.btwr.creeper_shearing.data;

import net.minecraft.world.entity.monster.Creeper;

public interface CreeperDataProvider {
    CreeperData get(Creeper creeper);
    CreeperData getOrCreate(Creeper creeper);
    void set(Creeper creeper, CreeperData data);
}
