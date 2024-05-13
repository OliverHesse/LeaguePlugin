package me.oliverhesse.leagueplugin;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class CommandSpawnTower implements CommandExecutor {
    private final Plugin plugin;
    public CommandSpawnTower(Plugin plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            Tower newTower = new Tower(player.getLocation(),plugin);
            newTower.build();

            return true;
        }

        return false;
    }
}
