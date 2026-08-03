package org.btwr.creeper_shearing.mixin.client;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.btwr.creeper_shearing.api.NeuteredDropsRegistry;
import org.btwr.creeper_shearing.data.CreeperDataAttachment;
import org.btwr.creeper_shearing.data.CreeperDataKeys;
import org.btwr.creeper_shearing.data.CreeperNeuterData;
import org.btwr.creeper_shearing.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "dropCustomDeathLoot", at = @At("TAIL"))
    private void dropCreeperNeuterLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;

        if (self.getType().is(ModTags.EntityTypes.NEUTERABLE_CREEPERS)) {
            CreeperNeuterData neuterData = CreeperDataAttachment.get((Monster)self, CreeperDataKeys.NEUTER_DATA);

            NeuteredDropsRegistry registry = new NeuteredDropsRegistry();

            if (!neuterData.isNeutered() && random.nextInt(3) == 0) {
                ItemStack stack = registry.getDrop(self.getType());

                this.spawnAtLocation(stack);
            }
        }
    }
}
