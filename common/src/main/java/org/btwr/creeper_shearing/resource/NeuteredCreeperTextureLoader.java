package org.btwr.creeper_shearing.resource;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import org.btwr.creeper_shearing.Constants;
import org.btwr.creeper_shearing.api.NeuteredCreeperTextures;
import org.btwr.creeper_shearing.api.impl.NeuteredCreeperTextureEntry;

import java.util.Map;

public class NeuteredCreeperTextureLoader {

    private NeuteredCreeperTextureLoader() {}

    public static void load(Map<ResourceLocation, JsonElement> resources) {
        NeuteredCreeperTextures.clearDatapack();
        resources.forEach(NeuteredCreeperTextureLoader::reloadNeuteredTextures);
    }

    private static void reloadNeuteredTextures(ResourceLocation id, JsonElement json) {
        NeuteredCreeperTextureEntry.CODEC
                .parse(JsonOps.INSTANCE, json)
                .resultOrPartial(error -> Constants.LOG.error("[Creeper Shearing] Failed to load {}:{}", id, error))
                .ifPresent(NeuteredCreeperTextures::registerDatapack);
    }
}
