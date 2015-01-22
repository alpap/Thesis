package com.ModRobMineCraft;

import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.RobotGenerator;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;

public class Main {

    public static void main(String[] args)  {
        Location loc=new Location(blk.getWorld,blk.getX(),blk.getY(),blk.getZ());
        MessageManager msgMan= new MessageManager();
        RobotGenerator mc=new RobotGenerator();
        BehaviorManager<MobileBlock> bhMan=new BehaviorManager<MobileBlock>(msgMan);
        mc.generateRobotsMC(bhMan, msgMan,loc,16);

    }
}
