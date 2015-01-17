package com.ModRobMineCraft.Utility;

import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import org.bukkit.Location;

import java.util.Random;

/**
 * Created by LoG on 1/12/2015.
 */
public class Utility {
    public Utility(){}
    /**
     * Checks if the receiver is in the message scope
     *
     * @param msg Message object
     * @return true if within the scope range else false
     */
    public boolean checkScope(Message msg, int X, int Y , int Z){
        int x = (Integer) msg.getValue(MessageType.PosX);
        int y = (Integer) msg.getValue(MessageType.PosY);
        int z = (Integer) msg.getValue(MessageType.PosZ);
        int scope = (Integer) msg.getValue(MessageType.CommunicationScope);
        double dis = Math.sqrt(Math.pow((x - X), 2) + Math.pow((y - Y), 2) + Math.pow((z - Z), 2));
        if (dis <= scope) return true;
        else return false;
    }

    public boolean checkScope(Message msg, double X, double Y , double Z){
        int x = (Integer) msg.getValue(MessageType.PosX);
        int y = (Integer) msg.getValue(MessageType.PosY);
        int z = (Integer) msg.getValue(MessageType.PosZ);
        int scope = (Integer) msg.getValue(MessageType.CommunicationScope);
        double dis = Math.sqrt(Math.pow((x - X), 2) + Math.pow((y - Y), 2) + Math.pow((z - Z), 2));
        if (dis <= scope) return true;

        else return false;
    }

    public double getDistance(int x,int y,int z,int X,int Y,int Z){
        double dis = Math.sqrt(Math.pow((x - X), 2) + Math.pow((y - Y), 2) + Math.pow((z - Z), 2));
        return dis;
    }
    public double getDistance(double x,double y,double z,double X,double Y,double Z){
        double dis = Math.sqrt(Math.pow((x - X), 2) + Math.pow((y - Y), 2) + Math.pow((z - Z), 2));
        return dis;
    }

    /**
     *
     * @return a random int form 0 to 100
     */
    public int[] randomDir() {
        Random r = new Random();
        int[] rand={rNeg(r.nextInt(100)),rNeg(r.nextInt(100)), rNeg(r.nextInt(100))};
        return rand;
    }

    public int rNeg(int num){
        Random r = new Random();
        if (r.nextInt(9)>=4) return num*(-1);
        else return num;
    }

}
