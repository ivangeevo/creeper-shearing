package org.btwr.creeper_shearing.util;

import net.minecraft.world.entity.Entity;

public interface EntityAttachmentBase<T extends Entity> {
    default boolean isDirty() {
        return false;
    }
    default void markDirty() {}
    default void tick(T entity) {}
}