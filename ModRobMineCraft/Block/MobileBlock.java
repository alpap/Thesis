package com.ModRobMineCraft.Block;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;

public interface MobileBlock { //} extends Block{

    public void setWantedLocation(int x, int y, int z);

    public void addToWantedLocation(int x, int y, int z);

    public Location getLocation();

    public void setLocation(Location loc);

    public Location getWantedLocation();

    public int getId();

    //------------------ id ------------------------
    public void setId(int id);

    public boolean getForceMove();

    //------------------ force movement ------------------------
    public void setForceMove(boolean ForceMove);

    //------------------- block ----------------------
    public Block getMCBlock();

    public boolean getFly();

    //------------------ fly ------------------------
    public void setFly(boolean fly);

    //------------------ linked ------------------------
    public ArrayList linkedTo();

    public void linkTo(int id);

    public boolean getLinked();

    public void setLinked(boolean linked);

    //------------------ move ------------------------

    public void moveBlock();

    //------------------ messages ------------------------
    public void sendMessage(int MessageCode, int deliveryID, int scope, int speed);

    public void setMessageManager(MessageManager msgMan);

    public MessageManager getMessageManager();

    public Message receiveMessage();

    public BehaviorType getBehavior();

    //---------------------------behaviors---------------------
    public void setBehavior(BehaviorType Behavior);

}
