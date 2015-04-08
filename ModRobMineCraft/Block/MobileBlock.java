package com.ModRobMineCraft.Block;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;
import org.bukkit.block.Block;

public interface MobileBlock { //} extends Block{

    public void setGradient(int gr);

    public int getGradient();

    public void setWantedLocation(int x, int y, int z);

    public void addToWantedLocation(int x, int y, int z);

    public Location getLocation();

    public void setLocation(Location loc);

    public Location getWantedLocation();

    public void setWantedLocation(Location loc);

    public int getId();

    //------------------ id ------------------------
    public void setId(int id);

    public Location getPrevLocation();

    public void setPrevLoc(Location loc);

    public boolean getForceMove();

    //------------------ force movement ------------------------
    public void setForceMove(boolean ForceMove);

    //------------------- block ----------------------
    public Block getMCBlock();

    public boolean getFly();

    //------------------ fly ------------------------
    public void setFly(boolean fly);

    //------------------ linked ------------------------

    public boolean getLinked();

    public void setLinked(boolean linked);

    //------------------ simpleMovement ------------------------

    //public void moveBlock();

    //------------------ messages ------------------------
    public void sendMessage(int MessageCode, int deliveryID, int scope, int speed);

    public MessageManager getMessageManager();

    public void setMessageManager(MessageManager msgMan);

    public Message receiveMessage();

    public BehaviorType getBehavior();

    //---------------------------behaviors---------------------
    public void setBehavior(BehaviorType Behavior);

}
