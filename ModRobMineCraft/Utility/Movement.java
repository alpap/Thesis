package com.ModRobMineCraft.Utility;


import com.ModRobMineCraft.Block.MobileBlock;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;


public class Movement<T extends MobileBlock> {

    public Movement() {
    }

    public boolean collision(org.bukkit.Location loc) {
        if (loc.getBlock().getType().equals(Material.AIR)) return false;
        return true;
    }

    public boolean isRobot(org.bukkit.Location loc) {
        if (loc.getBlock().getType().equals(Material.BRICK)) return true;
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

    //=======================improved for linked movement detection===================================================
    public Location localSearchAlgorithmLinked(T blk) {

        Utility util = new Utility();
        ArrayList<Location> list = scan(blk);
        if (list.size() != 0) {
            Location loc = blk.getLocation().clone();
            for (int i = 0; i < list.size(); i++) {

                if (!theSamelocation(blk.getLocation(), list.get(i))) {
                    if (list.get(i).getBlockX() == blk.getLocation().getBlockX() + 1 || list.get(i).getBlockX() == blk.getLocation().getBlockX() - 1) {
//                        if (list.get(i).getBlockX() == blk.getLocation().getBlockX() + 1 && isRobot(blk.getLocation().add(-1,0,0)) ){
//                            list.remove(i);
//                            break;
//                        }
//                        if (list.get(i).getBlockX() == blk.getLocation().getBlockX() - 1 && isRobot(blk.getLocation().add(1,0,0)) ){
//                            list.remove(i);
//                            break;
//                        }
                        if (isRobot(list.get(i).clone().add(0, 1, 0))) break;
                        else if (isRobot(list.get(i).clone().add(0, -1, 0))) break;
                        else if (isRobot(list.get(i).clone().add(0, 0, 1))) break;
                        else if (isRobot(list.get(i).clone().add(0, 0, -1))) break;
                        else list.remove(i);
                    } else if (list.get(i).getBlockY() == blk.getLocation().getBlockY() + 1 || list.get(i).getBlockY() == blk.getLocation().getBlockY() - 1) {
//
                        if (isRobot(list.get(i).clone().add(1, 0, 0))) break;
                        else if (isRobot(list.get(i).clone().add(-1, 0, 0))) break;
                        else if (isRobot(list.get(i).clone().add(0, 0, 1))) break;
                        else if (isRobot(list.get(i).clone().add(0, 0, -1))) break;
                        else list.remove(i);
                    } else if (list.get(i).getBlockZ() == blk.getLocation().getBlockZ() + 1 || list.get(i).getBlockZ() == blk.getLocation().getBlockZ() - 1) {
//
                        if (isRobot(list.get(i).clone().add(1, 0, 0))) break;
                        else if (isRobot(list.get(i).clone().add(-1, 0, 0))) break;
                        else if (isRobot(list.get(i).clone().add(0, 1, 0))) break;
                        else if (isRobot(list.get(i).clone().add(0, -1, 0))) break;
                        else list.remove(i);
                    }
                }
            }
            int[] store = {10000, -1};
            for (int i = 0; i < list.size(); i++) {
                if (util.getDistance(list.get(i).getBlockX(), list.get(i).getBlockY(), list.get(i).getBlockZ(), blk.getWantedLocation().getBlockX(), blk.getWantedLocation().getBlockY(), blk.getWantedLocation().getBlockZ()) < store[0])
                    store[1] = i;
            }
            if (store[1] != -1) {
                return list.get(store[1]).clone();
            }
        }
        return null;
    }


    //=========================improved for normal movement detection==================================================

    public Location localSearchAlgorithm(T blk) {
        Utility util = new Utility();
        ArrayList<Location> list = scan(blk);
        if (list.size() != 0) {
            double[] store = {10000, -1};
            if (list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (!collision(list.get(i))) {
                        if (util.getDistance(list.get(i).getBlockX(), list.get(i).getBlockY(), list.get(i).getBlockZ(), blk.getWantedLocation().getBlockX(), blk.getWantedLocation().getBlockY(), blk.getWantedLocation().getBlockZ()) < store[0]) {
                            store[1] = i;
                            store[0] = util.getDistance(list.get(i).getBlockX(), list.get(i).getBlockY(), list.get(i).getBlockZ(), blk.getWantedLocation().getBlockX(), blk.getWantedLocation().getBlockY(), blk.getWantedLocation().getBlockZ());
                        }
                    }
                }
            }
            if (store[1] != -1) {
                return list.get((int) store[1]).clone();
            }
        }
        return null;

    }

    //========================================================================================================

    public ArrayList scan(T blk) {
        ArrayList<Location> list = new ArrayList<Location>();

        double x = blk.getLocation().getBlockX();
        double y = blk.getLocation().getBlockY();
        double z = blk.getLocation().getBlockZ();

        list.add(new Location(blk.getLocation().getWorld(), x - 1, y, z));
        list.add(new Location(blk.getLocation().getWorld(), x + 1, y, z));
        list.add(new Location(blk.getLocation().getWorld(), x, y, z + 1));
        list.add(new Location(blk.getLocation().getWorld(), x, y, z - 1));

        if (blk.getFly() || blk.getLinked()) {

            list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z));
            list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z));
            if (blk.getLinked()) {
                list.add(new Location(blk.getLocation().getWorld(), x + 1, y + 1, z));
                list.add(new Location(blk.getLocation().getWorld(), x + 1, y - 1, z));
                list.add(new Location(blk.getLocation().getWorld(), x - 1, y + 1, z));
                list.add(new Location(blk.getLocation().getWorld(), x - 1, y - 1, z));
                list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z + 1));
                list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z - 1));
                list.add(new Location(blk.getLocation().getWorld(), x, y + 1, z + 1));
                list.add(new Location(blk.getLocation().getWorld(), x, y - 1, z - 1));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (blk.getForceMove() && !theSamelocation(list.get(i), blk.getPrevLocation())) break;
            else if (!(list.get(i).getBlock().getType() == Material.AIR) || theSamelocation(list.get(i), blk.getPrevLocation())) {
                list.remove(i);
            }
        }


        return list;
    }


    //=======================================new movement==============================
    public void moveFullPath(T blk, Location next) {
        if (next != null) {
            while (!theSamelocation(blk.getLocation(), blk.getWantedLocation())) {
                blk.setPrevLoc(blk.getLocation().clone());

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
                    } else if (blk.getLocation().getBlockY() > next.getBlockY()) {
                        blk.getLocation().add(0, -1, 0);
                    }
                }
                if (!theSamelocation(blk.getLocation(), blk.getPrevLocation())) {
                    blk.getPrevLocation().getBlock().setType(Material.AIR);
                    blk.getLocation().getBlock().setType(Material.BRICK);
                }


            }
        }
    }

    public void stepMove(T blk, Location next) {
        blk.setPrevLoc(blk.getLocation().clone());
        if (blk.getLocation().getBlockY()>65 && !blk.getLinked() && !blk.getFly()){
            Location ll=blk.getLocation().clone().add(0,-1,0);
            if (ll.getBlock().getType()==Material.AIR){
                blk.getLocation().add(0, -1, 0);
                blk.getPrevLocation().getBlock().setType(Material.AIR);
                blk.getLocation().getBlock().setType(Material.BRICK);
            }
        }else{

        if (next != null) {


            if (blk.getLocation().getBlockX() < next.getBlockX()) {
                blk.getLocation().add(1, 0, 0);
            } else if (blk.getLocation().getBlockX() > next.getBlockX()) {
                blk.getLocation().add(-1, 0, 0);
            } else if (blk.getLocation().getBlockZ() < next.getBlockZ()) {
                blk.getLocation().add(0, 0, 1);
            } else if (blk.getLocation().getBlockZ() > next.getBlockZ()) {
                blk.getLocation().add(0, 0, -1);
            }if (blk.getFly() || blk.getLinked()) {
                if (blk.getLocation().getBlockY() < next.getBlockY()) {
                    blk.getLocation().add(0, 1, 0);
                } else if (blk.getLocation().getBlockY() > next.getBlockY()) {
                    blk.getLocation().add(0, -1, 0);
                }
            }


            blk.getPrevLocation().getBlock().setType(Material.AIR);
            blk.getLocation().getBlock().setType(Material.BRICK);
        }
        }
    }

    //==============================================================================
    public void moveOnLinked(T blk, boolean fullpath) {

        if (fullpath) moveFullPath(blk, localSearchAlgorithmLinked(blk)); // kati exw kanei lathos den exei full path
        else stepMove(blk, localSearchAlgorithmLinked(blk));
    }

    public void move(T blk, boolean fullpath) {

        if (fullpath) moveFullPath(blk, localSearchAlgorithm(blk));
        else stepMove(blk, localSearchAlgorithm(blk));
    }


}