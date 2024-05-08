package me.oliverhesse.leagueplugin;

import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


public class CommandSpawn implements CommandExecutor {
    public final LeaguePlugin main;
    public CommandSpawn(LeaguePlugin main){
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player){
            sender.getServer().getLogger().info(this.main.TempEnd.toString());

            Plugin plugin = sender.getServer().getPluginManager().getPlugin("LeaguePlugin");
            Location newLocation = this.main.TempStart;


            MeleeMinion customZombie = new MeleeMinion(net.minecraft.world.entity.EntityType.ZOMBIE, ((CraftWorld) player.getWorld()).getHandle(),newLocation,this.main.TempEnd);
            customZombie.setCurrentTarget(this.main.TempEnd);
            customZombie.target = this.main.TempEnd;
            sender.getServer().getLogger().info(customZombie.target.toString());
            boolean didItWork = ((CraftWorld) player.getWorld()).getHandle().addFreshEntity(customZombie, CreatureSpawnEvent.SpawnReason.COMMAND);
            if(didItWork){
                plugin.getServer().getLogger().info("did not work");
            }
            Component displayName = Component.Serializer.fromJson("{\"text\":\"Melee Minion\"}");

            customZombie.setCustomName(displayName);
            customZombie.setCustomNameVisible(true);
            return true;
        }

        return false;
    }
}

