package me.oliverhesse.leagueplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Slime;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Transformation;


import java.util.List;

public class Tower {
    private final Plugin plugin;
    private final Location towerLocation;
    private List<TowerBlock> blocks;
    //change this to work with a custom mob
    private Shulker turret;
    public Tower(Location location, Plugin plugin){
        this.plugin = plugin;
        this.towerLocation = location;
        this.towerLocation.setPitch(0f);
        this.towerLocation.setYaw(0f);
        this.towerLocation.setX(Math.floor(this.towerLocation.getX()));
        this.towerLocation.setY(Math.floor(this.towerLocation.getY()));
        this.towerLocation.setZ(Math.floor(this.towerLocation.getZ()));

    }
    public void build(){
        //this code constructs me a tower

        //temporarily store a slime block and display block over each-other. make slime slightly bigger
        Location shulkerLocations = this.towerLocation.clone();
        shulkerLocations.setZ(shulkerLocations.getZ()+0.5f);
        shulkerLocations.setX(shulkerLocations.getX()+0.5f);

        Shulker newShulker = (Shulker) shulkerLocations.getWorld().spawnEntity(shulkerLocations, EntityType.SHULKER);

        newShulker.setAI(false);
        newShulker.setCollidable(true);
        newShulker.setInvisible(true);
        BlockDisplay newBlock = towerLocation.getWorld().spawn(towerLocation,BlockDisplay.class);
        newBlock.setBlock(Bukkit.createBlockData(Material.STONE_BRICKS));


        Location stairLocation = this.towerLocation.clone();
        stairLocation.setX(stairLocation.getX()+1f);

        BlockDisplay newStair1 = stairLocation.getWorld().spawn(stairLocation,BlockDisplay.class);
        newStair1.setBlock(Bukkit.createBlockData(Material.STONE_BRICK_STAIRS));

        stairLocation.setX(stairLocation.getX()+1f);

        BlockDisplay newStair2 = stairLocation.getWorld().spawn(stairLocation,BlockDisplay.class);
        newStair2.setBlock(Bukkit.createBlockData(Material.STONE_BRICK_STAIRS));

        //stairLocation.setZ(stairLocation.getZ()-1f);
        stairLocation.setYaw(180f);

        BlockDisplay newStair3 = stairLocation.getWorld().spawn(stairLocation,BlockDisplay.class);
        Stairs newStairs = (Stairs) Material.STONE_BRICK_STAIRS.createBlockData();
        newStairs.setShape(Stairs.Shape.INNER_LEFT);
        newStair3.setBlock(newStairs);

        stairLocation.setX(stairLocation.getX()+1f);


        BlockDisplay newStair4 = stairLocation.getWorld().spawn(stairLocation,BlockDisplay.class);
        newStair4.setBlock(Bukkit.createBlockData(Material.STONE_BRICK_STAIRS));
        plugin.getServer().getLogger().info(newBlock.getLocation().toString());
        plugin.getServer().getLogger().info(newStair1.getLocation().toString());
        plugin.getServer().getLogger().info(newStair2.getLocation().toString());
        plugin.getServer().getLogger().info(newStair3.getLocation().toString());
        plugin.getServer().getLogger().info(newStair4.getLocation().toString());
    }
}
