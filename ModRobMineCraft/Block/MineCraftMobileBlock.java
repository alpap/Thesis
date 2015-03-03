package com.ModRobMineCraft.Block;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import com.ModRobMineCraft.Utility.Movement;
import com.ModRobMineCraft.Utility.Utility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class MineCraftMobileBlock implements MobileBlock {

    protected Location location;
    protected Location wantedLocation;
    protected Block blk;
    protected boolean fly;
    protected boolean linked;
    protected BehaviorType behavior = null;
    protected int id;
    protected boolean forceMove;
    protected ArrayList<Integer> linkedIdList;
    protected MessageManager msgManager;
    protected int msgId;


    public MineCraftMobileBlock(Location loc) {
        this.blk = loc.getBlock();
        this.location = loc;
        this.wantedLocation = loc;
        this.msgId = 0;
        this.blk.setType(Material.BRICK);
        this.forceMove = false;
        this.fly = false;
        this.linked = false;
        this.linkedIdList = new ArrayList<Integer>();
        this.behavior = BehaviorType.Stop;
    }

    public MineCraftMobileBlock(MessageManager msgMgr, Location loc, BehaviorType Behavior) {
        this.blk = loc.getBlock();
        this.location = loc;
        this.wantedLocation = loc;
        this.msgId = 0;
        this.blk.setType(Material.BRICK);
        this.forceMove = false;
        this.fly = false;
        this.linked = false;
        this.linkedIdList = new ArrayList<Integer>();
        this.msgManager = msgMgr;
        this.behavior = Behavior;
    }

    public Location getLocation() {
        return this.location;
    }

    //----------- location ---------------------------------------------
    public void setLocation(Location loc) {
        this.location = loc;
    }

    public void addToWantedLocation(int x, int y, int z) {
        this.wantedLocation.add((double) x, (double) y, (double) z);
    }

    public void setWantedLocation(int x, int y, int z) {
        this.wantedLocation = new Location(this.location.getWorld(), (double) x, (double) y, (double) z);
    }

    public Location getWantedLocation() {
        return this.wantedLocation;
    }

    //----------block--------------------------------------
    public Block getMCBlock() {
        return this.blk;
    }

    //----------- force movement-----------------------------------------------
    public boolean getForceMove() {
        return this.forceMove;
    }

    public void setForceMove(boolean fm) {
        this.forceMove = fm;
    }

    //----------- fly --------------------------------------------------
    public boolean getFly() {
        return this.fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    // ---------- id ---------------------------------------------------
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //------------- linked ---------------------------------------------
    public ArrayList linkedTo() {
        return this.linkedIdList;
    }

    public void linkTo(int id) {
        this.linkedIdList.add(id);
    }

    public boolean getLinked() {
        return this.linked;
    }

    public void setLinked(boolean linked) {
        this.linked = fly;
    }

    // ----------- move ------------------------------------------------


    public void moveBlock() { // move function

        Movement mv = new Movement();
        Location curLoc = this.blk.getLocation();
        Location prevLoc = new Location(curLoc.getWorld(), curLoc.getX(), curLoc.getY(), curLoc.getZ());

        Location finLoc = mv.move(this.blk, this.wantedLocation, this.fly, this.forceMove);

        this.blk = finLoc.getBlock();
        this.blk.setType(Material.BRICK);
        Block blkPr = prevLoc.getBlock();
        this.location.setX(finLoc.getX());
        this.location.setY(finLoc.getY());
        this.location.setZ(finLoc.getZ());
        if (!mv.checkLoc(blkPr.getLocation(), blk.getLocation())) {
            blkPr.setType(Material.AIR);
        }
    }
    //------------------- messages ------------------------
    public void setMessageManager (MessageManager msgMan){
        this.msgManager = msgMan;
    }


    public MessageManager getMessageManager(){
        return this.msgManager;
    }
    /**
     * Sends a message to other robots
     *
     * @param MessageCode message code to check for action
     * @param receiverID  id of the robot if 0 send to everyone
     * @param scope       the communication scope
     * @param speed       the speed in milliseconds for the message to arrive if it is 0 then the message is instant
     */
    public void sendMessage(int MessageCode, int receiverID, int scope, int speed) {
        Message<MessageType, Integer> msg = new Message<MessageType, Integer>();
        msg.setValue(MessageType.SenderID, id);
        msg.setValue(MessageType.PosX, (int) this.location.getX());
        msg.setValue(MessageType.PosY, (int) this.location.getY());
        msg.setValue(MessageType.PosZ, (int) this.location.getZ());
        msg.setValue(MessageType.MessageCode, MessageCode);
        msg.setValue(MessageType.CommunicationScope, scope);
        msg.setValue(MessageType.ReceiverID, receiverID);
        msg.setValue(MessageType.Speed, speed);

        this.msgManager.addMessageToList(msg, speed);
    }


    public Message receiveMessage() {
        Utility check = new Utility();
        if (msgManager.sizeOfList() == 0) return null;
        Message msg = this.msgManager.getMessage();
        if (msg.getValue(MessageType.SenderID).equals(id) || msg.getValue(MessageType.MessageID).equals(msgId))
            return null;
        if (msg.getValue(MessageType.CommunicationScope).equals(0) || msg.getValue(MessageType.Speed).equals(0)) {
            return msg;
        } else if (check.checkScope(msg, this.location.getX(), this.location.getY(), this.location.getZ())) {
            return msg;
        }
        return null;
    }

    public BehaviorType getBehavior() {
        return this.behavior;
    }

    //---------------------------behaviors---------------------
    public void setBehavior(BehaviorType Behavior) {
        this.behavior = Behavior;
    }

}