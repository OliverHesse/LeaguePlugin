package me.oliverhesse.leagueplugin;

import net.minecraft.world.entity.LivingEntity;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class LeaguePlugin extends JavaPlugin {
    private static LeaguePlugin instance;
    public Location TempStart;
    public LivingEntity TempEnd;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getCommand("spawn").setExecutor(new CommandSpawn(this));

        getCommand("setDest").setExecutor(new CommandSetDest(this));
        getCommand("setSpawn").setExecutor(new CommandSetSpawn(this));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static LeaguePlugin getInstance() {
        return instance;
    }
}
