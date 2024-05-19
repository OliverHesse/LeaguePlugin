package me.oliverhesse.leagueplugin.Events;

import me.oliverhesse.leagueplugin.GameOverReason;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class LobbyCleared extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final int lobbyID;
    public LobbyCleared(int lobbyID){
        this.lobbyID = lobbyID;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
    @Override
    public @NotNull HandlerList getHandlers(){
        return HANDLER_LIST;
    }

    public int getLobbyID(){
        return this.lobbyID;
    }


}