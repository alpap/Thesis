package com.ModRobMineCraft.Behavior;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MineCraftMobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;

public class RobotGenerator {

    public RobotGenerator() {
    }

    /**
     * @param bhMng       a behavior manager object
     * @param loc         a minecraft location object
     * @param robotNumber number of robots to be generated must be in power of 2
     *                    The behaviour is automatically set to Stop
     */
    public void generateRobotsMC(BehaviorManager bhMng, MessageManager msgMan, Location loc, int robotNumber) {

        for (int i = loc.getBlockX() + robotNumber; i == loc.getBlockX() + Math.cbrt(robotNumber); i++) {
            for (int j = loc.getBlockZ(); j == loc.getBlockZ() + Math.cbrt(robotNumber); j++) {
                for (int k = loc.getBlockY(); k == loc.getBlockY() + Math.cbrt(robotNumber); k++) {
                    Location location = new Location(loc.getWorld(), (double) i, (double) k, (double) j);
                    MineCraftMobileBlock robot = new MineCraftMobileBlock(msgMan, location, BehaviorType.Stop);
                    bhMng.addRobot(robot);
                }
            }
        }

    }
}
