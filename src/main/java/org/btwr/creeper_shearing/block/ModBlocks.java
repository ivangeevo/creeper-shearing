package org.btwr.creeper_shearing.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicSlab;
import net.minecraft.core.block.BlockLogicSupplier;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.sound.BlockSounds;
import org.btwr.creeper_shearing.CreeperShearingMod;
import org.btwr.creeper_shearing.config.ModConfig;

public class ModBlocks {
	public static Block<BlockLogic> CREEPER_OYSTER_BLOCK;
	public static Block<BlockLogicSlab> CREEPER_OYSTER_SLAB;

	public static void afterBlockInit() {
		CREEPER_OYSTER_BLOCK = registerBlock("creeper_oyster_block", block -> new BlockLogic(
			block
				.withSound(BlockSounds.CLOTH)
				.withHardness(0.6F),
			Materials.CLAY
		));
		CREEPER_OYSTER_SLAB = registerBlock("creeper_oyster_slab", block -> new BlockLogicSlab(
			block
				.withSound(BlockSounds.CLOTH)
				.withHardness(0.6F),
			CREEPER_OYSTER_BLOCK
		));
	}

	private static <T extends BlockLogic> Block<T> registerBlock(String name, BlockLogicSupplier<T> logicSupplier) {
		String namespaceId = CreeperShearingMod.MOD_ID + ":block/" + name;
		String key = CreeperShearingMod.MOD_ID + "." + name.replace("_", ".");
		return Blocks.register(key, namespaceId, ModConfig.blockID(), logicSupplier);
	}
}