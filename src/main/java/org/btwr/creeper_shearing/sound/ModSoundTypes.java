package org.btwr.creeper_shearing.sound;

import net.minecraft.core.sound.SoundTypes;
import org.btwr.creeper_shearing.CreeperShearingMod;

public class ModSoundTypes {
	public static final String CREEPER_SHEARED = create("creeper.sheared");
	public static final String CREEPER_SHEARED_LAYER = create("creeper.sheared_layer");

	public static void init() {
		SoundTypes.loadSoundsJson(CreeperShearingMod.MOD_ID);
	}

	private static String create(String key) {
		return CreeperShearingMod.MOD_ID + ":" + key;
	}
}