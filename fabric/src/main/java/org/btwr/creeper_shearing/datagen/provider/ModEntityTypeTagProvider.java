package org.btwr.creeper_shearing.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;
import org.btwr.creeper_shearing.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {


    public ModEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(ModTags.EntityTypes.NEUTERABLE_CREEPERS).add(EntityType.CREEPER);
    }
}