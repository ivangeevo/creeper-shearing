package org.btwr.creeper_shearing.api;

import net.minecraft.core.entity.monster.MobCreeper;
import net.minecraft.core.item.ItemStack;
import org.btwr.creeper_shearing.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class NeuteredDropsRegistry {
	private static final Map<Class<? extends MobCreeper>, Supplier<ItemStack>> DROPS = new HashMap<>();

	private NeuteredDropsRegistry() {}

	public static void register(Class<? extends MobCreeper> creeper, Supplier<ItemStack> drop) {
		DROPS.put(creeper, drop);
	}

	public static void registerDefaults() {
		register(MobCreeper.class, () -> new ItemStack(ModItems.CREEPER_OYSTERS, 1));
	}

	@Nullable
	public static ItemStack get(MobCreeper creeper) {
		for (Class<?> type = creeper.getClass(); MobCreeper.class.isAssignableFrom(type); type = type.getSuperclass()) {
			Supplier<ItemStack> drop = DROPS.get(type);
			if (drop != null) {
				return drop.get();
			}
		}
		return null;
	}
}