package com.ModRobMineCraft.Behavior.BehaviorHandler;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import com.ModRobMineCraft.Utility.*;


@SuppressWarnings("unchecked")
public class BehaviorHandler<T extends MobileBlock> {
    BehaviorManager<T> behMan;
    A2d<T> l2d ;
    A3d<T> l3d ;
    LiM<T> lim ;
    LinkedMovement<T> lm;
    MessageManager msgm;
    Movement<T> mv = new Movement<T>();
    Utility utlt = new Utility();
    //MovementModular<T> mvm= new MovementModular<T>();
    int counterForGradient;

    public BehaviorHandler(BehaviorManager behaviourManager,MessageManager msgm) {
        this.behMan =behaviourManager;
        this.msgm=msgm;
        this.l2d =new A2d<T>(behMan);
        this.l3d =new A3d<T>(4,msgm,behMan);
        this.lm=new LinkedMovement<T>(behMan);
        this.lim= new LiM<T>(behMan);
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
        //if (rob.getBehavior().equals(BehaviorType.MoveFullPath)) moveFullPath(rob);//improvedMove(rob);
        if (rob.getBehavior().equals(BehaviorType.Beacon)) beacon(rob);
        if (rob.getBehavior().equals(BehaviorType.MoveOnLinked)) moveOnLinked(rob);
        if (rob.getBehavior().equals(BehaviorType.ALG2D)) a2d(rob);
        if (rob.getBehavior().equals(BehaviorType.Seed)) seed(rob);
        if (rob.getBehavior().equals(BehaviorType.Wandering)) wandering(rob);
        if (rob.getBehavior().equals(BehaviorType.Drone)) drone(rob);
        if (rob.getBehavior().equals(BehaviorType.LiMMA)) limma(rob);


    }



    /**
     * randomly moves the robot and sends a location message
     *
     * @param rob robot to be moved
     */
    public void randomMovement(T rob) {


        if (rob.getLocation().getBlockX() == rob.getGoalLocation().getBlockX() &&
                rob.getLocation().getBlockZ() == rob.getGoalLocation().getBlockZ()) {
            int[] randA = utlt.randomDir();
            rob.addToGoalLocation(randA[0], randA[1], randA[2]);


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
            rob.setGoalLocation((Integer) msg.getValue(MessageType.PosX), (Integer) msg.getValue(MessageType.PosY), (Integer) msg.getValue(MessageType.PosZ));
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
        rob.sendMessage(1, 0, 0);
    }

    /**
     * move on other linked robots
     *
     * @param rob robot to move
     */
    @SuppressWarnings("unchecked")
    public void moveOnLinked(T rob) {


        lm.moveOnLinked(rob);

    }

    public void a2d(T rob){
        l2d.a2dMove(rob);

    }

    public void seed(T rob){
        l3d.move(rob);
    }

    public void wandering(T rob){
        l3d.move(rob);
    }

    public void drone(T rob){
        if (rob.getLocation().getBlockX() == rob.getGoalLocation().getBlockX() &&
                rob.getLocation().getBlockZ() == rob.getGoalLocation().getBlockZ()) {
            int[] randA = utlt.randomDir();
            rob.addToGoalLocation(randA[0], randA[1], randA[2]);
        } else {
           // mv.move(rob, 4);
           // pathList.add(rob.getLocation());
        }

    }
    public void limma(T rob){
        lim.move(rob);
    }

}

