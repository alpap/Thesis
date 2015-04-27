package com.ModRobMineCraft.Behavior.BehaviorHandler;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import com.ModRobMineCraft.Utility.Movement;
import com.ModRobMineCraft.Utility.MovementModular;
import com.ModRobMineCraft.Utility.Utility;

public class BehaviorHandler<T extends MobileBlock> {
    final int division=20;
    BehaviorManager<T> behMan;
    Movement<T> mv = new Movement<T>();
    Utility utlt = new Utility();
    MovementModular<T> mvm= new MovementModular<T>();
    int counterForGradient;
    public BehaviorHandler(BehaviorManager behaviourManager) {
        this.behMan =behaviourManager;
        this.counterForGradient=(int)Math.floor(behaviourManager.getRobots().size()/division);
    }

    /**
     * Checks and executes the behaviour for a robot
     *
     * @param rob robot to be checked
     */
    public void executeBehaviour(T rob) {
        if (rob.getBehavior().equals(BehaviorType.RandomMovement)) randomMovement(rob);
        if (rob.getBehavior().equals(BehaviorType.Follow)) follow(rob);
        if (rob.getBehavior().equals(BehaviorType.Stop)) stop(rob);
        if (rob.getBehavior().equals(BehaviorType.Move)) move(rob);
        if (rob.getBehavior().equals(BehaviorType.MoveFullPath)) moveFullPath(rob);//improvedMove(rob);
        if (rob.getBehavior().equals(BehaviorType.Beacon)) beacon(rob);
        if (rob.getBehavior().equals(BehaviorType.MoveOnLinked)) moveOnLinked(rob);
       // if (rob.getBehavior().equals(BehaviorType.MoveOnLinkedFullPath)) moveOnLinkedFullPath(rob);


    }

    private void moveFullPath(T rob) {
        mv.move(rob, true);
    }

    /**
     * randomly moves the robot and sends a location message
     *
     * @param rob robot to be moved
     */
    public void randomMovement(T rob) {


        if (rob.getLocation().getBlockX() == rob.getWantedLocation().getBlockX() &&
                rob.getLocation().getBlockZ() == rob.getWantedLocation().getBlockZ()) {
            int[] randA = utlt.randomDir();
            rob.addToWantedLocation(randA[0], randA[1], randA[2]);
            // rob.sendMessage(1, 0, 0, 0);

        } else {

            mv.move(rob, false);
        }
    }

    /**
     * Follows the robot that is transmitting a location
     *
     * @param rob robot to be moved to the received location
     */

    public void follow(T rob) {
        Message msg = rob.receiveMessage();
        if (msg != null) {
            rob.setWantedLocation((Integer) msg.getValue(MessageType.PosX), (Integer) msg.getValue(MessageType.PosY), (Integer) msg.getValue(MessageType.PosZ));
            mv.move(rob, false);
        } else mv.move(rob, false);
    }

    /**
     * Stops the robot
     *
     * @param rob robot to be stopped
     */
    public void stop(T rob) {
    }

    /**
     * Moves the robot from the current location to the desired location
     *
     * @param rob robot to be moved
     */
    public void move(T rob) {
        mv.move(rob, false);
    }


    /**
     * Sends a message with its location
     *
     * @param rob robot to transmit
     */
    public void beacon(T rob) {
        rob.sendMessage(1, 0, 0, 0);
    }

    /**
     * move on other linked robots
     *
     * @param rob robot to move
     */
    public void moveOnLinked(T rob) {
        if(this.counterForGradient==Math.floor(behMan.getRobots().size()/division)){
            mvm.calculateGradient(behMan.getRobots());
            this.counterForGradient=0;
        }
       // this.counterForGradient++;

        mvm.moveOnLinked(rob);
    }

    }

