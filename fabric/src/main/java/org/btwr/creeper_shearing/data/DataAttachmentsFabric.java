package org.btwr.creeper_shearing.data;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class DataAttachmentsFabric {

    public static final AttachmentType<CreeperNeuterData> CREEPER_NEUTER_DATA = AttachmentRegistry.create(
            CreeperDataKeys.NEUTER_DATA.id(), builder -> builder
                    .initializer(() -> new CreeperNeuterData(false))
                    .persistent(CreeperNeuterData.CODEC)
                    .syncWith(CreeperNeuterData.PACKET_CODEC, AttachmentSyncPredicate.all())
    );

    public static final AttachmentType<CreeperExplodeData> CREEPER_EXPLODE_DATA = AttachmentRegistry.create(
            CreeperDataKeys.EXPLODE_DATA.id(), builder -> builder
                    .initializer(() -> new CreeperExplodeData(false))
                    .persistent(CreeperExplodeData.CODEC)
                    .syncWith(CreeperExplodeData.PACKET_CODEC, AttachmentSyncPredicate.all())
    );

    public static final AttachmentType<CreeperSubtypeData> CREEPER_SUBTYPE_DATA = AttachmentRegistry.create(
            CreeperDataKeys.SUBTYPE_DATA.id(), builder -> builder
                    .initializer(() -> new CreeperSubtypeData(""))
                    .persistent(CreeperSubtypeData.CODEC)
                    .syncWith(CreeperSubtypeData.PACKET_CODEC, AttachmentSyncPredicate.all())
    );

    public static void initialize() {}

}
