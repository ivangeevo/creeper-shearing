package org.btwr.creeper_shearing.api;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import org.btwr.creeper_shearing.Constants;

import java.util.HashMap;
import java.util.Map;

public class NeuteredDropsRegistry {

    public static final Map<EntityType<?>, ItemStack> NEUTERED_DROPS = new HashMap<>();

    /**
     * Registers a neutered drop for the target entity.
     * @param type the entity type
     * @param stack the item stack drop
     */
    public static void registerDrop(EntityType<?> type, ItemStack stack) {
        NEUTERED_DROPS.put(type, stack);
    }

    /**
     * Removes an already registered drop from the neutered drops map
     * @param type the entity type
     */
    public static void unregisterDrop(EntityType<?> type) {
        if (!NEUTERED_DROPS.containsKey(type)) {
            Constants.LOG.error("[Creeper Shearing] Failed to remove drop for entity type {}, because no drops are registered for it", type);
            return;
        }

        NEUTERED_DROPS.remove(type);
    }

    public ItemStack getDrop(EntityType<?> type) {
        return NEUTERED_DROPS.get(type);
    }

}