package org.btwr.creeper_shearing.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.item.ModItems;

import static net.minecraft.data.models.BlockModelGenerators.createSlab;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        generator.createTrivialCube(ModBlocks.CREEPER_OYSTER_BLOCK);
        this.registerSimpleSlab(generator, ModBlocks.CREEPER_OYSTER_BLOCK, ModBlocks.CREEPER_OYSTER_SLAB);
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(ModItems.CREEPER_OYSTERS, ModelTemplates.FLAT_ITEM);
    }

    private void registerSimpleSlab(BlockModelGenerators gen, Block mainBlock, Block slabBlock) {
        ResourceLocation id = ModelLocationUtils.getModelLocation(mainBlock);
        TexturedModel texturedModel = TexturedModel.CUBE.get(mainBlock);
        ResourceLocation id2 = ModelTemplates.SLAB_BOTTOM.create(slabBlock, texturedModel.getMapping(), gen.modelOutput);
        ResourceLocation id3 = ModelTemplates.SLAB_TOP.create(slabBlock, texturedModel.getMapping(), gen.modelOutput);
        gen.blockStateOutput.accept(createSlab(slabBlock, id2, id3, id));
    }

}