package org.btwr.creeper_shearing.api.impl;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record NeuteredCreeperTextureEntry(ResourceLocation entity, ResourceLocation texture) {

    public static final Codec<NeuteredCreeperTextureEntry> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ResourceLocation.CODEC.fieldOf("entity").forGetter(NeuteredCreeperTextureEntry::entity),
                    ResourceLocation.CODEC.fieldOf("texture").forGetter(NeuteredCreeperTextureEntry::texture)
            ).apply(instance, NeuteredCreeperTextureEntry::new)
    );
}
