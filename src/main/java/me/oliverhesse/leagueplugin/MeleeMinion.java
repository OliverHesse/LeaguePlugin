package me.oliverhesse.leagueplugin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MeleeMinion extends Zombie {
    LivingEntity currentTarget;
    private int currentTower = 0;
    private List<LivingEntity> towers;

    public LivingEntity target;
    public MeleeMinion(EntityType<? extends Zombie> type, Level world, Location loc,LivingEntity endPoint) {

        super(type, world);
        this.setPosRaw(loc.getX(),loc.getY(),loc.getZ());
        Bukkit.getServer().getLogger().info("should be called");
        Bukkit.getServer().getLogger().info(endPoint.toString());
        this.target = endPoint;
        Bukkit.getServer().getLogger().info(this.target.toString());

    }
    public void setCurrentTarget(LivingEntity endPoint){
        this.target = endPoint;
    }
    public void setupGameData(String team,String lane,Double health,Double speed){
        this.getBukkitEntity().getPersistentDataContainer().set(new NamespacedKey(LeaguePlugin.getInstance(),"team"), PersistentDataType.STRING,team);
        this.getBukkitEntity().getPersistentDataContainer().set(new NamespacedKey(LeaguePlugin.getInstance(),"lane"), PersistentDataType.STRING,lane);

        this.getBukkitEntity().getPersistentDataContainer().set(new NamespacedKey(LeaguePlugin.getInstance(),"team"), PersistentDataType.STRING,team);

    }
    @Override
    protected void registerGoals() {
        //this.targetSelector.addGoal();
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2,new NearestAttackableTargetGoal<>(this, Skeleton.class, true));
        //this.goalSelector.addGoal(2,new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3,new AttackCurrentTowerGoal<>(this,this.target));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

    }
    @Override
    public @NotNull AttributeMap getAttributes(){
        return new AttributeMap(MeleeMinion.createAttributes().build());
    }
    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH,1).add(Attributes.FOLLOW_RANGE, 3.0D).add(Attributes.MOVEMENT_SPEED, 0.23000000417232513D);
    }

}
