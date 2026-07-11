package org.btwr.creeper_shearing.util;

import net.minecraft.world.entity.Entity;

public class UpdateRequiringData<T extends Entity> implements EntityAttachmentBase<T> {
    private boolean updated;

    public void markDirty() {
        this.updated = true;
    }

    public boolean isDirty() {
        if (!this.updated) {
            return false;
        } else {
            this.updated = false;
            return true;
        }
    }

}
