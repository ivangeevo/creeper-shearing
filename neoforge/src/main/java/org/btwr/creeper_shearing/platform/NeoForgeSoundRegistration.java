package org.btwr.creeper_shearing.platform;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.btwr.creeper_shearing.Constants;
import org.btwr.creeper_shearing.platform.services.SoundRegistration;

public class NeoForgeSoundRegistration implements SoundRegistration {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Constants.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> CREEPER_SHEARED =
            SOUNDS.register(
                    "creeper_sheared",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "creeper_sheared")
                    )
            );

    public static final DeferredHolder<SoundEvent, SoundEvent> CREEPER_SHEARED_LAYER =
            SOUNDS.register(
                    "creeper_sheared_layer",
                    () -> SoundEvent.createVariableRangeEvent(
                            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "creeper_sheared_layer")
                    )
            );

    @Override
    public SoundEvent creeperSheared() {
        return CREEPER_SHEARED.get();
    }

    @Override
    public SoundEvent creeperShearedLayer() {
        return CREEPER_SHEARED_LAYER.get();
    }
}
