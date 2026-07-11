package org.btwr.creeper_shearing.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.btwr.creeper_shearing.data.CreeperData;
import org.btwr.creeper_shearing.data.CreeperDataAttachment;
import org.btwr.creeper_shearing.entity.ai.goal.CreeperSwellBehavior;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public abstract class CreeperMixin extends Monster {

    @Shadow private int maxSwell;
    @Shadow private int oldSwell;
    @Shadow private int swell;
    @Shadow public abstract boolean isIgnited();
    @Shadow public abstract void setSwellDir(int fuseSpeed);
    @Shadow public abstract int getSwellDir();
    @Shadow protected abstract void explodeCreeper();

    protected CreeperMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(EntityType<?> entityType, Level level, CallbackInfo ci) {
        CreeperDataAttachment.getOrCreate((Creeper)(Object)this);
    }

    @ModifyArg(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V", ordinal = 1), index = 1)
    private Goal injected(Goal goal) {
        return new CreeperSwellBehavior((Creeper)(Object)this);
    }

    // Copying, modifying and cancelling the original tick logic with our custom conditions.
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void injectedTick(CallbackInfo ci) {
        this.creeper_shearing$setCustomTickLogic();
        ci.cancel();
    }

    // Add a drop on death with a chance, instead of modifying the loot table.
    @Inject(method = "dropCustomDeathLoot", at = @At("TAIL"))
    private void onDropEquipment(ServerLevel level, DamageSource damageSource, boolean recentlyHit, CallbackInfo ci) {
        if (!creeper_shearing$isNeutered() && random.nextInt(3) == 0) {
            this.spawnAtLocation(Items.EMERALD, 1);
        }
    }

    // Creeper sometimes makes a hiss sound if neutered - he's crying :(
    @Override
    public void playAmbientSound() {
        if (creeper_shearing$isNeutered()) {
            this.playSound(this.getAmbientSound(), 0.25F, this.getVoicePitch() + 0.25F);
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CREEPER_HURT;
    }

    @Unique
    private void creeper_shearing$setCustomTickLogic() {
        if (this.isAlive()) {
            this.oldSwell = this.swell;
            if (this.isIgnited()) {
                this.setSwellDir(1);
            }

            if (this.getSwellDir() > 0 && this.swell == 0 && !creeper_shearing$isNeutered()) {
                this.playSound(SoundEvents.CREEPER_PRIMED, 1.0f, 0.5f);
                this.gameEvent(GameEvent.PRIME_FUSE);
            }

            this.swell += this.getSwellDir();

            if (this.swell < 0) {
                this.swell = 0;
            }

            // Check if the creeper is not neutered
            if (!creeper_shearing$isNeutered()) {
                if (this.swell >= this.maxSwell) {
                    this.swell = this.maxSwell;
                    this.explodeCreeper();
                }
            } else {
                // Reset fuse time when neutered
                this.swell = 0;
            }
        }

        Creeper self = (Creeper)(Object)this;
        CreeperData creeperData = CreeperDataAttachment.getOrCreate(self);

        if (!self.level().isClientSide) {
            creeperData.tick(self);

            if (creeperData.isDirty()) {
                CreeperDataAttachment.set(self, creeperData);
            }
        }

        super.tick();
    }

    @Unique
    private boolean creeper_shearing$isNeutered() {
        var data = CreeperDataAttachment.get((Creeper)(Object)this);
        return data != null && data.isNeutered();
    }

}