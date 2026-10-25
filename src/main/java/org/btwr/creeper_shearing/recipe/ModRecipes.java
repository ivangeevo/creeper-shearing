package org.btwr.creeper_shearing.recipe;

import net.minecraft.core.item.ItemStack;
import org.btwr.creeper_shearing.CreeperShearingMod;
import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.item.ModItems;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShapeless;

public class ModRecipes {
	public static void initNamespaces() {
		RecipeBuilder.initNameSpace(CreeperShearingMod.MOD_ID);
	}

	public static void onRecipesReady() {
		RecipeBuilderShaped oysterBlock = new RecipeBuilderShaped(CreeperShearingMod.MOD_ID, "###", "###", "###");
		oysterBlock
			.addInput('#', ModItems.CREEPER_OYSTERS)
			.create("creeper_oyster_block", new ItemStack(ModBlocks.CREEPER_OYSTER_BLOCK, 1));

		RecipeBuilderShaped oysterSlab = new RecipeBuilderShaped(CreeperShearingMod.MOD_ID, "###");
		oysterSlab
			.addInput('#', ModBlocks.CREEPER_OYSTER_BLOCK)
			.create("creeper_oyster_slab", new ItemStack(ModBlocks.CREEPER_OYSTER_SLAB, 6));

		RecipeBuilderShapeless oystersFromBlock = new RecipeBuilderShapeless(CreeperShearingMod.MOD_ID);
		oystersFromBlock
			.addInput(ModBlocks.CREEPER_OYSTER_BLOCK)
			.create("creeper_oysters_from_block", new ItemStack(ModItems.CREEPER_OYSTERS, 9));

		RecipeBuilderShapeless oysterBlockFromSlabs = new RecipeBuilderShapeless(CreeperShearingMod.MOD_ID);
		oysterBlockFromSlabs
			.addInput(ModBlocks.CREEPER_OYSTER_SLAB)
			.addInput(ModBlocks.CREEPER_OYSTER_SLAB)
			.create("creeper_oyster_block_from_slabs", new ItemStack(ModBlocks.CREEPER_OYSTER_BLOCK, 1));
	}
}