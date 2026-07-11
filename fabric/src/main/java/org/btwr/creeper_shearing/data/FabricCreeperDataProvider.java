package org.btwr.creeper_shearing.data;

import net.minecraft.world.entity.monster.Creeper;

public class FabricCreeperDataProvider implements CreeperDataProvider {

    @Override
    public CreeperData get(Creeper creeper) {
        return creeper.getAttached(DataAttachmentsFabric.CREEPER_DATA);
    }

    @Override
    public CreeperData getOrCreate(Creeper creeper) {
        return creeper.getAttachedOrCreate(DataAttachmentsFabric.CREEPER_DATA);
    }

    @Override
    public void set(Creeper creeper, CreeperData data) {
        creeper.setAttached(DataAttachmentsFabric.CREEPER_DATA, data);
    }

}