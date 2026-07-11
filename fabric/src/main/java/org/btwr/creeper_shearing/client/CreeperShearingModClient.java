package org.btwr.creeper_shearing.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.server.packs.PackType;
import org.btwr.creeper_shearing.block.ModBlocks;
import org.btwr.creeper_shearing.resource.FabricNeuteredCreeperTextureReloadListener;

public class CreeperShearingModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES)
                .registerReloadListener(new FabricNeuteredCreeperTextureReloadListener());
    }

}