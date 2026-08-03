package org.btwr.creeper_shearing.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.monster.Monster;
import org.btwr.creeper_shearing.util.UpdateRequiringData;

public class CreeperExplodeData extends UpdateRequiringData<Monster> {

    public static Codec<CreeperExplodeData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.BOOL.fieldOf("is_determined_to_explode").forGetter(CreeperExplodeData::getIsDeterminedToExplode)
            ).apply(instance, CreeperExplodeData::new)
    );

    public static StreamCodec<ByteBuf, CreeperExplodeData> PACKET_CODEC = ByteBufCodecs.fromCodec(CODEC);

    private boolean isDeterminedToExplode;

    public CreeperExplodeData(boolean isDeterminedToExplode) {
        this.isDeterminedToExplode = isDeterminedToExplode;
    }

    public boolean getIsDeterminedToExplode() {
        return isDeterminedToExplode;
    }

    public void setDeterminedToExplode(boolean value) {
        isDeterminedToExplode = value;
    }

}