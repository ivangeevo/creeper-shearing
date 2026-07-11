package org.btwr.creeper_shearing.data;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.btwr.creeper_shearing.Constants;

import java.util.function.Supplier;

public class DataAttachmentsNeoForge {

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(
            NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Constants.MOD_ID
    );

    public static final Supplier<AttachmentType<CreeperData>> CREEPER_DATA = ATTACHMENT_TYPES.register(
            CreeperData.ID,
            () -> AttachmentType.builder(() -> new CreeperData(false, false))
                    .serialize(CreeperData.CODEC)
                    .sync(CreeperData.PACKET_CODEC)
                    .build()
    );

    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}
