package org.btwr.creeper_shearing.client;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import org.btwr.creeper_shearing.resource.NeoForgeNeuteredCreeperTextureReloadListener;

public class CreeperShearingModClient {

    public static void init(IEventBus eventBus) {
        eventBus.addListener(CreeperShearingModClient::registerReloadListeners);
    }

    public static void registerReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new NeoForgeNeuteredCreeperTextureReloadListener());
    }
}