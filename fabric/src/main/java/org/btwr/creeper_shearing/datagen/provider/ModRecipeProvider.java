package org.btwr.creeper_shearing.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CREEPER_OYSTER_BLOCK)
                .define('#', ModItems.CREEPER_OYSTERS)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(ModItems.CREEPER_OYSTERS), has(ModItems.CREEPER_OYSTERS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CREEPER_OYSTER_SLAB)
                .define('#', ModBlocks.CREEPER_OYSTER_BLOCK)
                .pattern("###")
                .unlockedBy(getHasName(ModBlocks.CREEPER_OYSTER_BLOCK), has(ModBlocks.CREEPER_OYSTER_BLOCK))
                .save(recipeOutput);
    }
}