package com.MC;


import com.ModRobMineCraft.Behavior.BehaviorManager;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Utility.RobotGenerator;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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

    @Override
    public void onDisable() {
        getLogger().info("disabled!");
    }
}

class ExListener implements Listener {
    private final Manipulator plugin;

    public ExListener(Manipulator plugin) {

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
            //BehaviorManager<MineCraftMobileBlock> behMan= gen.becon(lk,1,true,true);
            //BehaviorManager<MineCraftMobileBlock> behMan= gen.moveAndFollow(lk,false,false);

            //=============================testing limits=========================================================
//            testBehHandler<mobileBlockForTesting> bhandler= new testBehHandler<mobileBlockForTesting>();
//            robGenForTesting rgt=new robGenForTesting();
//            ArrayList<mobileBlockForTesting> ls =rgt.generatorForTesting(lk,1000000,true,true, BehaviorType.RandomMovement);
            //======================================================================================================
            @Deprecated
            public void run() {

//                for (int i=0; i<ls.size();i++){
//                    bhandler.executeBehaviour(ls.get(i));
//                }
                //behMan.execute();
                behMan.executeSequentially(1);
                //Message msgsss= behMan.getRobot(1).receiveMessage();


            }
        }.runTaskTimer(this.plugin, 20, 10); // after 20 ticks every 1 sec 20 ticks
    }


}

