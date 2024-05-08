package me.oliverhesse.leagueplugin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class CommandSetDest implements CommandExecutor {
    public final LeaguePlugin main;
    public CommandSetDest(LeaguePlugin main){
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            Plugin plugin = sender.getServer().getPluginManager().getPlugin("LeaguePlugin");
            Skeleton skeleton = new Skeleton(EntityType.SKELETON, ((CraftWorld) player.getWorld()).getHandle());

            // Set position
            skeleton.setPosRaw(player.getX(),player.getY(), player.getZ());

            // Disable AI
            skeleton.setNoAi(true);

            ((CraftWorld) player.getWorld()).getHandle().addFreshEntity(skeleton, CreatureSpawnEvent.SpawnReason.COMMAND);

            this.main.TempEnd =  skeleton;
        }
        return false;
    }
}
