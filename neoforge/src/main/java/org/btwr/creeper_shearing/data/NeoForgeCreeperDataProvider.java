package org.btwr.creeper_shearing.data;

import net.minecraft.world.entity.monster.Creeper;

public class NeoForgeCreeperDataProvider implements CreeperDataProvider {

    @Override
    public CreeperData get(Creeper creeper) {
        return creeper.getData(DataAttachmentsNeoForge.CREEPER_DATA.get());
    }

    @Override
    public CreeperData getOrCreate(Creeper creeper) {
        return creeper.getData(DataAttachmentsNeoForge.CREEPER_DATA.get());
    }

    @Override
    public void set(Creeper creeper, CreeperData data) {
        creeper.setData(DataAttachmentsNeoForge.CREEPER_DATA.get(), data);
    }

}