package org.btwr.creeper_shearing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import org.btwr.creeper_shearing.api.NeuteredDropsRegistry;
import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.data.DataAttachmentsFabric;
import org.btwr.creeper_shearing.item.ModItems;
import org.btwr.creeper_shearing.util.CreeperShearLogic;

public class CreeperShearingMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.init();

        ModBlocks.initialize();
        ModItems.initialize();
        this.registerItemGroups();
        DataAttachmentsFabric.initialize();
        UseEntityCallback.EVENT.register(CreeperShearLogic::tryShear);

        // Register creeper oyster drops
        NeuteredDropsRegistry.registerDrop(EntityType.CREEPER, new ItemStack(ModItems.CREEPER_OYSTERS, 1));
    }

    private void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(ModItems.CREEPER_OYSTERS);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.accept(ModBlocks.CREEPER_OYSTER_BLOCK);
            entries.addAfter(ModBlocks.CREEPER_OYSTER_BLOCK, ModBlocks.CREEPER_OYSTER_SLAB);
        });
    }

}
