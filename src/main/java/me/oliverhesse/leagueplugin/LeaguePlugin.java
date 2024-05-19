package me.oliverhesse.leagueplugin;

import net.minecraft.world.entity.LivingEntity;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class LeaguePlugin extends JavaPlugin {
    private static LeaguePlugin instance;
    private LobbyManager LobbyManager;
    public Location TempStart;
    public LivingEntity TempEnd;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getCommand("spawn").setExecutor(new CommandSpawn(this));

        getCommand("setDest").setExecutor(new CommandSetDest(this));
        getCommand("setSpawn").setExecutor(new CommandSetSpawn(this));
        getCommand("spawnTower").setExecutor(new CommandSpawnTower(this));

        GameManager currentGame = new GameManager();


        currentGame.startGame();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static LeaguePlugin getInstance() {
        return instance;
    }
}
