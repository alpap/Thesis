package com.ModRobMineCraft.Examples;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.RobotGenerator;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class Multiple_robot_generator_minecraft_example {

    public void multiGenerate(Block blk) {
        Location loc = new Location(blk.getWorld(), blk.getX(), blk.getY(), blk.getZ());
        MessageManager msgMan = new MessageManager();
        RobotGenerator mc = new RobotGenerator();
        BehaviorManager<MobileBlock> bhMan = new BehaviorManager<MobileBlock>(msgMan);
        mc.generateRobotsMC(bhMan, msgMan, loc, 16);
    }
}
