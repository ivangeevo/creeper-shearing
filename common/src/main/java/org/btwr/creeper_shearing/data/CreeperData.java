package org.btwr.creeper_shearing.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.monster.Creeper;
import org.btwr.creeper_shearing.util.UpdateRequiringData;

public class CreeperData extends UpdateRequiringData<Creeper> {

    public static final String ID = "creeper_data";

    public static Codec<CreeperData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("is_neutered").forGetter(CreeperData::isNeutered),
                    Codec.BOOL.fieldOf("is_determined_to_explode").forGetter(CreeperData::getIsDeterminedToExplode)
            ).apply(instance, CreeperData::new)
    );

    public static StreamCodec<ByteBuf, CreeperData> PACKET_CODEC = ByteBufCodecs.fromCodec(CODEC);

    private boolean isNeutered;
    private boolean isDeterminedToExplode;

    public CreeperData(boolean isNeutered, boolean isDeterminedToExplode) {
        this.isNeutered = isNeutered;
        this.isDeterminedToExplode = isDeterminedToExplode;
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

    public boolean getIsDeterminedToExplode() {
        return isDeterminedToExplode;
    }

    public void setDeterminedToExplode(boolean value) {
        isDeterminedToExplode = value;
    }

}