package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

import static com.ModRobMineCraft.Commmunication.MessageTypes.MessageType.*;

public class A3d<T extends MobileBlock> {
    ArrayList<Location> llst;
    ArrayList<Location> llst2;
    MessageManager msgman;
    Utility util;
    int msgScope;
    boolean init;
    BehaviorManager<T> bh;
    Location zeroloc;
    int counter;
    @SuppressWarnings("unchecked")
    public A3d(int scope,MessageManager msgm,BehaviorManager behman){

        this.util=new Utility();
        this.llst=new ArrayList<Location>();
        this.llst2=new ArrayList<Location>();
        this.msgScope=scope;
        this.msgman=msgm;
        this.init=true;
        this.zeroloc=null;
        this.bh=behman;
        this.counter=0;
    }

    public void generateShape(Location loc2){
        Location loc=new Location(loc2.getWorld(),loc2.getBlockX(),loc2.getBlockY(),loc2.getBlockZ());

//        for (int i = loc.getBlockY();i <= loc.getBlockY()+69;i++ ){
//            this.llst.add(util.passLocation(loc, 0, i, 0));
//        }
//        for (int i = loc.getBlockX();i <= loc.getBlockX()+100;i++ ) {
//            for (int j = loc.getBlockZ(); j <= loc.getBlockZ() + 100; j++) {
//                llst.add(util.passLocation(loc, i, 70, j));
//            }
//        }
//        for (int i = loc.getBlockY()+71;i <= loc.getBlockY()+170;i++ ) {
//            for (int j = loc.getBlockZ()+100; j <= loc.getBlockZ() ; j--) {
//                llst.add(util.passLocation(loc, 100, i, j));
//            }
//        }
//        for (int i = loc.getBlockY()+69;i <= loc.getBlockY();i-- ){
//            llst.add(util.passLocation(loc,100, i,0));
//        }
//        for (int i = loc.getBlockY()+69;i <= loc.getBlockY();i-- ){
//            llst.add(util.passLocation(loc, 0, i,100));
//        }
//        for (int i = loc.getBlockY()+69;i <= loc.getBlockY();i-- ){
//            llst.add(util.passLocation(loc, 100, i,100));
//        }

        for (int i = loc.getBlockY();i <= loc.getBlockY()+10;i++ ){
            this.llst.add(util.passLocation(loc, 0, i, 0));
        }

//        for(int i =0; i<bh.numberOfRobots();i++){
//            bh.getRobot(i).setGoalLocation(llst.get(0));
//        }
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
        for (int i = list.size()-1; i >=0; i--) {
            if (util.collision(list.get(i)) || util.theSameLocation(list.get(i), blk.getPrevLocation())) {
                list.remove(i);
            }
        }
        return list;
    }

      public boolean sideCheckForBot(T blk,Location loc){
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
            else if (util.isRobot(new Location(blk.getLocation().getWorld(), x, y -1,z))) isBot = true;
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

        ArrayList<Location> list = scan(blk); // scan for locations
        if (list.size() >= 0) {             // check the list size
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
        return null;
    }

    public void stepMove(T blk,Location next) {
        if (util.passLocation(blk.getLocation(),0,1,0).getBlock().getType()!=Material.GOLD_BLOCK) {
            if (next != null) {
                blk.setPrevLoc(util.passLocation(blk.getLocation()));
                //Location newLoc=util.passLocation(blk.getGoalLocation());
                if (blk.getLocation().getBlockX() < next.getBlockX()) {
                    blk.getLocation().add(1, 0, 0);
                } else if (blk.getLocation().getBlockX() > next.getBlockX()) {
                    blk.getLocation().add(-1, 0, 0);
                } else if (blk.getLocation().getBlockZ() < next.getBlockZ()) {
                    blk.getLocation().add(0, 0, 1);
                } else if (blk.getLocation().getBlockZ() > next.getBlockZ()) {
                    blk.getLocation().add(0, 0, -1);
                }
                if (blk.getLocation().getBlockY() < next.getBlockY()) {
                    blk.getLocation().add(0, 1, 0);
                } else if (blk.getLocation().getBlockY() > next.getBlockY()) {
                    blk.getLocation().add(0, -1, 0);
                }
                blk.getPrevLocation().getBlock().setType(Material.AIR);
                blk.getLocation().getBlock().setType(Material.GOLD_BLOCK);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public boolean receiveMessageFromSeed(T blk){

        if (msgman.hasMessages()) {
            Message msg=blk.receiveMessage();
            blk.setGradient(((Integer) msg.getValue(MessageCode)) - 1);
            blk.sendMessage(blk.getGradient(), 0, this.msgScope);
            blk.setGoalLocation(new Location(blk.getLocation().getWorld(), (Integer) msg.getValue(PosX), (Integer) msg.getValue(PosY), (Integer) msg.getValue(PosZ)));
            return true;
        }else return false;
    }

    public void checkGrid(T blk){
        for (Location aLlst : llst) {
            if (blk.getLocation() == aLlst) blk.setBehavior(BehaviorType.Seed);
        }
    }

    public void checkPosition(T blk){
        boolean gridEnable=false;
        if (blk.getLocation().getBlockX()==blk.getGoalLocation().getBlockX()+1&&blk.getLocation().getBlockX()==blk.getGoalLocation().getBlockX()-1){
            gridEnable=true;
        }else if (blk.getLocation().getBlockY()==blk.getGoalLocation().getBlockY()+1&&blk.getLocation().getBlockY()==blk.getGoalLocation().getBlockY()-1){
            gridEnable=true;
        }else if (blk.getLocation().getBlockZ()==blk.getGoalLocation().getBlockZ()+1&&blk.getLocation().getBlockZ()==blk.getGoalLocation().getBlockZ()-1){
            gridEnable=true;
        }
        if (gridEnable) checkGrid(blk);

    }

    @SuppressWarnings("unchecked")
    public void move(T blk) {
        if (init) {
            generateShape(util.passLocation(blk.getLocation()));
            init = false;
            blk.setBehavior(BehaviorType.Seed);
        }
        if (blk.getBehavior().equals(BehaviorType.Wandering)) {
            receiveMessageFromSeed(blk);
            stepMove(blk, localSearchAlgorithmLinked(blk));
            checkPosition(blk);

        } else if (blk.getBehavior().equals(BehaviorType.Seed)) {
            blk.sendMessage(msgScope, 0, msgScope);
        }
    }
}
