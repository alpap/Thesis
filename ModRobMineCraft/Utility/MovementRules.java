package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Block.MobileBlock;
import org.bukkit.Location;
import org.bukkit.Material;


public class MovementRules {

    public MovementRules(){}


    /**
     * Returns an integer of value:
     * 1 = forcemove enabled
     * 10 = fly enabled
     * 100 = linked enabled
     * 11 = forcemove and fly
     * 111 = all rules apply
     *
     * @param blk Mobile block
     * @param next next location
     * @return int from 1 to 111
     */
    public int checkRules(MobileBlock blk ,Location next){
        Location curloc=blk.getLocation().clone();
        Location nextloc=next.clone();
        int check = 0;

        if (blk.getForceMove()){
            if (nextloc.getBlock().getType()!= Material.BRICK){
                check++;
            }
        }
        if (blk.getFly()){
            if (curloc.getBlockY()+1==nextloc.getBlockY() ||
                    curloc.getBlockY()-1 ==nextloc.getBlockY()){
              check+=10;
            }
        }
        if (blk.getLinked()){
            if (nextloc.getBlockX() == blk.getLocation().getBlockX() + 1 || nextloc.getBlockX() == blk.getLocation().getBlockX() - 1) {
                if (isRobot(nextloc.clone().add(0, 1, 0))) check+=100 ;
                else if (isRobot(nextloc.clone().add(0, -1, 0))) check+=100;
                else if (isRobot(nextloc.clone().add(0, 0, 1))) check+=100;
                else if (isRobot(nextloc.clone().add(0, 0, -1))) check+=100;

            } else if (nextloc.getBlockY() == blk.getLocation().getBlockY() + 1 || nextloc.getBlockY() == blk.getLocation().getBlockY() - 1) {

                if (isRobot(nextloc.clone().add(1, 0, 0))) check+=100;
                else if (isRobot(nextloc.clone().add(-1, 0, 0))) check+=100;
                else if (isRobot(nextloc.clone().add(0, 0, 1))) check+=100;
                else if (isRobot(nextloc.clone().add(0, 0, -1))) check+=100;
            } else if (nextloc.getBlockZ() == blk.getLocation().getBlockZ() + 1 || nextloc.getBlockZ() == blk.getLocation().getBlockZ() - 1) {

                if (isRobot(nextloc.clone().add(1, 0, 0))) check+=100;
                else if (isRobot(nextloc.clone().add(-1, 0, 0))) check+=100;
                else if (isRobot(nextloc.clone().add(0, 1, 0))) check+=100;
                else if (isRobot(nextloc.clone().add(0, -1, 0))) check+=100;
            }
        }

        if (check >111) check=111;
        return check;
    }

    public boolean isRobot(org.bukkit.Location loc) {
        if (loc.getBlock().getType().equals(Material.BRICK)) return true;
        return false;
    }
}
