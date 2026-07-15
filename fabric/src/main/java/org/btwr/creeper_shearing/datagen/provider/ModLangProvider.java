package org.btwr.creeper_shearing.datagen.provider;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {

    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder tb) {
        tb.add(ModBlocks.CREEPER_OYSTER_BLOCK, "Block of Oysters");
        tb.add(ModBlocks.CREEPER_OYSTER_SLAB, "Oyster Slab");

        tb.add(ModItems.CREEPER_OYSTERS, "Creeper Oysters");

        tb.add("subtitles.creeper_shearing.creeper_sheared", "Creeper sheared");
    }
}