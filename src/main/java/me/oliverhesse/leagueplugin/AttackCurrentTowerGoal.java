package me.oliverhesse.leagueplugin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.EnumSet;

public class AttackCurrentTowerGoal<T extends LivingEntity> extends TargetGoal {

    private final MeleeMinion parent;
    private final LivingEntity target;
    public AttackCurrentTowerGoal(Mob mob,LivingEntity target) {

        super(mob, false, false);
        this.parent = (MeleeMinion) mob;
        this.target = target;
        //this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        Bukkit.getServer().getLogger().info("Is called");
        Bukkit.getServer().getLogger().info(parent.toString());
    }

    @Override
    public boolean canUse() {
        Bukkit.getServer().getLogger().info("can it use this?");
        Bukkit.getServer().getLogger().info(((Boolean) (this.parent.target != null && this.mob.distanceTo(this.parent.target) > this.getFollowDistance())).toString());

        return this.parent.target != null && this.mob.distanceTo(this.parent.target) > this.getFollowDistance();

    }
    @Override
    public void start() {
        Bukkit.getServer().getLogger().info("trying to start event");
        Bukkit.getServer().getLogger().info(((Boolean) (this.parent.target != null)).toString());
        if (this.parent.target != null && this.parent.target.isAlive()) {
            if (this.mob.distanceTo(this.parent.target) > this.getFollowDistance()) {
                // Target is out of range, move towards it
                this.mob.getNavigation().moveTo(this.parent.target, 1.0);
            }
        }

        super.start();
    }

    protected boolean findTarget(){
        return this.parent.isAlive();
    }
}
