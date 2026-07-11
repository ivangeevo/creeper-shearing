package org.btwr.creeper_shearing.tag;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.btwr.creeper_shearing.Constants;

public class ModTags {

    public static class EntityTypes {
        /** Marks creepers as neuterable(can be sheared to neutralize).
         * <p>Developers should add their custom creeper types to this tag if they want them to be neuter-able
         **/
        public static final TagKey<EntityType<?>> NEUTERABLE_CREEPERS = createTag("neuterable_creepers");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
        }
    }

}