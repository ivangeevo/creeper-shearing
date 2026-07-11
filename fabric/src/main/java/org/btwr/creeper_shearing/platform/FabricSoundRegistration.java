package org.btwr.creeper_shearing.platform;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.btwr.creeper_shearing.Constants;
import org.btwr.creeper_shearing.platform.services.SoundRegistration;

public class FabricSoundRegistration implements SoundRegistration {

    public static final SoundEvent CREEPER_SHEARED = register("creeper_sheared");
    public static final SoundEvent CREEPER_SHEARED_LAYER = register("creeper_sheared_layer");

    private static SoundEvent register(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    @Override
    public SoundEvent creeperSheared() {
        return CREEPER_SHEARED;
    }

    @Override
    public SoundEvent creeperShearedLayer() {
        return CREEPER_SHEARED_LAYER;
    }
}
