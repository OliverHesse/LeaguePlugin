package me.oliverhesse.leagueplugin;

import me.oliverhesse.leagueplugin.Events.LobbyCleared;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class LobbyManager implements Listener{
    //still deciding between static and dynamic lobby sizes
    //I will start programming for a single server.
    //so I will start with a limited amount of games at once
    private final List<Player> queue = new ArrayList<>();
    private final Lobby[] allLobbies = new Lobby[10];
    private final Plugin plugin;
    public LobbyManager(Plugin plugin){
        //initialize all lobbies. only needs to be done once.
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
        for (int i = 0; i < allLobbies.length; i++) {
            Lobby newLobby = new Lobby(6,plugin,i);
            plugin.getServer().getPluginManager().registerEvents(newLobby,plugin);
            allLobbies[i] = newLobby;

        }
    }

    public void addPlayerToLobby(Player player){
        if(!this.queue.isEmpty()){
            this.queue.add(player);
            return;
        }
        for (Lobby allLobby : allLobbies) {
            if (allLobby.acceptingPlayers()) {
                allLobby.addPlayer(player);
                return;
            }
        }
    }


    @EventHandler
    public void LobbyFinished(LobbyCleared event){
        if(!this.queue.isEmpty()){
            fillFromQueue(event.getLobbyID());
        }
    }
    public void fillFromQueue(int lobbyID){
        while(allLobbies[lobbyID].acceptingPlayers() && !queue.isEmpty()){
            allLobbies[lobbyID].addPlayer(queue.remove(0));
        }
    }





}
