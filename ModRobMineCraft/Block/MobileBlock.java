package com.ModRobMineCraft.Block;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Commmunication.Message.Message;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.yaml.snakeyaml.Loader;

import java.util.ArrayList;

public interface MobileBlock { //} extends Block{

    public void setLocation(Location loc);
    public void setWantedLocation(int x, int y,int z);
    public void addToWantedLocation(int x, int y,int z);
    public Location getLocation();
    public Location getWantedLocation();
    public int getId();

    //------------------ id ------------------------
    public void setId(int id);

    public boolean getForceMove();
    //------------------- block ----------------------
    public Block getMCBlock();
    //------------------ force movement ------------------------
    public void setForceMove(boolean ForceMove);

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

    public Message receiveMessage();

    //---------------------------behaviors---------------------
    public void setBehavior(BehaviorType Behavior);

    public BehaviorType getBehavior();

}
