package com.MC.Events;

import com.MC.Manipulator;
import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Utility.RobotGenerator;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by log on 3/19/15.
 */
public class Parallel implements Listener {
    private final Manipulator plugin;

    public Parallel(Manipulator plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(final PlayerJoinEvent pje) {


        new BukkitRunnable() {
            Location loc = pje.getPlayer().getLocation(); // get the player location
            Location lk = new Location(loc.getWorld(), loc.getBlockX() + 10, loc.getBlockY(), loc.getBlockZ());
            RobotGenerator gen = new RobotGenerator();
            BehaviorManager behMan = gen.generateLinkedRobots(lk, 40, BehaviorType.MoveOnLinked, false, false, true);//1048576 //  fly,  force,  linked

            @Deprecated
            public void run() {

                behMan.executeSequentially(1);

            }
        }.runTaskTimer(this.plugin, 20, 10); // after 20 ticks every 1 sec 20 ticks
    }


}


