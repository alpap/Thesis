package com.ModRobMineCraft.Examples.tests;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import org.bukkit.Location;

import java.util.ArrayList;

/**
 * Created by log on 3/9/15.
 */
public class robGenForTesting {

    public robGenForTesting() {

    }

    public ArrayList generatorForTesting(Location loc, int robotNumber, boolean fly, boolean force, BehaviorType beh) {
        Location startLocation;
        ArrayList<mobileBlockForTesting> ls = new ArrayList<mobileBlockForTesting>();
        for (double i = loc.getBlockY(); i < loc.getBlockY() + Math.cbrt(robotNumber); i++) {
            for (double j = loc.getBlockZ(); j < loc.getBlockZ() + Math.cbrt(robotNumber); j++) {
                for (double k = loc.getBlockX(); k < loc.getBlockX() + Math.cbrt(robotNumber); k++) {
                    startLocation = new Location(loc.getWorld(), k, i, j);
                    mobileBlockForTesting robot = new mobileBlockForTesting(startLocation);
                    robot.setFly(fly);
                    robot.setForceMove(force);
                    robot.setBehavior(beh);
                    ls.add(robot);
                }
            }
        }
        return ls;
    }
}


