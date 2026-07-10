package org.btwr.creeper_shearing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import org.btwr.creeper_shearing.data.DataAttachmentsFabric;
import org.btwr.creeper_shearing.item.ModItems;
import org.btwr.creeper_shearing.util.CreeperShearLogic;

public class CreeperShearingMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.init();

        ModItems.initialize();
        DataAttachmentsFabric.initialize();
        UseEntityCallback.EVENT.register(CreeperShearLogic::tryShear);

        // Config
        /**
        ModConfig.init(FabricLoader.getInstance().getConfigDir());

        // Reload config every time a world is loaded
        ServerLifecycleEvents.SERVER_STARTING.register(server -> ModConfig.load());
         **/
    }
}
