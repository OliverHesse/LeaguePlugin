package me.oliverhesse.leagueplugin;

import me.oliverhesse.leagueplugin.Events.GameOverEvent;
import me.oliverhesse.leagueplugin.Events.LobbyCleared;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;


import java.util.ArrayList;
import java.util.List;

public class Lobby implements Listener {
    private boolean gameState = false;
    private final Plugin plugin;
    private List<Player> lobbyPlayers = new ArrayList<>();
    private final int MAX_PLAYERS;
    private int gameID;
    private int lobbyID;


    public Lobby(int MAX_PLAYERS, Plugin plugin,int lobbyID) {
        this.MAX_PLAYERS = MAX_PLAYERS;
        this.plugin = plugin;
        this.lobbyID = lobbyID;

    }



    @EventHandler
    public void GameOverEvent(GameOverEvent event){
        //sort out lobby stuff

        LobbyCleared newEvent = new LobbyCleared(lobbyID);
        newEvent.callEvent();
    }
    public boolean acceptingPlayers(){
        return this.lobbyPlayers.size() < this.MAX_PLAYERS || !this.gameState;
    }

    public void addPlayer(Player player){
        //code to add player
    }


}
