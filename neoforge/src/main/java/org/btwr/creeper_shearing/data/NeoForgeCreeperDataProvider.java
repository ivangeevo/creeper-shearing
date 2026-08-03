package org.btwr.creeper_shearing.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;
import net.neoforged.neoforge.attachment.AttachmentType;
import org.btwr.creeper_shearing.data.impl.CreeperDataProvider;
import org.btwr.creeper_shearing.data.impl.DataKey;

import java.util.Map;

@SuppressWarnings("unchecked")
public class NeoForgeCreeperDataProvider implements CreeperDataProvider {

    private static final Map<ResourceLocation, AttachmentType<?>> BY_ID = Map.of(
            DataAttachmentsNeoForge.CREEPER_NEUTER_DATA.getId(), DataAttachmentsNeoForge.CREEPER_NEUTER_DATA.get(),
            DataAttachmentsNeoForge.CREEPER_EXPLODE_DATA.getId(), DataAttachmentsNeoForge.CREEPER_EXPLODE_DATA.get(),
            DataAttachmentsNeoForge.CREEPER_SUBTYPE_DATA.getId(), DataAttachmentsNeoForge.CREEPER_SUBTYPE_DATA.get()
    );

    private <T> AttachmentType<T> resolve(DataKey<T> key) {
        AttachmentType<T> type = (AttachmentType<T>) BY_ID.get(key.id());
        if (type == null) {
            throw new IllegalArgumentException("No NeoForge attachment registered for: " + key.id());
        }

        return type;
    }

    @Override
    public <T> T get(Monster creeper, DataKey<T> key) {
        return creeper.getExistingDataOrNull(resolve(key));
    }

    @Override
    public <T> T getOrCreate(Monster creeper, DataKey<T> key) {
        return creeper.getData(resolve(key));
    }

    @Override
    public <T> void set(Monster creeper, DataKey<T> key, T data) {
        creeper.setData(resolve(key), data);
    }
}