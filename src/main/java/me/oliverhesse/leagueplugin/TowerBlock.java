package me.oliverhesse.leagueplugin;

import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Shulker;

public class TowerBlock {
    private final BlockDisplay display;
    private final Shulker hitBox;

    public TowerBlock(BlockDisplay display,Shulker hitBox){
        this.display = display;
        this.hitBox = hitBox;
    }

    public BlockDisplay getDisplay(){
        return this.display;
    }
    public Shulker getHitBox(){
        return this.hitBox;
    }
}
