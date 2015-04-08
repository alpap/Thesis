package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Block.MobileBlock;
import org.bukkit.Location;
import org.bukkit.Material;


public class MovementRules {

    public MovementRules(){}

    public boolean checkRules(MobileBlock blk ,Location next){
        Location curloc=blk.getLocation().clone();
        Location nextloc=next.clone();

        if (blk.getForceMove()){
            if (nextloc.getBlock().getType()!= Material.BRICK){
                return true;
            }
        }
        if (blk.getFly()){
            if (curloc.getBlockY()+1==nextloc.getBlockY() ||
                    curloc.getBlockY()-1 ==nextloc.getBlockY()){
              return true;
            }
        }
        if (blk.getLinked()){
            if (nextloc.getBlockX() == blk.getLocation().getBlockX() + 1 || nextloc.getBlockX() == blk.getLocation().getBlockX() - 1) {
                if (isRobot(nextloc.clone().add(0, 1, 0))) return true;
                else if (isRobot(nextloc.clone().add(0, -1, 0))) return true;
                else if (isRobot(nextloc.clone().add(0, 0, 1))) return true;
                else if (isRobot(nextloc.clone().add(0, 0, -1))) return true;

            } else if (nextloc.getBlockY() == blk.getLocation().getBlockY() + 1 || nextloc.getBlockY() == blk.getLocation().getBlockY() - 1) {

                if (isRobot(nextloc.clone().add(1, 0, 0))) return true;
                else if (isRobot(nextloc.clone().add(-1, 0, 0))) return true;
                else if (isRobot(nextloc.clone().add(0, 0, 1))) return true;
                else if (isRobot(nextloc.clone().add(0, 0, -1))) return true;
            } else if (nextloc.getBlockZ() == blk.getLocation().getBlockZ() + 1 || nextloc.getBlockZ() == blk.getLocation().getBlockZ() - 1) {

                if (isRobot(nextloc.clone().add(1, 0, 0))) return true;
                else if (isRobot(nextloc.clone().add(-1, 0, 0))) return true;
                else if (isRobot(nextloc.clone().add(0, 1, 0))) return true;
                else if (isRobot(nextloc.clone().add(0, -1, 0))) return true;
            }
        }

        return false;
    }

    public boolean isRobot(org.bukkit.Location loc) {
        if (loc.getBlock().getType().equals(Material.BRICK)) return true;
        return false;
    }
}
