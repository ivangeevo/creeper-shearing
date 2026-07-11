package org.btwr.creeper_shearing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
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
    }

    private void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(ModItems.CREEPER_OYSTERS);
        });
    }

}
