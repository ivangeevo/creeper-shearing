package org.btwr.creeper_shearing;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.btwr.creeper_shearing.client.CreeperShearingModClient;
import org.btwr.creeper_shearing.data.DataAttachmentsNeoForge;
import org.btwr.creeper_shearing.item.ModItems;
import org.btwr.creeper_shearing.platform.NeoForgeSoundRegistration;
import org.btwr.creeper_shearing.util.CreeperShearLogic;

import static net.minecraft.world.item.CreativeModeTabs.FOOD_AND_DRINKS;
import static net.minecraft.world.item.CreativeModeTabs.INGREDIENTS;


@Mod(Constants.MOD_ID)
public class CreeperShearingMod {

    public CreeperShearingMod(IEventBus eventBus) {
        CommonClass.init();
        DataAttachmentsNeoForge.register(eventBus);

        // Register this class's @SubscribeEvent methods to the GAME event bus
        NeoForge.EVENT_BUS.register(this);
        NeoForgeSoundRegistration.SOUNDS.register(eventBus);

        eventBus.addListener(this::onRegister);
        eventBus.addListener(this::registerCreativeTabEntries);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            CreeperShearingModClient.init(eventBus);
        }
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.ITEM) {
            ModItems.initialize();
        }
    }

    private void registerCreativeTabEntries(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == FOOD_AND_DRINKS) {
            event.accept(ModItems.CREEPER_OYSTERS);
        }

        if (event.getTabKey() == INGREDIENTS) {
            event.accept(ModItems.CREEPER_OYSTERS);
        }
    }

    // Create an event for interacting with creepers so they can be sheared
    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        var result = CreeperShearLogic.tryShear(
                event.getEntity(),
                event.getLevel(),
                event.getHand(),
                event.getTarget(),
                new EntityHitResult(event.getTarget())
        );

        if (result == InteractionResult.SUCCESS) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }

}