package com.ModRobMineCraft.Behavior.BehaviorHandler;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import com.ModRobMineCraft.Utility.Utility;

public class BehaviorHandler<T extends MobileBlock> {

    public BehaviorHandler(){}

    public void randomMovement(T rob) {
        if (rob.getLocation().equals(rob.getWantedLocation())) {
            Utility utlt = new Utility();
            int[] randA = utlt.randomDir();
            rob.addToWantedLocation(randA[1], randA[2], randA[3]);
            rob.sendMessage(1,0,0,0);
        }
        else rob.moveBlock();
    }

    public void follow(T rob){
        Message msg = rob.receiveMessage();
        if (msg!=null){
            rob.setWantedLocation((Integer)msg.getValue(MessageType.PosX),(Integer)msg.getValue(MessageType.PosY),(Integer)msg.getValue(MessageType.PosZ));
            rob.moveBlock();
        }
        else rob.moveBlock();
    }

    public void stop(T rob){
    }

    public void move(T rob){
        rob.moveBlock();
    }

    public void beacon(T rob){
        rob.sendMessage(1,0,0,0);
    }


}
