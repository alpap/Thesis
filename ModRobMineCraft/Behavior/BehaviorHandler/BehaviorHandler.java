package com.ModRobMineCraft.Behavior.BehaviorHandler;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import com.ModRobMineCraft.Utility.Utility;

public class BehaviorHandler<T extends MobileBlock> {

    public BehaviorHandler() {
    }


    /**
     * Checks and executes the behaviour for a robot
     * @param rob robot to be checked
     */
    public void executeBehaviour(T rob){
        if (rob.getBehavior().equals(BehaviorType.RandomMovement)) randomMovement(rob);
        if (rob.getBehavior().equals(BehaviorType.Follow)) follow(rob);
        if (rob.getBehavior().equals(BehaviorType.Stop)) stop(rob);
        if (rob.getBehavior().equals(BehaviorType.Move)) move(rob);
        if (rob.getBehavior().equals(BehaviorType.Beacon)) beacon(rob);
        if (rob.getBehavior().equals(BehaviorType.MoveOnLinked)) moveOnLinked(rob);


    }

    /**
     * randomly moves the robot and sends a location message
     * @param rob robot to be moved
     */
    public void randomMovement(T rob) {
        if (rob.getLocation().equals(rob.getWantedLocation())) {
            Utility utlt = new Utility();
            int[] randA = utlt.randomDir();
            rob.addToWantedLocation(randA[1], randA[2], randA[3]);
            rob.sendMessage(1, 0, 0, 0);
        } else rob.moveBlock();
    }

    /**
     * Follows the robot that is transmitting a location
     * @param rob robot to be moved to the received location
     */
    public void follow(T rob) {
        Message msg = rob.receiveMessage();
        if (msg != null) {
            rob.setWantedLocation((Integer) msg.getValue(MessageType.PosX), (Integer) msg.getValue(MessageType.PosY), (Integer) msg.getValue(MessageType.PosZ));
            rob.moveBlock();
        } else rob.moveBlock();
    }

    /**
     * Stops the robot
     * @param rob robot to be stopped
     */
    public void stop(T rob) {
    }

    /**
     * Moves the robot from the current location to the desired location
     * @param rob robot to be moved
     */
    public void move(T rob) {
        rob.moveBlock();
    }

    public void beacon(T rob) {
        rob.sendMessage(1, 0, 0, 0);
    }

    public void moveOnLinked(T rob) {

    }

}
