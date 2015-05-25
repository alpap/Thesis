package com.ModRobMineCraft.Block;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import com.ModRobMineCraft.Utility.Utility;
import org.bukkit.Location;
import org.bukkit.Material;

public class MineCraftMobileBlock implements MobileBlock {

    protected Location location;
    protected Location goalLocation;
    protected boolean fly;
    protected boolean linked;
    protected BehaviorType behavior = null;
    protected int id;
    protected boolean forceMove;
    protected MessageManager msgManager;
    protected int messageId;
    protected Location prevLoc;
    public int gradient;


    public MineCraftMobileBlock(Location loc) {
        this.location = loc;
        this.goalLocation = loc.clone();
        this.prevLoc = loc;
        this.messageId = 0;
        this.location.getBlock().setType(Material.BRICK);
        this.forceMove = false;
        this.fly = false;
        this.linked = false;
        this.behavior = BehaviorType.Stop;
        this.gradient=-1;
    }

    public MineCraftMobileBlock(MessageManager msgMgr, Location loc, BehaviorType Behavior) {
        this.location = loc;
        this.prevLoc = loc;
        this.goalLocation = loc.clone();
        this.messageId = 0;
        this.location.getBlock().setType(Material.BRICK);
        this.forceMove = false;
        this.fly = false;
        this.linked = false;
        this.msgManager = msgMgr;
        this.behavior = Behavior;
        this.gradient=-1;
    }

    public int getGradient(){return this.gradient;}

    public void setGradient(int gradient ){this.gradient=gradient;}

    public Location getLocation() {
        return this.location;
    }

    //----------- location ---------------------------------------------
    public void setLocation(Location loc) {
        this.location = loc;
    }

    public void addToGoalLocation(int x, int y, int z) {
        this.goalLocation.add(x, y, z);
    }

    public void setGoalLocation(int x, int y, int z) {
        this.goalLocation = new Location(this.location.getWorld(), (double) x, (double) y, (double) z);
    }

    public Location getPrevLocation() {
        return this.prevLoc;
    }

    public void setPrevLoc(Location loc) {
        this.prevLoc = loc;
    }

    public Location getGoalLocation() {
        return this.goalLocation;
    }

    public void setGoalLocation(Location loc) {
        this.goalLocation = loc.clone();
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

    public boolean getLinked() {
        return this.linked;
    }

    public void setLinked(boolean linked) {
        this.linked = linked;
    }

    // ----------- simpleMovement ------------------------------------------------

    public MessageManager getMessageManager() {
        return this.msgManager;
    }

    //------------------- messages ------------------------
    public void setMessageManager(MessageManager msgMan) {
        this.msgManager = msgMan;
    }

    /**
     * Sends a message to other robots
     *
     * @param MessageCode message code to check for action
     * @param receiverID  id of the robot if 0 send to everyone
     * @param scope       the communication scope
     */
    public void sendMessage(int MessageCode, int receiverID, int scope) {
        Message<MessageType, Integer> msg = new Message<MessageType, Integer>();
        msg.setValue(MessageType.SenderID, id);
        msg.setValue(MessageType.PosX, (int) this.location.getBlockX());
        msg.setValue(MessageType.PosY, (int) this.location.getBlockY());
        msg.setValue(MessageType.PosZ, (int) this.location.getBlockZ());
        msg.setValue(MessageType.MessageCode, MessageCode);
        msg.setValue(MessageType.CommunicationScope, scope);
        msg.setValue(MessageType.ReceiverID, receiverID);


        this.msgManager.sendMessage(msg);
    }


    public Message receiveMessage() {
        Utility check = new Utility();
        if (msgManager.size() == 0) return null;// if there is no message
        Message msg = this.msgManager.getMessage();
        if (msg.getValue(MessageType.SenderID).equals(id) || msg.getValue(MessageType.MessageID).equals(messageId)) // if the sender id is the same as the receiver or if the message code is the same
            return null;
        if (msg.getValue(MessageType.CommunicationScope).equals(0)) {// if instant messaging is on
            return msg;
        } else if (check.checkScope(msg, this.location.getX(), this.location.getY(), this.location.getZ())) {// if instant communication is off
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