package org.btwr.creeper_shearing.sound;

import net.minecraft.sounds.SoundEvent;
import org.btwr.creeper_shearing.platform.Services;

public class ModSoundEvents {

    public static SoundEvent CREEPER_SHEARED() {
        return Services.SOUND_EVENTS.creeperSheared();
    }

    public static SoundEvent CREEPER_SHEARED_LAYER() {
        return Services.SOUND_EVENTS.creeperShearedLayer();
    }
}
