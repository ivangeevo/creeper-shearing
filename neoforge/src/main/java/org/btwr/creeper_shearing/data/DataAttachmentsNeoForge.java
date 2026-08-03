package org.btwr.creeper_shearing.data;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.btwr.creeper_shearing.Constants;

public class DataAttachmentsNeoForge {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(
            NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Constants.MOD_ID
    );

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<CreeperNeuterData>> CREEPER_NEUTER_DATA =
            ATTACHMENT_TYPES.register(
                    CreeperDataKeys.NEUTER_DATA.id().getPath(),
                    () -> AttachmentType.builder(() -> new CreeperNeuterData(false))
                            .serialize(CreeperNeuterData.CODEC)
                            .sync(CreeperNeuterData.PACKET_CODEC)
                            .build()
    );

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<CreeperExplodeData>> CREEPER_EXPLODE_DATA =
            ATTACHMENT_TYPES.register(
                    CreeperDataKeys.EXPLODE_DATA.id().getPath(),
                    () -> AttachmentType.builder(() -> new CreeperExplodeData(false))
                            .serialize(CreeperExplodeData.CODEC)
                            .sync(CreeperExplodeData.PACKET_CODEC)
                            .build()
    );

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<CreeperSubtypeData>> CREEPER_SUBTYPE_DATA =
            ATTACHMENT_TYPES.register(
                    CreeperDataKeys.SUBTYPE_DATA.id().getPath(),
                    () -> AttachmentType.builder(() -> new CreeperSubtypeData(""))
                            .serialize(CreeperSubtypeData.CODEC)
                            .sync(CreeperSubtypeData.PACKET_CODEC)
                            .build()
    );

    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}
