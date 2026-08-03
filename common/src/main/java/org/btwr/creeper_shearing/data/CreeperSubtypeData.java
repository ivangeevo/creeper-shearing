package org.btwr.creeper_shearing.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public class CreeperSubtypeData {

    public static Codec<CreeperSubtypeData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.optionalFieldOf("creeper_subtype").forGetter(CreeperSubtypeData::getSubType)
            ).apply(instance, (s) -> new CreeperSubtypeData(s.orElse(null)))
    );

    public static StreamCodec<ByteBuf, CreeperSubtypeData> PACKET_CODEC = ByteBufCodecs.fromCodec(CODEC);

    private String subtype;

    public CreeperSubtypeData(String subtype) {
        this.subtype = subtype;
    }

    public Optional<String> getSubType() {
        return Optional.ofNullable(subtype);
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}