package com.ModRobMineCraft.Utility;

import org.bukkit.Material;


public class Movement {

    public Movement() {
    }

    public org.bukkit.Location move(org.bukkit.block.Block blk, org.bukkit.Location wanted, boolean fly, boolean forcemove) {

        org.bukkit.Location curLoc = blk.getLocation();
        org.bukkit.Location curLoc2 = new org.bukkit.Location(curLoc.getWorld(), curLoc.getX(), curLoc.getY(), curLoc.getZ());
        org.bukkit.Location curLoc3 = new org.bukkit.Location(curLoc.getWorld(), curLoc.getX(), curLoc.getY(), curLoc.getZ());
        org.bukkit.Location curLoc1 = new org.bukkit.Location(curLoc.getWorld(), curLoc.getX(), curLoc.getY(), curLoc.getZ());
        org.bukkit.Location finalLoc = new org.bukkit.Location(curLoc.getWorld(), curLoc.getX(), curLoc.getY(), curLoc.getZ());
        Utility util = new Utility();


        if (fly) {
            if (curLoc1.getX() < wanted.getX()) {
                curLoc1.setX(curLoc1.getX() + 1);
            } else if (curLoc1.getX() > wanted.getX()) {
                curLoc1.setX(curLoc1.getX() - 1);
            }
            if (curLoc2.getZ() < wanted.getZ()) {
                curLoc2.setZ(curLoc2.getZ() + 1);
            } else if (curLoc2.getZ() > wanted.getZ()) {
                curLoc2.setZ(curLoc2.getZ() - 1);
            }
            if (curLoc3.getY() < wanted.getY()) {
                curLoc3.setY(curLoc3.getY() + 1);
            } else if (curLoc3.getY() > wanted.getY()) {
                curLoc3.setY(curLoc3.getY() - 1);
            }

        }else{
            if (curLoc1.getX() < wanted.getX()) {
                curLoc1.setX(curLoc1.getX() + 1);
            } else if (curLoc1.getX() > wanted.getX()) {
                curLoc1.setX(curLoc1.getX() - 1);
            }
            if (curLoc2.getZ() < wanted.getZ()) {
                curLoc2.setZ(curLoc2.getZ() + 1);
            } else if (curLoc2.getZ() > wanted.getZ()) {
                curLoc2.setZ(curLoc2.getZ() - 1);
            }


        }

        double distanceX = util.getDistance(curLoc1.getX(), curLoc1.getY(), curLoc1.getZ(), wanted.getX(), wanted.getY(), wanted.getZ());
        double distanceZ = util.getDistance(curLoc2.getX(), curLoc2.getY(), curLoc2.getZ(), wanted.getX(), wanted.getY(), wanted.getZ());
        double distanceY = util.getDistance(curLoc3.getX(), curLoc3.getY(), curLoc3.getZ(), wanted.getX(), wanted.getY(), wanted.getZ());

        if (distanceX <= distanceZ && distanceX <= distanceY)
            //finalLoc = new org.bukkit.Location(curLoc1.getWorld(), curLoc1.getX(), curLoc1.getY(), curLoc1.getZ());
            finalLoc = curLoc1;
        else if (distanceZ <= distanceY && distanceZ <= distanceX)
            //finalLoc = new org.bukkit.Location(curLoc2.getWorld(), curLoc2.getX(), curLoc2.getY(), curLoc2.getZ());
            finalLoc=curLoc2;
        else if (distanceY <= distanceZ && distanceY <= distanceX && fly)
            //finalLoc = new org.bukkit.Location(curLoc3.getWorld(), curLoc3.getX(), curLoc3.getY(), curLoc3.getZ());
            finalLoc=curLoc3;

        if (forcemove) return finalLoc;
        else if (!collision(finalLoc))
            return finalLoc;
        else if (distanceX <= distanceZ && distanceX <= distanceY && !collision(curLoc1))
            return curLoc1;
        else if (distanceZ <= distanceY && distanceZ <= distanceX && !collision(curLoc2))
            return curLoc2;
        else if (distanceY <= distanceZ && distanceY <= distanceX && !collision(curLoc3))
            return curLoc3;
        else
            return curLoc;


    }

    public org.bukkit.Location moveFullPath(org.bukkit.block.Block blk, org.bukkit.Location wanted, boolean fly, boolean forcemove){


        return null; //loc;
    }

    public boolean collision(org.bukkit.Location loc) {
        if (loc.getBlock().getType().equals(Material.AIR)) return false;
        return true;
    }

    public boolean checkLoc(org.bukkit.Location oldLoc, org.bukkit.Location newLoc) {
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


}
