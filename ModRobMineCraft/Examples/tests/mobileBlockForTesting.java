package com.ModRobMineCraft.Examples.tests;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class mobileBlockForTesting {

    protected Location location;
    protected Location wantedLocation;
    protected Block blk;
    protected boolean fly;
    protected boolean linked;
    protected BehaviorType behavior = null;
    protected int id;
    protected boolean forceMove;
    protected int msgId;


    public mobileBlockForTesting(Location loc) {
        this.blk = loc.getBlock();
        this.location = loc;
        this.wantedLocation = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        this.msgId = 0;
        this.blk.setType(Material.BRICK);
        this.forceMove = false;
        this.fly = false;
        this.linked = false;
        this.behavior = BehaviorType.Stop;
    }


    public Location getLocation() {
        return this.location;
    }

    //----------- location ---------------------------------------------
    public void setLocation(Location loc) {
        this.location = loc;
    }

    public void addToWantedLocation(int x, int y, int z) {
        this.wantedLocation.add(x, y, z);
    }

    public void setWantedLocation(int x, int y, int z) {
        this.wantedLocation = new Location(this.location.getWorld(), (double) x, (double) y, (double) z);
    }

    public Location getWantedLocation() {
        return this.wantedLocation;
    }

    //----------block--------------------------------------
    public Block getMCBlock() {
        return this.blk;
    }

    //----------- force movement-----------------------------------------------
    public boolean getForceMove() {
        return this.forceMove;
    }

    public void setForceMove(boolean fm) {
        this.forceMove = fm;
    }

    //----------- fly --------------------------------------------------
    public boolean getFly() {
        return this.fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

//    public void moveBlock() { // simpleMovement function
//
//        Movement mv = new Movement();
//        Location curLoc = this.blk.getLocation();
//        Location prevLoc = new Location(curLoc.getWorld(), curLoc.getX(), curLoc.getY(), curLoc.getZ());
//
//        Location finLoc = mv.simpleMovement(this.blk, this.goalLocation, this.fly, this.forceMove);
//
//        this.blk = finLoc.getBlock();
//        this.blk.setType(Material.BRICK);
//        Block blkPr = prevLoc.getBlock();
//        this.location.setX(finLoc.getX());
//        this.location.setY(finLoc.getY());
//        this.location.setZ(finLoc.getZ());
//        if (!mv.theSamelocation(blkPr.getLocation(), blk.getLocation())) {
//            blkPr.setType(Material.AIR);
//        }
//    }

    public BehaviorType getBehavior() {
        return this.behavior;
    }

    //---------------------------behaviors---------------------
    public void setBehavior(BehaviorType Behavior) {
        this.behavior = Behavior;
    }

}