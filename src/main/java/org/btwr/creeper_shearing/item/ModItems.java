package org.btwr.creeper_shearing.item;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import org.btwr.creeper_shearing.CreeperShearingMod;
import org.btwr.creeper_shearing.config.ModConfig;
import turniplabs.halplibe.helper.ItemBuilder;

public class ModItems {
	public static Item CREEPER_OYSTERS;

	public static void afterItemInit() {
		CREEPER_OYSTERS = registerItem("creeper_oysters", (key, namespaceId, id) ->
			new ItemFood(key, namespaceId, id, 1, 0, false, 64)
		);
	}

	private static <T extends Item> T registerItem(String name, ItemFactory<T> factory) {
		String translationKey = name.replace("_", ".");
		String namespaceId = CreeperShearingMod.MOD_ID + ":item/" + name;
		return new ItemBuilder(CreeperShearingMod.MOD_ID).build(factory.create(translationKey, namespaceId, ModConfig.itemID()));
	}

	@FunctionalInterface
	public interface ItemFactory<T extends Item> {
		T create(String key, String namespaceId, int id);
	}
}