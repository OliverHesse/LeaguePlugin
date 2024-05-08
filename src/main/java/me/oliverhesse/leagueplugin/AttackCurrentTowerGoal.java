package me.oliverhesse.leagueplugin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import org.bukkit.event.entity.EntityTargetEvent;

public class AttackCurrentTowerGoal<T extends LivingEntity> extends TargetGoal {
    private LivingEntity target;

    public AttackCurrentTowerGoal(Mob mob,LivingEntity target) {
        super(mob, false, false);
    }

    @Override
    public boolean canUse() {
        return this.findTarget();
    }
    @Override
    public void start() {
        this.mob.setTarget(this.target, EntityTargetEvent.TargetReason.CUSTOM, true); // CraftBukkit - reason
        super.start();
    }

    protected boolean findTarget(){
        return this.target.isAlive();
    }
}
