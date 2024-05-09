package me.oliverhesse.leagueplugin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RangeMinion extends Skeleton {
    public RangeMinion(EntityType<? extends Skeleton> type, Level world) {
        super(type, world);
    }
    @Override
    protected void registerGoals() {


    }
    @Override
    public @NotNull AttributeMap getAttributes(){
        return new AttributeMap(RangeMinion.createAttributes().build());
    }
    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,1).add(Attributes.FOLLOW_RANGE, 0D).add(Attributes.MOVEMENT_SPEED, 0.01D);
    }
}
