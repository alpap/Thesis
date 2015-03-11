package com.ModRobMineCraft.Examples;

import com.ModRobMineCraft.Utility.RobotGenerator;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class Multiple_robot_generator_minecraft_example {

    public void multiGenerate(Block blk) {
        Location loc = new Location(blk.getWorld(), blk.getX(), blk.getY(), blk.getZ());
        RobotGenerator mc = new RobotGenerator();
        mc.generateRobotsMC(loc, 16);
    }
}
