package com.ModRobMineCraft.Block;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;
import org.bukkit.block.Block;

 public interface MobileBlock { //} extends Block{

     void setGradient(int gr);

     int getGradient();

     void setGoalLocation(int x, int y, int z);

     void addToGoalLocation(int x, int y, int z);

     Location getLocation();

     void setLocation(Location loc);

     Location getGoalLocation();

     void setGoalLocation(Location loc);

     int getId();

    //------------------ id ------------------------
     void setId(int id);

     Location getPrevLocation();

     void setPrevLoc(Location loc);

     boolean getForceMove();

    //------------------ force movement ------------------------
     void setForceMove(boolean ForceMove);

    //------------------- block ----------------------

    boolean getFly();

    //------------------ fly ------------------------
    void setFly(boolean fly);

    //------------------ linked ------------------------

     boolean getLinked();

     void setLinked(boolean linked);

    //------------------ simpleMovement ------------------------

    // void moveBlock();

    //------------------ messages ------------------------
     void sendMessage(int MessageCode, int deliveryID, int scope);

     MessageManager getMessageManager();

     void setMessageManager(MessageManager msgMan);

     Message receiveMessage();

     BehaviorType getBehavior();

    //---------------------------behaviors---------------------
     void setBehavior(BehaviorType Behavior);

}
