package org.btwr.creeper_shearing.data;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.ResourceLocation;
import org.btwr.creeper_shearing.Constants;

public class DataAttachmentsFabric {

    public static final AttachmentType<CreeperData> CREEPER_DATA = AttachmentRegistry.create(
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, CreeperData.ID),
            builder -> builder
                    .initializer(() -> new CreeperData(false, false))
                    .persistent(CreeperData.CODEC)
                    .syncWith(CreeperData.PACKET_CODEC, AttachmentSyncPredicate.all())
    );

    public static void initialize() {}

}
