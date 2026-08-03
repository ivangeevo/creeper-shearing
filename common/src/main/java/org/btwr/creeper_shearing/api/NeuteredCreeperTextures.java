package org.btwr.creeper_shearing.api;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import org.btwr.creeper_shearing.api.impl.NeuteredCreeperTextureEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility registry that allows external mods to provide custom textures
 * for neutered creeper-like entities.
 *
 * <p><strong>Usage:</strong></p>
 *
 * <pre>{@code
 * NeuteredCreeperTextures.register(
 *         MyEntities.MY_CUSTOM_CREEPER,
 *         Identifier.of("mymod", "textures/entity/my_neutered_creeper.png")
 * );
 * }</pre>
 */
public final class NeuteredCreeperTextures {

    /** Internal storage mapping entity id's to their neutered texture identifiers. */
    private static final Map<ResourceLocation, ResourceLocation> DATAPACK_TEXTURES = new HashMap<>();
    private static final Map<ResourceLocation, ResourceLocation> API_OVERRIDES = new HashMap<>();

    /**
     * Registers a custom neutered texture for the given entity type.
     * The provided texture will override the default neutered creeper texture
     * whenever a matching entity is rendered.
     *
     * @param entity     the creeper entity id
     * @param texture  the texture identifier to use when neutered
     */
    public static void register(ResourceLocation entity, ResourceLocation texture) {
        API_OVERRIDES.put(entity, texture);
    }

    public static void registerDatapack(NeuteredCreeperTextureEntry entry) {
        DATAPACK_TEXTURES.put(entry.entity(), entry.texture());
    }

    /**
     * Retrieves the registered neutered texture for the given entity type.
     *
     * @param type  the entity type being rendered
     * @return the registered texture identifier, or {@code null} if none exists
     */
    public static ResourceLocation get(EntityType<?> type) {
        ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(type);


        //
        // API override wins
        ResourceLocation override = API_OVERRIDES.get(id);
        if (override != null) {
            return override;
        }

        // Datapack fallback
        return DATAPACK_TEXTURES.get(id);
    }

    public static void clearDatapack() {
        DATAPACK_TEXTURES.clear();
    }

}