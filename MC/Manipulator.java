package com.MC;


import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MineCraftMobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Manipulator extends JavaPlugin {

    @Override
    public void onEnable() {
        new ExListener(this);
        getLogger().info("onEnable has been invoked!");


    }
}

class ExListener implements Listener {
    private final Manipulator plugin;

    public ExListener(Manipulator plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent pje) {


        new BukkitRunnable() {
            Location loc = pje.getPlayer().getLocation(); // get the player location
            Location lk = new Location(loc.getWorld(), loc.getBlockX() + 5, loc.getBlockY(), loc.getBlockZ());
            boolean apl=true;
            BehaviorManager behMan = startIt(lk);
            @Deprecated
            public void run() {

                behMan.execute();
                plugin.getServer().broadcastMessage("block set on location: " + behMan.getRobot(0).getLocation().getBlockX() +
                        " " + behMan.getRobot(0).getLocation().getBlockY()+
                        " "+behMan.getRobot(0).getLocation().getBlockZ());

            }
        }.runTaskTimer(this.plugin, 20, 20); // every sec after 1 sec
    }
    public BehaviorManager startIt(Location lk){
            MessageManager msgMan = new MessageManager(); // create a message manager
            MineCraftMobileBlock bk = new MineCraftMobileBlock(msgMan, lk, BehaviorType.Move); // or u can just pass loc
            bk.setId(0); // to set an id
            bk.setWantedLocation(lk.getBlockX() + 15, lk.getBlockY() + 12, lk.getBlockZ() + 12);
            BehaviorManager<MineCraftMobileBlock> behMan = new BehaviorManager<MineCraftMobileBlock>(msgMan);
            behMan.addRobot(bk);// automatically agings an id

        return behMan;
    }


}

