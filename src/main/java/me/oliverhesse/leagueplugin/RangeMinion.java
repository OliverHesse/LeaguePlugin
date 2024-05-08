package me.oliverhesse.leagueplugin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.Level;

public class RangeMinion extends Skeleton {
    public RangeMinion(EntityType<? extends Skeleton> type, Level world) {
        super(type, world);
    }
}
