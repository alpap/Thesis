package com.ModRobMineCraft.Behavior;

import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MineCraftMobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;

public class RobotGenerator {

    public RobotGenerator() {
    }

    /**
     * @param loc         a minecraft location object
     * @param robotNumber number of robots to be generated must be in power of 2
     *                    The behaviour is automatically set to Stop
     */
    public BehaviorManager generateRobotsMC(Location loc, int robotNumber) {
        Location startLocation;
        BehaviorManager<MineCraftMobileBlock> bhMng =new BehaviorManager<MineCraftMobileBlock>();
        for (double i = loc.getBlockY(); i < loc.getBlockY() + Math.cbrt(robotNumber); i++) {
            for (double j = loc.getBlockZ(); j < loc.getBlockZ() + Math.cbrt(robotNumber); j++) {
                for (double k = loc.getBlockX(); k < loc.getBlockX() + Math.cbrt(robotNumber); k++) {
                    startLocation = new Location(loc.getWorld(), j, i, k);

                    bhMng.addRobot(new MineCraftMobileBlock(startLocation));
                }
            }
        }
        return bhMng;
    }

    /**
     * @param loc         a minecraft location object
     * @param robotNumber number of robots to be generated must be in power of 2
     * @param beh         predefine a behaviour
     *                    The behaviour is automatically set to Stop
     */
    public BehaviorManager generateRobotsMC(Location loc, int robotNumber, BehaviorType beh) {
        Location startLocation;
        BehaviorManager<MineCraftMobileBlock> bhMng =new BehaviorManager<MineCraftMobileBlock>();
        for (double i = loc.getBlockY(); i < loc.getBlockY() + Math.cbrt(robotNumber); i++) {
            for (double j = loc.getBlockZ(); j < loc.getBlockZ() + Math.cbrt(robotNumber); j++) {
                for (double k = loc.getBlockX(); k < loc.getBlockX() + Math.cbrt(robotNumber); k++) {
                    startLocation = new Location(loc.getWorld(), k, i, j);
                    MineCraftMobileBlock robot = new MineCraftMobileBlock(bhMng.getMessageManager(),startLocation, beh);
                    bhMng.addRobot(robot);
                }
            }
        }
        return bhMng;
    }


    public BehaviorManager inline(Location loc, int robotNumber) {
        Location startLocation;
        BehaviorManager<MineCraftMobileBlock> bhMng =new BehaviorManager<MineCraftMobileBlock>();
        for (double i = loc.getBlockZ(); i < loc.getBlockZ() + robotNumber; i+=1) {

                    startLocation = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), i);
                    bhMng.addRobot(new MineCraftMobileBlock(startLocation));


        }
        return bhMng;
    }

}
