package org.btwr.creeper_shearing.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.btwr.creeper_shearing.Constants;

import java.util.Map;

public class FabricNeuteredCreeperTextureReloadListener
        extends SimpleJsonResourceReloadListener
        implements IdentifiableResourceReloadListener
{

    private static final Gson GSON = new Gson();

    public FabricNeuteredCreeperTextureReloadListener() {
        super(GSON, "neutered_creeper_textures");
    }

    @Override
    public ResourceLocation getFabricId() {
        return ResourceLocation.fromNamespaceAndPath(
                Constants.MOD_ID,
                "neutered_creeper_textures"
        );
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler) {
        NeuteredCreeperTextureLoader.load(object);
    }
}
