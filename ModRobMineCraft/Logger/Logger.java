package com.ModRobMineCraft.Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Created by log on 2/27/15.
 */
public class Logger {


    void Logger() {
    }

    public void log(String msg) {
        java.util.logging.Logger logger;
        logger = java.util.logging.Logger.getLogger("my log");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("/home/Minecraft/log/MCloG.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info(msg);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
