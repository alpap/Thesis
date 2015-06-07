package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;


public class LiM<T extends MobileBlock> {



    int maxGradient;
    Utility util;
    BehaviorManager<T> bhm;
    boolean init;
    Location zeroblock;
    int dir;
    public LiM(BehaviorManager<T> bht) {

        this.maxGradient = -1;
        this.util = new Utility();
        this.bhm = bht;
        this.init = true;
        this.zeroblock=null;
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

    @SuppressWarnings("unchecked")
    public Location localSearchAlgorithmLinked(T blk) {


            ArrayList<Location> list = scan(blk); // scan for locations
            if (list.size() > 0) {             // check the list size
                for (int i = list.size() - 1; i >= 0; i--) {   // traverse through the list
                    if (!sideCheckForBot(blk, list.get(i))) list.remove(i); // if no robot neighbors delete list
                }
                double[] store = {10000, -1};
                for (int i = list.size() - 1; i >= 0; i--) {
                    double distance = util.getDistance(list.get(i).getBlockX(), list.get(i).getBlockY(), list.get(i).getBlockZ(), blk.getTempLoc().getBlockX(), blk.getTempLoc().getBlockY(), blk.getTempLoc().getBlockZ());
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


    public void calculateDirection(Location loc){
        if (zeroblock.getBlockX()==loc.getBlockX())
            this.dir =1; //change on z axis
        else this.dir= 0; //change on x axis
    }

    public void updateGradient(T blk) {
        int grad;
        if (this.dir == 1){
            grad=blk.getLocation().getBlockZ()-zeroblock.getBlockZ();
            if (grad<0) grad*=-1;
            blk.setGradient(grad);
        }else if (this.dir==0){
            grad=blk.getLocation().getBlockX()-zeroblock.getBlockX();
            if (grad<0) grad*=-1;
            blk.setGradient(grad);
        }

    }
    @SuppressWarnings("unchecked")
    public void receiveMsg(T blk){

        if (bhm.getMessageManager().hasMessages()){
            Message<MessageType,Integer> msg=blk.receiveMessage();
            int abl;
            if (blk.getLocation().getBlockX()==msg.getValue(MessageType.PosX)&&
                    blk.getLocation().getBlockY()==msg.getValue(MessageType.PosY)){
                if (blk.getGoalLocation().getBlockZ()>blk.getLocation().getBlockZ())abl=1;
                else abl=-1;
                blk.setTempLoc(new Location(blk.getLocation().getWorld(),
                        msg.getValue(MessageType.PosX),msg.getValue(MessageType.PosY),msg.getValue(MessageType.PosZ)+abl));
                blk.getMessageManager().removeMessageFromList(0);

            }else if(blk.getLocation().getBlockZ()==msg.getValue(MessageType.PosZ)&&
                    blk.getLocation().getBlockY()==msg.getValue(MessageType.PosY)){
                if (blk.getGoalLocation().getBlockX()>blk.getLocation().getBlockX())abl=1;
                else abl=-1;
                blk.setTempLoc(new Location(blk.getLocation().getWorld(),
                        msg.getValue(MessageType.PosX)+abl,msg.getValue(MessageType.PosY),msg.getValue(MessageType.PosZ)));
                blk.getMessageManager().removeMessageFromList(0);
            }
        }
    }

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
        for (T aList : list) {
            updateGradient(aList);
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
       // if (!util.isRobot(util.passLocation(blk.getLocation(), 0, 1, 0))) {
            if (next != null) {
               // if (blk.getGradient() >= this.maxGradient - gradient) {
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
               // }
            }
        //}
    }

    @SuppressWarnings("unchecked")
    public void move(T blk) {
        if (init){
            calculateDirection(blk.getLocation());
            calculateGradient(bhm.getRobots());
            init=false;
        }
        if(blk.getMessageManager().hasMessages()) {
            receiveMsg(blk);
        }
        if (!util.theSameLocation(blk.getLocation(),blk.getTempLoc())) {
            stepMove(blk);
        }

    }


}
//check rob gen