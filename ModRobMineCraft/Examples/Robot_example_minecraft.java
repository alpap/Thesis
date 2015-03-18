package com.ModRobMineCraft.Examples;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MineCraftMobileBlock;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import com.ModRobMineCraft.Utility.Movement;
import org.bukkit.Location;


public class Robot_example_minecraft {

    public Robot_example_minecraft() {
    }

    public void Robot_test(Location loc) {
        // For single robot(block)
        Movement<MobileBlock> mv = new Movement<MobileBlock>();
        MessageManager msgMan = new MessageManager(); // create a message manager
        Location lk = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        MineCraftMobileBlock blk = new MineCraftMobileBlock(msgMan, lk, BehaviorType.Stop); // or u can just pass loc
        blk.setId(0); // to set an id
        blk.setWantedLocation(12, 12, 12); // set where the block is heading
//        mv.move(loc.getBlock(),false);

        // for multiple robots
        BehaviorManager<MineCraftMobileBlock> behMan = new BehaviorManager<MineCraftMobileBlock>(msgMan);
        behMan.addRobot(blk);// automatically agings an id
        behMan.execute(); // to execute the behaviours of all robots

    }
}
