package org.btwr.creeper_shearing;


import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.btwr.creeper_shearing.data.DataAttachmentsNeoForge;
import org.btwr.creeper_shearing.platform.NeoForgeSoundRegistration;
import org.btwr.creeper_shearing.util.CreeperShearLogic;

@Mod(Constants.MOD_ID)
public class CreeperShearingMod {

    public CreeperShearingMod(IEventBus eventBus) {
        CommonClass.init();
        DataAttachmentsNeoForge.register(eventBus);

        // Register this class's @SubscribeEvent methods to the GAME event bus
        NeoForge.EVENT_BUS.register(this);
        NeoForgeSoundRegistration.SOUNDS.register(eventBus);
    }

    // Create an event for interacting with creepers so they can be sheared
    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Constants.LOG.info("Interacted with {}", event.getTarget());
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