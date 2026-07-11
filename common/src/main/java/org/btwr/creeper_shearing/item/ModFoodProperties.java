package org.btwr.creeper_shearing.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties CREEPER_OYSTERS =
            new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationModifier(0.10f)
                    .effect(new MobEffectInstance(MobEffects.POISON, 100,0),1f)
                    .build();

}