package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MobileBlock;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class A2d<T extends MobileBlock> {
    ArrayList<Location> lls;
    ArrayList<Location> llsUpdate;
    Utility util;
    BehaviorManager bh;
    int sq;
    boolean check;
    Location startLocation;
    public A2d(BehaviorManager bh){
        this.util=new Utility();
        lls=new ArrayList<Location>();
        check =true;
        this.bh=bh;
    }

    public void generateCube(){
        for (int i=this.startLocation.getBlockX(); i!=this.startLocation.getBlockX()+sq;i++){
            for (int j=this.startLocation.getBlockZ(); j!=this.startLocation.getBlockZ()+sq;j++) {
                this.lls.add(new Location(startLocation.getWorld(),i,startLocation.getBlockY(),j));
            }

        }
        llsUpdate=new ArrayList<Location>(lls);
    }

    public boolean sideCheck (Location loc) {
        double x = loc.getBlockX();
        double y = loc.getBlockY();
        double z = loc.getBlockZ();
        if (new Location (loc.getWorld(),x+1, y, z).getBlock().getType()!=(Material.AIR) &&
                new Location (loc.getWorld(),x-1, y, z).getBlock().getType()!=(Material.AIR)) return true;
        else if (new Location (loc.getWorld(),x, y, z+1).getBlock().getType()!=(Material.AIR) &&
                new Location (loc.getWorld(),x, y, z-1).getBlock().getType()!=(Material.AIR)) return true;
        return false;
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

        //check for collisions and previous locations
        for (int i = list.size()-1; i >=0; i--) {
           if (util.collision(list.get(i)) || util.theSameLocation(list.get(i), blk.getPrevLocation())) {
                if (!sideCheck(list.get(i))) list.remove(i);
            }
        }


        return list;
    }


    /**
     * Linked block local search algorithm
     * @param blk MobileBlock
     * @return next good location to move to
     */
    @SuppressWarnings("unchecked")
    public Location localSearchAlgorithm2D(T blk) {

                ArrayList<Location> list = scan(blk); // scan for locations

                if (list.size() != 0) {             // check the list size
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


        return null;

    }


    /**
     * calculate the gradient for a block
     * @param blk mobile Block
     */

    public void stepMove(T blk,Location next) {
        if (new Location(blk.getLocation().getWorld(),blk.getLocation().getBlockX(),blk.getLocation().getBlockY()-1,blk.getLocation().getBlockZ()).getBlock().getType()==Material.AIR){
            blk.setPrevLoc(util.passLocation(blk.getLocation()));
            blk.getLocation().add(0,-1,0);
            blk.getPrevLocation().getBlock().setType(Material.AIR);
            blk.getLocation().getBlock().setType(Material.GOLD_BLOCK);
        }else
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
                             blk.getPrevLocation().getBlock().setType(Material.AIR);
                blk.getLocation().getBlock().setType(Material.GOLD_BLOCK);
        }
    }

    public boolean listCheck(T blk){ //check if within shapes borders
        for (Location ll : lls) {
            if (util.theSameLocation(blk.getLocation(), blk.getGoalLocation())) {
                if (util.theSameLocation(blk.getLocation(), ll)) {
                    return true;
                }
            }
        }


        return false;
    }

    public void a2dMove(T blk) {
        if (check) {
            this.sq =(int) Math.sqrt(bh.getRobots().size());
            this.startLocation = util.passLocation(blk.getLocation(), 0, 0, 0);
            check = false;
            generateCube();
        }
        if (llsUpdate.size()>0) blk.setGoalLocation(llsUpdate.get(0));
        if (listCheck(blk)) {
            blk.setBehavior(BehaviorType.Stop);
            llsUpdate.remove(0);
        } else stepMove(blk, localSearchAlgorithm2D(blk));

    }

}
