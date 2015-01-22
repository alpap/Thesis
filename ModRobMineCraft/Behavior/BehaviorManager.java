package com.ModRobMineCraft.Behavior;

import com.ModRobMineCraft.Behavior.BehaviorHandler.BehaviorHandler;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BehaviorManager<T extends MobileBlock> {
    List<T> robots;
    BehaviorHandler<T> handle;
    MessageManager msgMan;
    int robotIdCounter;
    public BehaviorManager(){
        this.robots= new LinkedList<T>();
        this.handle= new BehaviorHandler();
        this.msgMan= new MessageManager();
        this.robotIdCounter=0;
    }

    public BehaviorManager(MessageManager msgMan){
        this.robots= new LinkedList<T>();
        this.handle= new BehaviorHandler();
        this.msgMan= msgMan;
    }

    public void addRobot(T robot){
        robot.setId(robotIdCounter);
        this.robots.add(robot);
        robotIdCounter++;
    }

//    public void addMultipleRobots(T block){
//
//    }

    public boolean robotExists(int id){
        for (int i=0; i==robots.size()-1;i++){
            if (robots.get(i).getId()==id) return true;
        }
        return false;
    }

    public T getRobot(int id){
        for (int i=0; i==robots.size()-1;i++){
            if (robots.get(i).getId()==id) return robots.get(i);
        }
        return null;
    }

    public List getRobots(){return this.robots;}

    public void removeRobot(int id){
        for (int i=0; i==robots.size()-1;i++){
            if (robots.get(i).getId()==id) robots.remove(i);
        }
    }

    public void deleteRobots() { this.robots.clear();}

    public int numberOfRobots(){ return robots.size();}

    public boolean hasRobots(){
        if (robots.size()>0) return true;
        else return false;
    }

    public void execute(){
        T rob;
        ListIterator<T> iter=robots.listIterator();
        for (int i=0; i==this.robots.size()-1; i++){

            rob = robots.get(i);
            BehaviorType behaviour = rob.getBehavior();

            if(behaviour==BehaviorType.RandomMovement){
                handle.randomMovement(rob);
            }
            if(behaviour==BehaviorType.Follow){
                handle.follow(rob);
            }
            if(behaviour==BehaviorType.Stop){
                handle.stop(rob);
            }
        }
        msgMan.removeMessageFromList(0);
    }

}
