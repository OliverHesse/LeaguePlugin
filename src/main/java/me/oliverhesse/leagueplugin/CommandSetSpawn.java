package me.oliverhesse.leagueplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class CommandSetSpawn implements CommandExecutor {
    public final LeaguePlugin main;
    public CommandSetSpawn(LeaguePlugin main){
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            Plugin plugin = sender.getServer().getPluginManager().getPlugin("LeaguePlugin");

            this.main.TempStart = player.getLocation();

        }
        return true;
    }
}
