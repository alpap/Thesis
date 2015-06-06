package com.ModRobMineCraft.Utility;


import com.ModRobMineCraft.Block.MobileBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import java.util.ArrayList;


public class Movement<T extends MobileBlock> {
    Utility util = new Utility();

    public Movement() {
    }

    /**
     * check if a location is occupied by something
     * @param loc Location object
     * @return true of false
     */
    public boolean collision(org.bukkit.Location loc) {
        if (loc.getBlock().getType().equals(Material.AIR)) return false;
        return true;
    }

    /**
     * check if a robot is at the current location by default the robots are of type BRICk
     * @param loc Location object
     * @return true of false
     */
    public boolean isRobot(org.bukkit.Location loc) {
        if (loc.getBlock().getType().equals(Material.GOLD_BLOCK)) return true;
        return false;
    }

    /**
     * Check if the two locations are the same
     * @param oldLoc old Location object
     * @param newLoc new Location object
     * @return true of false
     */
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

    //=========================improved for normal movement detection==================================================

    /**
     * Simple search for a suitable location to move to
     * uses scan function
     * @param blk MobileBlock
     * @return Location
     */
    @SuppressWarnings("unchecked")
    public Location localSearchAlgorithm(T blk) {

        ArrayList<Location> list = scan(blk);

            double[] store = {10000, -1};
            if (list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    double distance=util.getDistance(list.get(i).getBlockX(), list.get(i).getBlockY(), list.get(i).getBlockZ(), blk.getGoalLocation().getBlockX(), blk.getGoalLocation().getBlockY(), blk.getGoalLocation().getBlockZ());
                    if (!collision(list.get(i))) {
                        if ( distance< store[0]) {
                            store[1] = i;
                            store[0] = distance;
                        }
                    }
                }
            }
            if (store[1] != -1) {
                return util.passLocation(list.get((int) store[1]));
            }

        return null;

    }

    //========================================================================================================

    /**
     * Scans the neighbouring locations of the block
     * @param blk MobileBlock
     * @return ArrayList of locations
     */
    public ArrayList scan(T blk) {
        ArrayList<Location> list = new ArrayList<Location>();
        //gwt block positions
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


    //=======================================new movement==============================

    /**
     * Full path movement of he block till it reaches the goal
     * @param blk mobileBlock to be moved
     */
    public void moveFullPath(T blk) {

            while (!theSamelocation(blk.getLocation(), blk.getGoalLocation())) {
                blk.setPrevLoc(util.passLocation(blk.getLocation()));
                if (blk.getLocation().getBlockY()>65 && !blk.getFly() && !blk.getLinked()) {// if above ground level move down to ground
                    Location ll = blk.getLocation().clone().add(0, -1, 0);
                    if (ll.getBlock().getType() == Material.AIR) {
                        blk.getLocation().add(0, -1, 0);
                        blk.getPrevLocation().getBlock().setType(Material.AIR);
                        blk.getLocation().getBlock().setType(Material.GOLD_BLOCK);
                    }

                }else if (blk.getLocation().getBlockX() < blk.getGoalLocation().getBlockX()) {
                    blk.getLocation().add(1, 0, 0);
                } else if (blk.getLocation().getBlockX() > blk.getGoalLocation().getBlockX()) {
                    blk.getLocation().add(-1, 0, 0);
                } else if (blk.getLocation().getBlockZ() < blk.getGoalLocation().getBlockZ()) {
                    blk.getLocation().add(0, 0, 1);
                } else if (blk.getLocation().getBlockZ() > blk.getGoalLocation().getBlockZ()) {
                    blk.getLocation().add(0, 0, -1);
                }
                if (blk.getFly() || blk.getLinked()) {
                    if (blk.getLocation().getBlockY() < blk.getGoalLocation().getBlockY()) {
                        blk.getLocation().add(0, 1, 0);
                    } else if (blk.getLocation().getBlockY() > blk.getGoalLocation().getBlockY()) {
                        blk.getLocation().add(0, -1, 0);
                    }
                }
                if (!theSamelocation(blk.getLocation(), blk.getPrevLocation())) {
                    blk.getPrevLocation().getBlock().setType(Material.AIR);
                    blk.getLocation().getBlock().setType(Material.GOLD_BLOCK);
                }


            }
        }


    /**
     * move the block one step towards the goal Location
     * @param blk MobileBlock
     */
    public void stepMove(T blk,Location next) {

       {


            blk.setPrevLoc(blk.getLocation().clone());
//            if (blk.getLocation().getBlockY() > 65 && !blk.getFly()) {// if above ground level move down to ground
//                Location ll = blk.getLocation().clone().add(0, -1, 0);
//                if (ll.getBlock().getType() == Material.AIR) {
//                    blk.getLocation().add(0, -1, 0);
//                    blk.getPrevLocation().getBlock().setType(Material.AIR);
//                    blk.getLocation().getBlock().setType(Material.BRICK);
//                }

                if (next != null) {

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
                    if (blk.getFly() || blk.getLinked()) {
                        if (blk.getLocation().getBlockY() < next.getBlockY()) {
                            blk.getLocation().add(0, 1, 0);
                        } else if (blk.getLocation().getBlockY() > next.getBlockY() && blk.getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.AIR) {
                            blk.getLocation().add(0, -1, 0);
                        }
                    }

                    blk.getPrevLocation().getBlock().setType(Material.AIR);
                    blk.getLocation().getBlock().setType(Material.GOLD_BLOCK);
                    //updateGradient(blk);
                }
            //}
        }}

    //==============================================================================

    public void move(T blk, boolean fullpath) {

        if (fullpath) moveFullPath(blk);
        else stepMove(blk,localSearchAlgorithm(blk));
    }


}