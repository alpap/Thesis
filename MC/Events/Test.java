package com.MC.Events;

import com.MC.Manipulator;
import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Utility.RobotGenerator;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by log on 3/19/15.
 */
public class Test implements Listener {
    private final Manipulator plugin;
    int one;
    int del;
    public Test(Manipulator plugin,int one, int delete) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.one = one;
        this.del=delete;

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(final BlockPlaceEvent pje) {
        new BukkitRunnable() {
            Location loc = pje.getBlockPlaced().getLocation(); // get the player locationn
            Location lk = new Location(loc.getWorld(), loc.getBlockX() + 10, loc.getBlockY(), loc.getBlockZ());
            RobotGenerator gen = new RobotGenerator();
            BehaviorManager behMan = gen.drones(lk, one);//fly,  force,  linked
            @Deprecated
            public void run() {
                behMan.execute();

            }
        }.runTaskTimer(this.plugin, 20, del);
    }
}





