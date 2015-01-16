package com.ModRobMineCraft.Behavior;

import com.ModRobMineCraft.Behavior.BehaviorHandler.BehaviorHandler;
import com.ModRobMineCraft.Behavior.BehaviourTypes.BehaviorType;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import java.util.LinkedList;
import java.util.List;

public class BehaviorManager<T extends MobileBlock> {
    List<T> robots;
    BehaviorHandler<T> handle;
    MessageManager msgMan;

    public BehaviorManager(){
        this.robots= new LinkedList<T>();
        this.handle= new BehaviorHandler();
        this.msgMan= new MessageManager();
    }

    public void addRobot(T robot){
        this.robots.add(robot);
    }

//    public void addRobors(int num){
//        for (int i=0; i==num-1;i++){
//            T newRobot = new T(this.msgMan,loc,i,BehaviorType.Stop);
//        }
//
//    }

    public T getRobot(int id){
        return this.robots.get(id);
    }

    public List getRobots(){return this.robots;}

    public void removeRobot(int id){this.robots.remove(id);}

    public void deleteRobots() { this.robots.clear();}

    public int numberOfRobots(){ return robots.size();}

    public boolean hasRobots(){
        if (robots.size()>0) return true;
        else return false;
    }

    public void execute(){
        T rob;
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
