package org.btwr.creeper_shearing.datagen.provider;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.btwr.creeper_shearing.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {

    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.CREEPER_OYSTERS, "Creeper Oysters");

        translationBuilder.add("subtitles.creeper_shearing.creeper_sheared", "Creeper sheared");
    }
}