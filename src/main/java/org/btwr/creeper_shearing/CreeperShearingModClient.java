package org.btwr.creeper_shearing;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.generic.BlockModelGeneric;
import net.minecraft.client.render.block.model.generic.BlockModelGenericSlab;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.item.ModItems;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.util.dependency.Key;

import static net.minecraft.client.render.block.model.BlockModelDispatcher.loadDataModel;

public class CreeperShearingModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(CreeperShearingMod.MOD_ID), CreeperShearingModClient::initBlockModels);
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(CreeperShearingMod.MOD_ID), CreeperShearingModClient::initItemModels);
	}

	public static void initItemModels(ItemModelDispatcher dispatcher) {
		dispatcher.addDispatch(new ItemModelStandard(ModItems.CREEPER_OYSTERS).setIcon(item("creeper_oysters")));
	}

	public static void initBlockModels(BlockModelDispatcher dispatcher) {
		dispatcher.addDispatch(new BlockModelGeneric<>(ModBlocks.CREEPER_OYSTER_BLOCK, loadDataModel(block("creeper_oyster_block"))));
		dispatcher.addDispatch(new BlockModelGenericSlab<>(ModBlocks.CREEPER_OYSTER_SLAB,
				loadDataModel(block("creeper_oyster_slab_lower")),
				loadDataModel(block("creeper_oyster_slab_upper")),
				loadDataModel(block("creeper_oyster_slab_full"))
				)
		);
	}

	private static String block(String name) {
		return CreeperShearingMod.MOD_ID + ":block/" + name;
	}
	private static String item(String name) {
		return CreeperShearingMod.MOD_ID + ":item/" + name;
	}
}