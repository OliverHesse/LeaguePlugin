package me.oliverhesse.leagueplugin.Events;


import me.oliverhesse.leagueplugin.GameOverReason;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GameOverEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public GameOverEvent(int gameID, String gameWinner, GameOverReason reason){
        //game winner can be red blue or draw
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
    @Override
    public @NotNull HandlerList getHandlers(){
        return HANDLER_LIST;
    }



}
