package com.MC;


import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Behavior.RobotGenerator;
import com.ModRobMineCraft.Block.MineCraftMobileBlock;
import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
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
            Location lk = new Location(loc.getWorld(), loc.getBlockX() + 100, loc.getBlockY(), loc.getBlockZ());
            RobotGenerator gen= new RobotGenerator();
            BehaviorManager<MineCraftMobileBlock> behMan= gen.generateRobotsMC(lk,131072,BehaviorType.RandomMovement,true, true);//1048576
            //BehaviorManager<MineCraftMobileBlock> behMan= gen.becon(lk,1,true,true);
            //BehaviorManager<MineCraftMobileBlock> behMan= gen.moveAndFollow(lk,false,false);
            @Deprecated
            public void run() {


                    behMan.execute();
                    //Message msgsss= behMan.getRobot(1).receiveMessage();


            }
        }.runTaskTimer(this.plugin, 5, 5); // every sec after 1 sec 20 20
    }



}

