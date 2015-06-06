package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Random;

public class Utility {
  
    public Utility() {
    }

    /**
     * Checks if the receiver is in the message scope
     *
     * @param msg Message object
     * @return true if within the scope range else false
     */
    public boolean checkScope(Message<MessageType, Integer> msg, int X, int Y, int Z) {
        int x = msg.getValue(MessageType.PosX);
        int y = msg.getValue(MessageType.PosY);
        int z = msg.getValue(MessageType.PosZ);
        int scope = msg.getValue(MessageType.CommunicationScope);
        double dis = Math.sqrt(Math.pow((x - X), 2) + Math.pow((y - Y), 2) + Math.pow((z - Z), 2));
        return dis <= scope;
    }

    public boolean checkScope(Message<MessageType,Integer> msg, double X, double Y, double Z) {
        int x = msg.getValue(MessageType.PosX);
        int y = msg.getValue(MessageType.PosY);
        int z = msg.getValue(MessageType.PosZ);
        int scope = msg.getValue(MessageType.CommunicationScope);
        double dis = Math.sqrt(Math.pow((x - X), 2) + Math.pow((y - Y), 2) + Math.pow((z - Z), 2));
        return dis <= scope;
    }

    public double getDistance(int x, int y, int z, int X, int Y, int Z) {
        return Math.sqrt(Math.pow((x - X), 2) + Math.pow((y - Y), 2) + Math.pow((z - Z), 2));
    }

    public int getDistance(Location a, Location b) {
        double dis = Math.sqrt(Math.pow((a.getBlockX() - b.getBlockX()), 2) + Math.pow((a.getBlockY() - b.getBlockY()), 2) + Math.pow((a.getBlockZ() - b.getBlockZ()), 2));
        return (int)dis;
    }
    public double getDistanceDouble(Location a, Location b) {
        return Math.sqrt(Math.pow((a.getBlockX() - b.getBlockX()), 2) + Math.pow((a.getBlockY() - b.getBlockY()), 2) + Math.pow((a.getBlockZ() - b.getBlockZ()), 2));
    }
    public double getDistance(double x, double y, double z, double X, double Y, double Z) {
        return Math.sqrt(Math.pow((x - X), 2) + Math.pow((y - Y), 2) + Math.pow((z - Z), 2));
    }
    public int getDistance2d(int x, int y,int x2,int y2) {
        double dis = Math.sqrt(Math.pow((x - x2), 2) + Math.pow((y - y2), 2));
        return (int)dis;
    }

    /**
     * @return a random int form -100 to 100
     */
    public int[] randomDir() {
        Random r = new Random();
        return new int[]{rNeg(r.nextInt(100)), rNeg(r.nextInt(100)), rNeg(r.nextInt(100))};
    }

    public int[] randomDirClose() {
        Random r = new Random();
        return new int[]{rNeg(r.nextInt(2)), rNeg(r.nextInt(2)), rNeg(r.nextInt(2))};
    }

    public int rNeg(int num) {
        Random r = new Random();
        if (r.nextInt(9) >= 4) return num * (-1);
        else return num;
    }

    /**
     * check if a location is occupied by something
     * @param loc Location object
     * @return true of false
     */
    public boolean collision(org.bukkit.Location loc) {
        return !loc.getBlock().getType().equals(Material.AIR);
    }

    /**
     * check if a robot is at the current location by default the robots are of type GOLD_BLOCK
     * @param loc Location object
     * @return true of false
     */
    public boolean isRobot(org.bukkit.Location loc) {
        return loc.getBlock().getType().equals(Material.GOLD_BLOCK);
    }

    /**
     * Check if the two locations are the same
     * @param oldLoc old Location object
     * @param newLoc new Location object
     * @return true of false
     */
    public boolean theSameLocation(org.bukkit.Location oldLoc, org.bukkit.Location newLoc) {
        return oldLoc.getBlockX() == newLoc.getBlockX() && oldLoc.getBlockY() == newLoc.getBlockY() && oldLoc.getBlockZ() == newLoc.getBlockZ();
    }

    public Location passLocation(Location loc){

        return new Location(loc.getWorld(),loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
    }

    public Location passLocation(Location loc,int x,int y,int z){

        return new Location(loc.getWorld(),loc.getBlockX()+x,loc.getBlockY()+y,loc.getBlockZ()+z);
    }

    public Location passLocation(MobileBlock blk){

        return new Location(blk.getLocation().getWorld(),blk.getLocation().getBlockX(),blk.getLocation().getBlockY(),blk.getLocation().getBlockZ());
    }
    public Location passLocation(MobileBlock blk,int x, int y ,int z){

        return new Location(blk.getLocation().getWorld(),blk.getLocation().getBlockX()+x,blk.getLocation().getBlockY()+y,blk.getLocation().getBlockZ()+z);
    }




}
