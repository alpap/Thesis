package com.ModRobMineCraft.Examples;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Behavior.RobotGenerator;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class Multiple_robot_generator_minecraft_example {

    public void multiGenerate(Block blk) {
        Location loc = new Location(blk.getWorld(), blk.getX(), blk.getY(), blk.getZ());
        RobotGenerator mc = new RobotGenerator();
        mc.generateRobotsMC(loc, 16);
    }
}
