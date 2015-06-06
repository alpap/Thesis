package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Block.MobileBlock;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public class MovementModular<T extends MobileBlock> {


    Location zeroblock;
    int gradient;
    Utility util;
    public MovementModular(){

        Location zeroblock=null;
        this.gradient =-1;
        this.util=new Utility();
    }

    public void setZerobBlock(Location zeroblock) {
        this.zeroblock = zeroblock;

    }

    public ArrayList scan(T blk) {
        ArrayList<Location> list = new ArrayList<Location>();
        //get block positions
        double x = blk.getLocation().getBlockX();
        double y = blk.getLocation().getBlockY();
        double z = blk.getLocation().getBlockZ();
        //add ground moving blocks locations

        list.add(new Location(blk.getLocation().getWorld(), x - 1, y, z));
        list.add(new Location(blk.getLocation().getWorld(), x + 1, y, z));
        list.add(new Location(blk.getLocation().getWorld(), x, y, z + 1));
        list.add(new Location(blk.getLocation().getWorld(), x, y, z - 1));

        if (blk.getFly() || blk.getLinked()) {
            // add vertically block positions
            list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z));
            list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z));
            //add linked block positions
            if (blk.getLinked()) {
                list.add(new Location(blk.getLocation().getWorld(), x + 1, y + 1, z));
                list.add(new Location(blk.getLocation().getWorld(), x + 1, y - 1, z));
                list.add(new Location(blk.getLocation().getWorld(), x - 1, y + 1, z));
                list.add(new Location(blk.getLocation().getWorld(), x - 1, y - 1, z));
                list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z + 1));
                list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z - 1));
                list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z + 1));
                list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z - 1));
            }
        }
        //check for collisions and previous locations
        for (int i = list.size()-1; i >=0; i--) {
            if (blk.getForceMove() && !theSamelocation(list.get(i), blk.getPrevLocation())) break;
            else if (collision(list.get(i)) || theSamelocation(list.get(i), blk.getPrevLocation())) {
                list.remove(i);
            }
        }


        return list;
    }

    /**
     * check if a location is occupied by something
     * @param loc Location object
     * @return true of false
     */
    public boolean collision(org.bukkit.Location loc) {
        if (loc.getBlock().getType()==Material.AIR) return false;
        return true;
    }

    public boolean isRobot(org.bukkit.Location loc) {
        if (loc.getBlock().getType()==(Material.BRICK)) return true;
        return false;
    }

    public boolean theSamelocation(org.bukkit.Location oldLoc, org.bukkit.Location newLoc) {
        if (oldLoc.getBlockX() != newLoc.getBlockX()) {
            return false;
        }
        if (oldLoc.getBlockY() != newLoc.getBlockY()) {
            return false;
        }
        if (oldLoc.getBlockZ() != newLoc.getBlockZ()) {
            return false;
        }
        return true;
    }

    /**
     *  Checks if the current block has something on the left or right of its position
     * @param blk mobile block to be checked
     * @return true if it shouldn't move false if it should
     */
    public boolean sideCheck (T blk) {
        double x = blk.getLocation().getBlockX();
        double y = blk.getLocation().getBlockY();
        double z = blk.getLocation().getBlockZ();
        if (new Location (blk.getLocation().getWorld(),x+1, y, z).getBlock().getType()!=(Material.AIR) &&
                new Location (blk.getLocation().getWorld(),x-1, y, z).getBlock().getType()!=(Material.AIR)) return true;
        else if (new Location (blk.getLocation().getWorld(),x, y+1, z).getBlock().getType()!=(Material.AIR) &&
                new Location (blk.getLocation().getWorld(),x, y-1, z).getBlock().getType()!=(Material.AIR)) return true;
        else if (new Location (blk.getLocation().getWorld(),x, y, z+1).getBlock().getType()!=(Material.AIR) &&
                new Location (blk.getLocation().getWorld(),x, y, z-1).getBlock().getType()!=(Material.AIR)) return true;
        return false;
    }

    public boolean sideCheckForBot(T blk,Location loc){
        boolean isBot = false;
        double x = blk.getLocation().getBlockX();
        double y = blk.getLocation().getBlockY();
        double z = blk.getLocation().getBlockZ();
        if (loc.getBlockX() == blk.getLocation().getBlockX() + 1 || loc.getBlockX() == blk.getLocation().getBlockX() - 1) {
            if (isRobot(new Location (blk.getLocation().getWorld(),x,y+1, z))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x,y-1,z))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x,y,z+1))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x,y,z-1))) isBot = true;
        } else if (loc.getBlockY() == blk.getLocation().getBlockY() + 1 || loc.getBlockY() == blk.getLocation().getBlockY() - 1) {
            if (isRobot(new Location (blk.getLocation().getWorld(),x+1,y, z))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x-1,y,z))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x,y,z+1))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x,y,z-1))) isBot = true;
        } else if (loc.getBlockZ() == blk.getLocation().getBlockZ() + 1 || loc.getBlockZ() == blk.getLocation().getBlockZ() - 1) {
            if (isRobot(new Location (blk.getLocation().getWorld(),x+1,y, z))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x-1,y,z))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x,y+1,z))) isBot = true;
            else if (isRobot(new Location (blk.getLocation().getWorld(),x,y-1,z))) isBot = true;
        }
        return isBot;
    }


    /**
     * Linked block local search algorithm
     * @param blk MobileBlock
     * @return next good location to move to
     */
    @SuppressWarnings("unchecked")
    public Location localSearchAlgorithmLinked(T blk) {

        if (blk.getGradient()>=this.gradient) { // check the gradient
            if (!sideCheck(blk)) {              // check the blocks sides
                ArrayList<Location> list = scan(blk); // scan for locations
                if (list.size() != 0) {             // check the list size
                    for (int i = list.size() - 1; i >= 0; i--) {   // traverse through the list
                        if (!theSamelocation(blk.getLocation(), list.get(i))) { // if not the previous location
                            if (!sideCheckForBot(blk, list.get(i))) list.remove(i); // if no robot neighbors delete list
                        }
                    }
                    double[] store = {10000, -1};
                    for (int i = list.size() - 1; i >= 0; i--) {
                        double distance = util.getDistance(list.get(i).getBlockX(), list.get(i).getBlockY(), list.get(i).getBlockZ(), blk.getGoalLocation().getBlockX(), blk.getGoalLocation().getBlockY(), blk.getGoalLocation().getBlockZ());
                        if (!collision(list.get(i))) {       // check for the closest position
                            if (distance < store[0]) {
                                store[1] = i;
                                store[0] = distance;
                            }
                        }
                    }
                    if (store[1] != -1) {
                        return list.get((int) store[1]); //return the closest position
                    }
                }
            }
        }
        return null;
    }


    /**
     * calculate the gradient for a block
     * @param blk mobile Block
     */
    public void updateGradient(T blk){
        double distance=util.getDistance(blk.getLocation().getBlockX(),blk.getLocation().getBlockY(),blk.getLocation().getBlockZ(),
                zeroblock.getBlockX(), zeroblock.getBlockY(), zeroblock.getBlockZ());
        int newGradient=(int)Math.floor(distance);
        blk.setGradient(newGradient);
        if (this.gradient < newGradient ) this.gradient= newGradient;
    }

    /**
     * Calculate the block with gradient zero
     * @param list ArrayList of locations
     */
    public void calculateGradient (ArrayList<T> list){

        double[] index={10000,-1};
        for(int i=0 ; i<list.size();i++){
            double distance=util.getDistance(list.get(i).getLocation().getBlockX(),list.get(i).getLocation().getBlockY(),
                    list.get(i).getLocation().getBlockZ(),zeroblock.getBlockX(),
                   zeroblock.getBlockY(),zeroblock.getBlockZ());
            if (distance<index[0]){
                index[1]=i;
                index[0]=distance;
            }
        }
        list.get((int)index[1]).setGradient(0);
        this.zeroblock=util.passLocation(list.get((int)index[1]).getLocation());

        for(int i=0 ; i<list.size();i++){
            updateGradient(list.get(i));
        }

    }

    public void stepMove(T blk,Location next) {


        if (new Location(blk.getLocation().getWorld(),blk.getLocation().getBlockX(),blk.getLocation().getBlockY()-1,blk.getLocation().getBlockZ()).getBlock().getType()==Material.AIR){
            blk.setPrevLoc(util.passLocation(blk.getLocation()));
            blk.getLocation().add(0, -1, 0);
            blk.getPrevLocation().getBlock().setType(Material.AIR);
            blk.getLocation().getBlock().setType(Material.BRICK);
        }else
        if (blk.getGradient()>=this.gradient) {

            if (next != null) {
                blk.setPrevLoc(util.passLocation(blk.getLocation()));

                if (blk.getLocation().getBlockX() < next.getBlockX()) {
                    blk.getLocation().add(1, 0, 0);
                } else if (blk.getLocation().getBlockX() > next.getBlockX()) {
                    blk.getLocation().add(-1, 0, 0);
                } else if (blk.getLocation().getBlockZ() < next.getBlockZ()) {
                    blk.getLocation().add(0, 0, 1);
                } else if (blk.getLocation().getBlockZ() > next.getBlockZ()) {
                    blk.getLocation().add(0, 0, -1);
                }
                if (blk.getFly() || blk.getLinked()) {
                    if (blk.getLocation().getBlockY() < next.getBlockY()) {
                        blk.getLocation().add(0, 1, 0);
                    } else if (blk.getLocation().getBlockY() > next.getBlockY() && new Location(blk.getLocation().getWorld(),blk.getLocation().getBlockX(),
                            blk.getLocation().getBlockY(),blk.getLocation().getBlockZ()).getBlock().getType() == Material.AIR) {
                        blk.getLocation().add(0, -1, 0);
                    }
                }
                blk.getPrevLocation().getBlock().setType(Material.AIR);
                blk.getLocation().getBlock().setType(Material.BRICK);
            }
        }
    }

    public void moveOnLinked(T blk) {

        stepMove(blk,localSearchAlgorithmLinked(blk));
    }


}
