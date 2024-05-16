package me.oliverhesse.leagueplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.block.data.type.Wall;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Slime;
import org.bukkit.plugin.Plugin;


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
        this.towerLocation.setX(Math.floor(this.towerLocation.getX())+3f);
        this.towerLocation.setY(Math.floor(this.towerLocation.getY()));
        this.towerLocation.setZ(Math.floor(this.towerLocation.getZ()));

    }
    public void build(){

        //north is -z south +z west -x east +x
        //this code constructs me a tower
        Location blockLocation = this.towerLocation.clone();
        Location shulkerLocation = this.towerLocation.clone();
        shulkerLocation.setX(shulkerLocation.getX()+0.5f);
        shulkerLocation.setZ(shulkerLocation.getZ()+0.5f);


        //create stairs for base
        placeSlime(shulkerLocation,0,0,-2);
        addStair(blockLocation,0,0,-2,Stairs.Shape.STRAIGHT,BlockFace.SOUTH, Bisected.Half.BOTTOM);

        placeSlime(shulkerLocation,0,0,4);
        addStair(blockLocation,0,0,4,Stairs.Shape.STRAIGHT,BlockFace.NORTH, Bisected.Half.BOTTOM);

        placeSlime(shulkerLocation,2,0,-2);
        addStair(blockLocation,2,0,-2,Stairs.Shape.STRAIGHT,BlockFace.WEST, Bisected.Half.BOTTOM);

        placeSlime(shulkerLocation,-4,0,0);
        addStair(blockLocation,-4,0,0,Stairs.Shape.STRAIGHT,BlockFace.EAST, Bisected.Half.BOTTOM);

        //reset location
        shulkerLocation.setX(shulkerLocation.getX()+2);
        blockLocation.setX(blockLocation.getX()+2);

        //create interlocking sections
        placeSlime(shulkerLocation,1,0,1);
        addStair(blockLocation,1,0,1,Stairs.Shape.INNER_RIGHT,BlockFace.WEST, Bisected.Half.BOTTOM);
        placeSlime(shulkerLocation,0,0,1);
        addStair(blockLocation,0,0,1,Stairs.Shape.OUTER_RIGHT,BlockFace.WEST, Bisected.Half.BOTTOM);
        placeSlime(shulkerLocation,1,0,-1);
        addStair(blockLocation,1,0,-1,Stairs.Shape.OUTER_RIGHT,BlockFace.WEST, Bisected.Half.BOTTOM);

        placeSlime(shulkerLocation,-3,0,-2);
        addStair(blockLocation,-3,0,-2,Stairs.Shape.INNER_RIGHT,BlockFace.EAST, Bisected.Half.BOTTOM);
        placeSlime(shulkerLocation,-1,0,0);
        addStair(blockLocation,-1,0,0,Stairs.Shape.OUTER_RIGHT,BlockFace.EAST, Bisected.Half.BOTTOM);
        placeSlime(shulkerLocation,1,0,-1);
        addStair(blockLocation,1,0,-1,Stairs.Shape.OUTER_RIGHT,BlockFace.EAST, Bisected.Half.BOTTOM);


        placeSlime(shulkerLocation,2,0,1);
        addStair(blockLocation,2,0,1,Stairs.Shape.INNER_RIGHT,BlockFace.SOUTH, Bisected.Half.BOTTOM);
        placeSlime(shulkerLocation,1,0,0);
        addStair(blockLocation,1,0,0,Stairs.Shape.OUTER_RIGHT,BlockFace.SOUTH, Bisected.Half.BOTTOM);
        placeSlime(shulkerLocation,-1,0,-1);
        addStair(blockLocation,-1,0,-1,Stairs.Shape.OUTER_RIGHT,BlockFace.SOUTH, Bisected.Half.BOTTOM);


        placeSlime(shulkerLocation,-2,0,3);
        addStair(blockLocation,-2,0,3,Stairs.Shape.INNER_RIGHT,BlockFace.NORTH, Bisected.Half.BOTTOM);
        placeSlime(shulkerLocation,-1,0,0);
        addStair(blockLocation,-1,0,0,Stairs.Shape.OUTER_RIGHT,BlockFace.NORTH, Bisected.Half.BOTTOM);
        placeSlime(shulkerLocation,1,0,1);
        addStair(blockLocation,1,0,1,Stairs.Shape.OUTER_RIGHT,BlockFace.NORTH, Bisected.Half.BOTTOM);

        //reset location
        shulkerLocation.setX(shulkerLocation.getX()+1);
        blockLocation.setX(blockLocation.getX()+1);
        shulkerLocation.setZ(shulkerLocation.getZ()-2);
        blockLocation.setZ(blockLocation.getZ()-2);



        //place body layers
        for(int i = 0;i<7;i++){

            //create the base of the tower
            placeShulker(shulkerLocation,0,i,0);
            addBlock(blockLocation,0,i,0,Material.STONE_BRICKS);


            //add one block around it
            placeShulker(shulkerLocation,1,0,0);
            addBlock(blockLocation,1,0,0,Material.STONE_BRICKS);

            placeShulker(shulkerLocation,-2,0,0);
            addBlock(blockLocation,-2,0,0,Material.STONE_BRICKS);

            placeShulker(shulkerLocation,1,0,1);
            addBlock(blockLocation,1,0,1,Material.STONE_BRICKS);

            placeShulker(shulkerLocation,0,0,-2);
            addBlock(blockLocation,0,0,-2,Material.STONE_BRICKS);
            //reset location
            shulkerLocation.setZ(shulkerLocation.getZ()+1);
            blockLocation.setZ(blockLocation.getZ()+1);
            shulkerLocation.setY(shulkerLocation.getY()-i);
            blockLocation.setY(blockLocation.getY()-i);

        }
        for(int i=1;i<7;i++){
            placeShulker(shulkerLocation,1,i,1);
            addWall(blockLocation,1,i,1, new BlockFace[]{BlockFace.NORTH, BlockFace.WEST});

            placeShulker(shulkerLocation,-2,0,-2);
            addWall(blockLocation,-2,0,-2, new BlockFace[]{BlockFace.SOUTH, BlockFace.EAST});

            placeShulker(shulkerLocation,0,0,2);
            addWall(blockLocation,0,0,2 ,new BlockFace[]{BlockFace.NORTH, BlockFace.EAST});

            placeShulker(shulkerLocation,2,0,-2);
            addWall(blockLocation,2,0,-2,new BlockFace[]{BlockFace.SOUTH, BlockFace.WEST});


            shulkerLocation.setZ(shulkerLocation.getZ()+1);
            blockLocation.setZ(blockLocation.getZ()+1);
            shulkerLocation.setX(shulkerLocation.getX()-1);
            blockLocation.setX(blockLocation.getX()-1);
            shulkerLocation.setY(shulkerLocation.getY()-i);
            blockLocation.setY(blockLocation.getY()-i);
        }
        shulkerLocation.setY(shulkerLocation.getY()+7);
        blockLocation.setY(blockLocation.getY()+7);
        for(int x =-2;x<3;x++){
            for(int z=-2;z<3;z++){
                placeShulker(shulkerLocation,x,0,z);
                addBlock(blockLocation,x,0,z,Material.STONE_BRICKS);

                shulkerLocation.setZ(shulkerLocation.getZ()-z);
                blockLocation.setZ(blockLocation.getZ()-z);

                shulkerLocation.setX(shulkerLocation.getX()-x);
                blockLocation.setX(blockLocation.getX()-x);
            }
        }



    }
    public static void placeShulker(Location location,float XChange,float YChange,float ZChange){
        location.setZ(location.getZ()+ZChange);
        location.setX(location.getX()+XChange);
        location.setY(location.getY()+YChange);

        Shulker newShulker = (Shulker) location.getWorld().spawnEntity(location, EntityType.SHULKER);

        newShulker.setAI(false);
        newShulker.setCollidable(true);
        newShulker.setInvisible(true);
    }
    public static void addBlock(Location location,float XChange,float YChange,float ZChange,Material type){
        location.setZ(location.getZ()+ZChange);
        location.setX(location.getX()+XChange);
        location.setY(location.getY()+YChange);
        BlockDisplay newBlock = location.getWorld().spawn(location,BlockDisplay.class);
        newBlock.setBlock(Bukkit.createBlockData(type));

    }
    public static void addWall(Location location,float XChange,float YChange,float ZChange,BlockFace[] direction){
        location.setZ(location.getZ()+ZChange);
        location.setX(location.getX()+XChange);
        location.setY(location.getY()+YChange);
        BlockDisplay newBlock = location.getWorld().spawn(location,BlockDisplay.class);

        Wall wallMeta = (Wall) Bukkit.createBlockData(Material.STONE_BRICK_WALL);
        wallMeta.setHeight(direction[0],Wall.Height.TALL);
        wallMeta.setHeight(direction[1],Wall.Height.TALL);
        newBlock.setBlock(wallMeta);
    }
    public static void placeSlime(Location location,float XChange,float YChange,float ZChange){
        location.setZ(location.getZ()+ZChange);
        location.setX(location.getX()+XChange);
        location.setY(location.getY()+YChange);

        Slime newSlime = (Slime) location.getWorld().spawnEntity(location, EntityType.SLIME);

        newSlime.setAI(false);
        newSlime.setCollidable(true);
        newSlime.setInvisible(true);
        newSlime.setSize(2);


    }
    public static void addStair(Location location, float XChange, float YChange, float ZChange, Stairs.Shape shape, BlockFace direction, Bisected.Half half){
        //stairs are slimes cus fuck this game and their damn shulker display logic
        location.setZ(location.getZ()+ZChange);
        location.setX(location.getX()+XChange);
        location.setY(location.getY()+YChange);

        BlockDisplay newStair3 = location.getWorld().spawn(location,BlockDisplay.class);
        Stairs newStairs = (Stairs) Material.STONE_BRICK_STAIRS.createBlockData();
        newStairs.setShape(shape);
        newStairs.setFacing(direction);
        newStairs.setHalf(half);
        newStair3.setBlock(newStairs);

    }


}
