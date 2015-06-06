package com.MC;


import com.MC.Events.*;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class Manipulator extends JavaPlugin {

    int robots;
    int time;


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

        if (cmd.getName().equalsIgnoreCase("mmm")) { // If the player typed /nor then do the following...
            trans(args);
            new Mmm(this, robots, time);
            getLogger().info("MMM has b1een invoked!");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("2d")) {
            trans(args);
            new Alg2dShape(this, robots, time);
            getLogger().info("Alg 2D Shape has been invoked!");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("3d")) {
            trans(args);
            new Alg3dShape(this, robots, time);
            getLogger().info("Alg 3D Shape has been invoked!");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("limma")) {
            trans(args);
            new LiMMA(this, robots, time);
            getLogger().info("LiMMA has been invoked!");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("test")) {
            trans(args);
            new Test(this, robots, time);
            getLogger().info("test has been invoked!");
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
    }
}

