package org.btwr.creeper_shearing;

import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.item.ModItems;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryRegistry;

public final class ModCreativeContents {
	private static final CreativeInventoryRegistry REG = CreativeInventoryRegistry.INSTANCE;

	private ModCreativeContents() {}

	public static void init() {
		REG.register(ModItems.CREEPER_OYSTERS, inCategory(CreativeInventoryCategory.ORGANIC));
		REG.register(ModBlocks.CREEPER_OYSTER_BLOCK, inCategory(CreativeInventoryCategory.ORGANIC));
		REG.register(ModBlocks.CREEPER_OYSTER_SLAB, inCategory(CreativeInventoryCategory.ORGANIC));
	}

	private static CreativeInventoryPlacement.Category inCategory(CreativeInventoryCategory category) {
		return new CreativeInventoryPlacement.Category(category);
	}
}