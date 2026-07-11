package org.btwr.creeper_shearing.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.btwr.creeper_shearing.Constants;

public class ModBlocks {

    public static Block CREEPER_OYSTER_BLOCK;
    public static Block CREEPER_OYSTER_SLAB;

    public static void initialize() {
        Constants.LOG.debug("Registering ModBlocks for " + Constants.MOD_ID);

        CREEPER_OYSTER_BLOCK = registerBlock("creeper_oyster_block", new Block(BlockBehaviour.Properties.of()
                .strength(0.6f)
                .sound(SoundType.HONEY_BLOCK)
                .requiresCorrectToolForDrops())
        );

        CREEPER_OYSTER_SLAB = registerBlock("creeper_oyster_slab", new SlabBlock(BlockBehaviour.Properties.of()
                .strength(0.6f)
                .sound(SoundType.HONEY_BLOCK)
                .requiresCorrectToolForDrops())
        );
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
    }

    private static Block registerWithoutItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name),
                new BlockItem(block, new Item.Properties())
        );
    }

}