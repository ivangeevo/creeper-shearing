package org.btwr.creeper_shearing.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.monster.Creeper;
import org.btwr.creeper_shearing.data.*;
import org.jetbrains.annotations.Nullable;

public class CreeperSwellBehavior extends SwellGoal {

    private final Creeper myCreeper;

    @Nullable private LivingEntity target;

    public CreeperSwellBehavior(Creeper creeper) {
        super(creeper);
        myCreeper = creeper;
    }

    @Override
    public boolean canUse() {
        CreeperNeuterData neuterData = (CreeperNeuterData) CreeperDataAttachment.getOrCreate(myCreeper, CreeperDataKeys.NEUTER_DATA);
        CreeperExplodeData explodeData = (CreeperExplodeData) CreeperDataAttachment.getOrCreate(myCreeper, CreeperDataKeys.EXPLODE_DATA);

        if (!myCreeper.isIgnited() && (neuterData != null && neuterData.isNeutered())) {
            return false;
        } else if (explodeData != null && explodeData.getIsDeterminedToExplode()) {
            return true;
        }

        return super.canUse();
    }

    @Override
    public void start() {
        this.myCreeper.getNavigation().stop();
        this.target = this.myCreeper.getTarget();
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public void tick() {
        CreeperNeuterData neuterData = (CreeperNeuterData) CreeperDataAttachment.getOrCreate(myCreeper, CreeperDataKeys.NEUTER_DATA);

        if (this.target == null || (neuterData != null && neuterData.isNeutered())) {
            this.myCreeper.setSwellDir(-1);
            return;
        }
        if (this.myCreeper.distanceToSqr(this.target) > 36.0) {
            this.myCreeper.setSwellDir(-1);
            return;
        }
        if (!this.myCreeper.getSensing().hasLineOfSight(this.target)) {
            this.myCreeper.setSwellDir(-1);
            return;
        }
        this.myCreeper.setSwellDir(1);
    }

}