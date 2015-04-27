package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MineCraftMobileBlock;
import com.ModRobMineCraft.Block.MobileBlock;
import org.bukkit.Location;

public class RobotGenerator {

    public RobotGenerator() {
    }

    /**
     * @param loc         a minecraft location object
     * @param robotNumber number of robots to be generated must be in power of 2
     *                    The behaviour is automatically set to Stop
     */
    public BehaviorManager generateRobots(Location loc, int robotNumber) {
        Location startLocation;
        BehaviorManager<MobileBlock> bhMng = new BehaviorManager<MobileBlock>();
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
    public BehaviorManager generateRobots(Location loc, int robotNumber, BehaviorType beh, boolean fly, boolean force, boolean linked) {
        Location startLocation;
        BehaviorManager<MobileBlock> bhMng = new BehaviorManager<MobileBlock>();

        for (double i = loc.getBlockY(); i < loc.getBlockY() + Math.cbrt(robotNumber); i++) {
            for (double j = loc.getBlockZ(); j < loc.getBlockZ() + Math.cbrt(robotNumber); j++) {
                for (double k = loc.getBlockX(); k < loc.getBlockX() + Math.cbrt(robotNumber); k++) {
                    if (bhMng.numberOfRobots() >= robotNumber) break;
                    startLocation = new Location(loc.getWorld(), k, i, j);
                    MineCraftMobileBlock robot = new MineCraftMobileBlock(bhMng.getMessageManager(), startLocation, beh);
                    robot.setFly(fly);
                    robot.setForceMove(force);
                    robot.setLinked(false);
                    bhMng.addRobot(robot);

                }
            }
        }
        return bhMng;
    }

    /**
     * @param loc         a Minecraft location object
     * @param robotNumber number of robots to be generated must be in power of 2
     * @param beh         predefine a behaviour
     *                    The behaviour is automatically set to Stop
     */
    public BehaviorManager generateLinkedRobots(Location loc, int robotNumber, BehaviorType beh, boolean fly, boolean force, boolean linked) {
        Location startLocation;
        BehaviorManager<MobileBlock> bhMng = new BehaviorManager<MobileBlock>();

        for (double i = loc.getBlockY(); i < loc.getBlockY() + Math.cbrt(robotNumber); i++) {
            for (double j = loc.getBlockZ(); j < loc.getBlockZ() + Math.cbrt(robotNumber); j++) {
                for (double k = loc.getBlockX(); k < loc.getBlockX() + Math.cbrt(robotNumber); k++) {
                    if (bhMng.numberOfRobots() >= robotNumber) break;
                    startLocation = new Location(loc.getWorld(), k, i, j);
                    MineCraftMobileBlock robot = new MineCraftMobileBlock(bhMng.getMessageManager(), startLocation, beh);
                    robot.setWantedLocation(loc.clone().add(0,0,100));
                    robot.setFly(fly);
                    robot.setForceMove(force);
                    robot.setLinked(linked);
                    bhMng.addRobot(robot);

                }
            }
        }
        return bhMng;
    }


    public BehaviorManager inline(Location loc, int robotNumber) {
        Location startLocation;
        BehaviorManager<MineCraftMobileBlock> bhMng = new BehaviorManager<MineCraftMobileBlock>();
        for (double i = loc.getBlockZ(); i < loc.getBlockZ() + robotNumber; i += 1) {

            startLocation = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), i);
            bhMng.addRobot(new MineCraftMobileBlock(startLocation));


        }
        return bhMng;
    }


    public BehaviorManager becon(Location loc, int robotNumber, boolean fly, boolean fm, BehaviorType bh) {
        Location startLocation;
        BehaviorManager<MineCraftMobileBlock> bhMng = new BehaviorManager<MineCraftMobileBlock>();
        for (double i = loc.getBlockZ(); i < loc.getBlockZ() + robotNumber; i++) {

            startLocation = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), i);
            MineCraftMobileBlock mbk = new MineCraftMobileBlock(bhMng.getMessageManager(), startLocation, bh);
            mbk.setFly(fly);
            mbk.setForceMove(fm);
            bhMng.addRobot(mbk);


        }
        return bhMng;
    }

    public BehaviorManager moveAndFollow(Location loc, boolean fly, boolean fm) {
        Location startLocation;
        BehaviorManager<MineCraftMobileBlock> bhMng = new BehaviorManager<MineCraftMobileBlock>();


        startLocation = new Location(loc.getWorld(), loc.getBlockX() + 30, loc.getBlockY(), loc.getBlockZ());
        MineCraftMobileBlock mbk = new MineCraftMobileBlock(bhMng.getMessageManager(), startLocation, BehaviorType.RandomMovement);
        mbk.setFly(fly);
        mbk.setForceMove(fm);
        bhMng.addRobot(mbk);

        startLocation = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        MineCraftMobileBlock mbk2 = new MineCraftMobileBlock(bhMng.getMessageManager(), startLocation, BehaviorType.Follow);
        mbk2.setFly(fly);
        mbk2.setForceMove(fm);
        bhMng.addRobot(mbk2);


        return bhMng;
    }


}
