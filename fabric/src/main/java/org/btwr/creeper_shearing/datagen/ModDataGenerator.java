package org.btwr.creeper_shearing.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.btwr.creeper_shearing.datagen.provider.ModEntityTypeTagProvider;
import org.btwr.creeper_shearing.datagen.provider.ModLangProvider;
import org.btwr.creeper_shearing.datagen.provider.ModModelProvider;
import org.btwr.creeper_shearing.datagen.provider.ModRecipeProvider;

public class ModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModEntityTypeTagProvider::new);
        pack.addProvider(ModLangProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }

}