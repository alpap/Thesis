package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Block.MobileBlock;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;


public class LinkedMovement<T extends MobileBlock> {


    final int gradient=1;
    int maxGradient;
    Utility util;
    BehaviorManager<T> bhm;
    int counterForGradient;
    int divisionForGradient;
    boolean init;

    public LinkedMovement(BehaviorManager<T> bht) {

        this.maxGradient = -1;
        this.util = new Utility();
        this.bhm = bht;
        this.init = true;

    }

    @SuppressWarnings("unchecked")
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
        list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z));
        list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z));
        list.add(new Location(blk.getLocation().getWorld(), x + 1, y + 1, z));
        list.add(new Location(blk.getLocation().getWorld(), x + 1, y - 1, z));
        list.add(new Location(blk.getLocation().getWorld(), x - 1, y + 1, z));
        list.add(new Location(blk.getLocation().getWorld(), x - 1, y - 1, z));
        list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z + 1));
        list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z - 1));
        list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z + 1));
        list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z - 1));

        //check for collisions and previous locations
        for (int i = 13; i >= 0; i--) {
            if (util.collision(list.get(i))) {
                list.remove(i);
            }
        }
        for (int j = 0; j < blk.oldLocSize(); j++) {
            list.remove(blk.getOlderLocations(j));
        }
        return list;
    }

    public boolean sideCheckForBot(T blk, Location loc) {
        boolean isBot = false;
        double x = blk.getLocation().getBlockX();
        double y = blk.getLocation().getBlockY();
        double z = blk.getLocation().getBlockZ();
        if (loc.getBlockX() == blk.getLocation().getBlockX() + 1 || loc.getBlockX() == blk.getLocation().getBlockX() - 1) {
            if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y + 1, z))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y - 1, z))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y, z + 1))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y, z - 1))) isBot = true;
        } else if (loc.getBlockY() == blk.getLocation().getBlockY() + 1 || loc.getBlockY() == blk.getLocation().getBlockY() - 1) {
            if (util.isRobot(new Location(blk.getLocation().getWorld(), x + 1, y, z))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x - 1, y, z))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y, z + 1))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y, z - 1))) isBot = true;
        } else if (loc.getBlockZ() == blk.getLocation().getBlockZ() + 1 || loc.getBlockZ() == blk.getLocation().getBlockZ() - 1) {
            if (util.isRobot(new Location(blk.getLocation().getWorld(), x + 1, y, z))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x - 1, y, z))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y + 1, z))) isBot = true;
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y - 1, z))) isBot = true;
        }
        return isBot;
    }



    /**
     * Linked block local search algorithm
     *
     * @param blk MobileBlock
     * @return next good location to move to
     */
    @SuppressWarnings("unchecked")
    public Location localSearchAlgorithmLinked(T blk) {

        if (blk.getGradient() >= this.maxGradient -gradient) { // check the gradient
            ArrayList<Location> list = scan(blk); // scan for locations
            if (list.size() > 0) {             // check the list size
                for (int i = list.size() - 1; i >= 0; i--) {   // traverse through the list
                    if (!sideCheckForBot(blk, list.get(i))) list.remove(i); // if no robot neighbors delete list
                }
                double[] store = {10000, -1};
                for (int i = list.size() - 1; i >= 0; i--) {
                    double distance = util.getDistance(list.get(i).getBlockX(), list.get(i).getBlockY(), list.get(i).getBlockZ(), blk.getGoalLocation().getBlockX(), blk.getGoalLocation().getBlockY(), blk.getGoalLocation().getBlockZ());
                    if (!util.collision(list.get(i))) {       // check for the closest position
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
        return null;
    }

    /**
     * Calculate the block with gradient zero
     */
    @SuppressWarnings("unchecked")
    public void calculateGradient() {
        maxGradient = 0;
        ArrayList<T> list = bhm.getRobots();
        int dis;
        for (T aList : list) {
            dis = util.getDistance(aList.getLocation(), aList.getGoalLocation());
            aList.setGradient(dis);
            if (dis > maxGradient) maxGradient = dis;
        }
    }


    /**
     * move the block one step towards the goal Location
     *
     * @param blk MobileBlock
     */
    public void stepMove(T blk) {

        Location next = localSearchAlgorithmLinked(blk);
        blk.setPrevLoc(blk.getLocation().clone());
        blk.addToOlderLocation(blk.getLocation().clone());
//        if (blk.getLocation().getBlockY() > 65 && (util.passLocation(blk.getLocation(), 0, -1, 0)).getBlock().getType() == Material.AIR) {// if above ground level move down to ground
//            blk.getLocation().add(0, -1, 0);
//            blk.getPrevLocation().getBlock().setType(Material.AIR);
//            blk.getLocation().getBlock().setType(Material.GOLD_BLOCK);
//
//        }else
        if (!util.isRobot(util.passLocation(blk.getLocation(), 0, 1, 0))) {
            if (next != null) {
                if (blk.getGradient() >= this.maxGradient - gradient) {
                    if (blk.getLocation().getBlockX() < next.getBlockX()) {
                        blk.getLocation().add(1, 0, 0);
                    } else if (blk.getLocation().getBlockX() > next.getBlockX()) {
                        blk.getLocation().add(-1, 0, 0);
                    }
                    if (blk.getLocation().getBlockZ() < next.getBlockZ()) {
                        blk.getLocation().add(0, 0, 1);
                    } else if (blk.getLocation().getBlockZ() > next.getBlockZ()) {
                        blk.getLocation().add(0, 0, -1);
                    }
                    if (blk.getLocation().getBlockY() < next.getBlockY()) {
                        blk.getLocation().add(0, 1, 0);
                    } else if (blk.getLocation().getBlockY() > next.getBlockY() && blk.getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.AIR) {
                        blk.getLocation().add(0, -1, 0);
                    }


                    blk.getPrevLocation().getBlock().setType(Material.AIR);
                    blk.getLocation().getBlock().setType(Material.GOLD_BLOCK);
                }
            }
        }
    }


    public void moveOnLinked(T blk) {

        if (init) {
            this.divisionForGradient = (((int) Math.cbrt((double) bhm.numberOfRobots())));
            this.counterForGradient = divisionForGradient;
            init = false;
        }
        if (counterForGradient == divisionForGradient) {
            calculateGradient();
            this.counterForGradient = -1;
        }
        counterForGradient++;

        stepMove(blk);

    }


}
