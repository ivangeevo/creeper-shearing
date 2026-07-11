package org.btwr.creeper_shearing.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.btwr.creeper_shearing.Constants;

public class ModItems {

    public static Item CREEPER_OYSTERS;

    public static void initialize() {
        Constants.LOG.info("Registering Mod Items for " + Constants.MOD_ID);

        CREEPER_OYSTERS = register("creeper_oysters", new Item(new Item.Properties().food(ModFoodProperties.CREEPER_OYSTERS)));
    }

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), item);
    }

}