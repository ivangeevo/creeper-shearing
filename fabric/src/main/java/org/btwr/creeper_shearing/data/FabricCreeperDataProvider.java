package org.btwr.creeper_shearing.data;

import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;
import org.btwr.creeper_shearing.data.impl.CreeperDataProvider;
import org.btwr.creeper_shearing.data.impl.DataKey;

import java.util.Map;

@SuppressWarnings("unchecked")
public class FabricCreeperDataProvider implements CreeperDataProvider {

    private static final Map<ResourceLocation, AttachmentType<?>> BY_ID = Map.of(
            DataAttachmentsFabric.CREEPER_NEUTER_DATA.identifier(), DataAttachmentsFabric.CREEPER_NEUTER_DATA,
            DataAttachmentsFabric.CREEPER_EXPLODE_DATA.identifier(), DataAttachmentsFabric.CREEPER_EXPLODE_DATA,
            DataAttachmentsFabric.CREEPER_SUBTYPE_DATA.identifier(), DataAttachmentsFabric.CREEPER_SUBTYPE_DATA
    );

    private <T> AttachmentType<T> resolve(DataKey<T> key) {
        AttachmentType<T> type = (AttachmentType<T>) BY_ID.get(key.id());
        if (type == null) {
            throw new IllegalArgumentException("No Fabric attachment registered for: " + key.id());
        }

        return type;
    }

    @Override
    public <T> T get(Monster creeper, DataKey<T> key) {
        return creeper.getAttached(resolve(key));
    }

    @Override
    public <T> T getOrCreate(Monster creeper, DataKey<T> key) {
        return creeper.getAttachedOrCreate(resolve(key));
    }

    @Override
    public <T> void set(Monster creeper, DataKey<T> key, T data) {
        creeper.setAttached(resolve(key), data);
    }
}