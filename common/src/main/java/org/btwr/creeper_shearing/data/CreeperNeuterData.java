package org.btwr.creeper_shearing.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.monster.Monster;
import org.btwr.creeper_shearing.util.UpdateRequiringData;

public class CreeperNeuterData extends UpdateRequiringData<Monster> {

    public static Codec<CreeperNeuterData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("is_neutered").forGetter(CreeperNeuterData::isNeutered)
            ).apply(instance, CreeperNeuterData::new)
    );

    public static StreamCodec<ByteBuf, CreeperNeuterData> PACKET_CODEC = ByteBufCodecs.fromCodec(CODEC);

    private boolean isNeutered;

    public CreeperNeuterData(boolean isNeutered) {
        this.isNeutered = isNeutered;
    }

    public boolean isNeutered() {
        return isNeutered;
    }

    public boolean canNeuter() {
        return !isNeutered;
    }

    public void setNeutered(boolean value) {
        isNeutered = value;
    }

}