package com.MC.Events;

import com.MC.Manipulator;
import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Utility.RobotGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Mmm implements Listener {
    private final Manipulator plugin;
    int robots;
    int time;
    /**
     * @param plg        pass the plugin name or just this
     * @param robotNum      number of robots to be generated
     * @param executionTime input the execution ticks 20 ticks are 1 sec
     */
    public Mmm(Manipulator plg, int robotNum, int executionTime) {
        this.robots = robotNum;
        this.time = executionTime;
        this.plugin = plg;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(final BlockPlaceEvent pje) {

        new BukkitRunnable() {
            Location loc = pje.getBlockPlaced().getLocation(); // get the player locationn
            Location lk = new Location(loc.getWorld(), loc.getBlockX() + 10, loc.getBlockY(), loc.getBlockZ());
            RobotGenerator gen = new RobotGenerator();
            BehaviorManager behMan = gen.generateMMMRobots(lk, robots, BehaviorType.MoveOnLinked);//fly,  force,  linked

            @Deprecated
            public void run() {
                behMan.execute();
            }
        }.runTaskTimer(this.plugin, 20, this.time); // after 20 ticks every 1 sec 20 ticks
    }


}


