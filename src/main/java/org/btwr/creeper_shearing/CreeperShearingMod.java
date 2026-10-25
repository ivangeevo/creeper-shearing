package org.btwr.creeper_shearing;

import net.fabricmc.api.ModInitializer;
import org.btwr.creeper_shearing.api.NeuteredDropsRegistry;
import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.config.ModConfig;
import org.btwr.creeper_shearing.item.ModItems;
import org.btwr.creeper_shearing.recipe.ModRecipes;
import org.btwr.creeper_shearing.sound.ModSoundTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.util.dependency.Key;

public class CreeperShearingMod implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("creeper_shearing", true);
	public static final Logger LOGGER = LoggerFactory.getLogger("Creeper Shearing");

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), ModConfig::init);
		CommonEvents.AFTER_BLOCK_INIT.listen(Key.of(MOD_ID), ModBlocks::afterBlockInit);
		CommonEvents.AFTER_ITEM_INIT.listen(Key.of(MOD_ID), ModItems::afterItemInit);
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), ModRecipes::initNamespaces);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), ModRecipes::onRecipesReady);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID), this::afterGameStart);

		LOGGER.info("Creeper Shearing initialized.");
	}

	public void afterGameStart() {
		ModCreativeContents.init();
		ModSoundTypes.init();
		NeuteredDropsRegistry.registerDefaults();
	}
}