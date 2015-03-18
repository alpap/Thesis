package com.ModRobMineCraft.Examples.tests;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Utility.Utility;

/**
 * Created by log on 3/9/15.
 */
public class testBehHandler<T extends mobileBlockForTesting> {

    public testBehHandler() {
    }


    /**
     * Checks and executes the behaviour for a robot
     *
     * @param rob robot to be checked
     */
    public void executeBehaviour(T rob) {
        if (rob.getBehavior().equals(BehaviorType.RandomMovement)) randomMovement(rob);
        if (rob.getBehavior().equals(BehaviorType.Stop)) stop(rob);
        if (rob.getBehavior().equals(BehaviorType.Move)) move(rob);


    }

    /**
     * randomly moves the robot and sends a location message
     *
     * @param rob robot to be moved
     */
    public void randomMovement(T rob) {
        if (rob.getLocation().getBlockX() == rob.getWantedLocation().getBlockX() &&
                rob.getLocation().getBlockZ() == rob.getWantedLocation().getBlockZ()) {
            Utility utlt = new Utility();
            int[] randA = utlt.randomDir();
            rob.addToWantedLocation(randA[0], randA[1], randA[2]);

        } else {

            //rob.moveBlock();
        }
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
        //rob.moveBlock();
    }
}