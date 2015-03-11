package com.MC;


import com.ModRobMineCraft.Behavior.BehaviorHandler.BehaviorHandler;
import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MineCraftMobileBlock;
import com.ModRobMineCraft.Examples.tests.mobileBlockForTesting;
import com.ModRobMineCraft.Examples.tests.robGenForTesting;
import com.ModRobMineCraft.Examples.tests.testBehHandler;
import com.ModRobMineCraft.Utility.RobotGenerator;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;


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
            Location lk = new Location(loc.getWorld(), loc.getBlockX() + 10, loc.getBlockY(), loc.getBlockZ());

            RobotGenerator gen= new RobotGenerator();
            BehaviorManager<MineCraftMobileBlock> behMan= gen.generateRobotsMC(lk,16,BehaviorType.RandomMovement,true, true);//1048576
            //BehaviorManager<MineCraftMobileBlock> behMan= gen.becon(lk,1,true,true);
            //BehaviorManager<MineCraftMobileBlock> behMan= gen.moveAndFollow(lk,false,false);

            //=============================testing limits=========================================================
            //testBehHandler<mobileBlockForTesting> bhandler= new testBehHandler<mobileBlockForTesting>();
            //robGenForTesting rgt=new robGenForTesting();
            //ArrayList<mobileBlockForTesting> ls =rgt.generatorForTesting(lk,1048576,true,true, BehaviorType.RandomMovement);
            //======================================================================================================
            @Deprecated
            public void run() {

//                for (int i=0; i<ls.size();i++){
//                    bhandler.executeBehaviour(ls.get(i));
//                }

                    behMan.executeSequentially(4);
                    //Message msgsss= behMan.getRobot(1).receiveMessage();


            }
        }.runTaskTimer(this.plugin, 5, 5); // every sec after 1 sec 20 20
    }



}

