package com.MC;


import com.MC.Events.Normal;
import com.MC.Events.Parallel;
import com.MC.Events.Sequential;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class Manipulator extends JavaPlugin {

    int robots;
    int time;
    int seqNum;

    @Override
    public void onEnable() {

        getLogger().info("onEnable has been invoked!");
    }

    @Override
    public void onDisable() {
        getLogger().info("disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {



        if (cmd.getName().equalsIgnoreCase("nor")) { // If the player typed /nor then do the following...
            trans(args);
            new Normal(this, robots, time);
            getLogger().info("Normal has b1een invoked!");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("seq")) {
            trans(args);
            new Sequential(this);
            getLogger().info("Sequential has been invoked!");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("par")) {
            trans(args);
            new Parallel(this);
            getLogger().info("Parallel has been invoked!");
            return true;

        } else if (cmd.getName().equalsIgnoreCase("stopit")) {
            Bukkit.getScheduler().cancelTasks(this);
            getLogger().info("Stop has been invoked!");

            return true;
        }
        return false;
    }

    public void trans(String[] args) {
        robots = Integer.parseInt(args[0]);
        time = Integer.parseInt(args[1]);
        //seqNum = Integer.parseInt(args[2]);

    }
}

