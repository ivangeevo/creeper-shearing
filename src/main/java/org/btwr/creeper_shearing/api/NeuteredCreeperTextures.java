package org.btwr.creeper_shearing.api;

import net.minecraft.core.entity.monster.MobCreeper;
import org.btwr.creeper_shearing.CreeperShearingMod;

import java.util.HashMap;
import java.util.Map;

public final class NeuteredCreeperTextures {
	public static final String DEFAULT = "/assets/" + CreeperShearingMod.MOD_ID + "/textures/entity/neutered_creeper.png";

	private static final Map<Class<? extends MobCreeper>, String> TEXTURES = new HashMap<>();

	private NeuteredCreeperTextures() {}

	public static void register(Class<? extends MobCreeper> creeper, String texturePath) {
		TEXTURES.put(creeper, texturePath);
	}

	public static String get(MobCreeper creeper) {
		for (Class<?> type = creeper.getClass(); MobCreeper.class.isAssignableFrom(type); type = type.getSuperclass()) {
			String texture = TEXTURES.get(type);
			if (texture != null) {
				return texture;
			}
		}
		return DEFAULT;
	}
}