package org.btwr.creeper_shearing.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.btwr.creeper_shearing.api.NeuteredDropsRegistry;
import org.btwr.creeper_shearing.data.CreeperDataKeys;
import org.btwr.creeper_shearing.data.CreeperNeuterData;
import org.btwr.creeper_shearing.data.CreeperDataAttachment;
import org.btwr.creeper_shearing.item.ModItems;
import org.btwr.creeper_shearing.sound.ModSoundEvents;
import org.btwr.creeper_shearing.tag.ModTags;

public class CreeperShearLogic {

    public static InteractionResult tryShear(
            Player player, Level level, InteractionHand hand, Entity entity, EntityHitResult hitResult)
    {
        if (entity instanceof Monster creeper) {
            CreeperNeuterData neuterData = CreeperDataAttachment.getOrCreate(creeper, CreeperDataKeys.NEUTER_DATA);

            if (neuterData == null) return InteractionResult.PASS;

            if (neuterData.canNeuter() && entity.getType().is(ModTags.EntityTypes.NEUTERABLE_CREEPERS)) {
                ItemStack handStack = player.getItemInHand(hand);

                if (handStack.getItem() instanceof ShearsItem && !neuterData.isNeutered()) {
                    float pitch2 = (player.level().random.nextFloat() - player.level().random.nextFloat()) * 0.1F + 0.7F;
                    player.level().playSound(null, player.blockPosition(), ModSoundEvents.CREEPER_SHEARED(),
                            SoundSource.NEUTRAL, 1.0F, 1.0F
                    );
                    player.level().playSound(null, player.blockPosition(), ModSoundEvents.CREEPER_SHEARED_LAYER(),
                            SoundSource.HOSTILE, 1.0F, pitch2
                    );

                    if(!creeper.level().isClientSide) {
                        neuterData.setNeutered(true);
                        neuterData.markDirty();

                        ParticleOptions particle = ParticleTypes.ITEM_SNOWBALL;
                        for (int i = 0; i < 50; i++) {
                            double particleX = creeper.getX() + level.random.nextDouble() - 0.5D;
                            double particleY = creeper.getY() - 0.45D + level.random.nextDouble() * 0.5D;  // Adjusted the Y coordinate
                            double particleZ = creeper.getZ() + level.random.nextDouble() - 0.5D;

                            double particleVelX = (level.random.nextDouble() - 0.5D) * 0.5D;
                            double particleVelY = level.random.nextDouble() * 0.25D;
                            double particleVelZ = (level.random.nextDouble() - 0.5D) * 0.5D;

                            ((ServerLevel) level).sendParticles(particle, particleX, particleY, particleZ, 1, particleVelX, particleVelY, particleVelZ, 0.0);
                        }

                        handStack.hurtAndBreak(10, player, LivingEntity.getSlotForHand(hand));
                        ItemStack dropStack = NeuteredDropsRegistry.NEUTERED_DROPS.get(creeper.getType());

                        creeper.spawnAtLocation(dropStack);

                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        return InteractionResult.PASS;
    }

}