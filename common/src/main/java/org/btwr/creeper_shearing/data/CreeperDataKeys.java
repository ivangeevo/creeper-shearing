package org.btwr.creeper_shearing.data;

import net.minecraft.resources.ResourceLocation;
import org.btwr.creeper_shearing.Constants;
import org.btwr.creeper_shearing.data.impl.DataKey;

import java.util.function.Supplier;

public class CreeperDataKeys {
    public static final DataKey<CreeperNeuterData> NEUTER_DATA = createNew(
            "creeper_neuter_data", () -> new CreeperNeuterData(false)
    );

    public static final DataKey<CreeperExplodeData> EXPLODE_DATA = createNew(
            "creeper_explode_data", () -> new CreeperExplodeData(false)
    );

    public static final DataKey<CreeperSubtypeData> SUBTYPE_DATA = createNew(
            "creeper_subtype_data", () -> new CreeperSubtypeData("")
    );

    private static <T> DataKey<T> createNew(String id, Supplier<T> factory) {
        return new DataKey<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, id), factory);
    }
}