package org.btwr.creeper_shearing.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import java.util.Map;

public class NeoForgeNeuteredCreeperTextureReloadListener extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();

    public NeoForgeNeuteredCreeperTextureReloadListener() {
        super(GSON, "neutered_creeper_textures");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        NeuteredCreeperTextureLoader.load(object);
    }
}
