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

    /**
     * Constructor creates a robots list, a behaviour handler and a message manager
     */
    public BehaviorManager() {
        this.robots = new LinkedList<T>();
        this.handle = new BehaviorHandler();
        this.msgMan = new MessageManager();
        this.robotIdCounter = 0;
    }

    /**
     * Constructor creates a robots list and a behaviour handler
     * @param msgMan pass in a  message manager
     */
    public BehaviorManager(MessageManager msgMan) {
        this.robots = new LinkedList<T>();
        this.handle = new BehaviorHandler();
        this.msgMan = msgMan;
    }

    /**
     * Add a robot to the behaviour manager
     * @param robot robot to be added
     */
    public void addRobot(T robot) {
        robot.setId(robotIdCounter);
        this.robots.add(robot);
        robotIdCounter++;
    }

    /**
     * Checks if th e robot with the specified id exists in the list
     * @param id robot id
     * @return true or false
     */
    public boolean robotExists(int id) {
        for (int i = 0; i == robots.size() - 1; i++) {
            if (robots.get(i).getId() == id) return true;
        }
        return false;
    }

    /**
     * Checks and returns the robot with the specified id
     * @param id robot id
     * @return robot
     */
    public T getRobot(int id) {
        for (int i = 0; i == robots.size() - 1; i++) {
            if (robots.get(i).getId() == id) return robots.get(i);
        }
        return null;
    }

    /**
     * Returns the robot list
     * @return robot list
     */
    public List getRobots() {
        return this.robots;
    }

    /**
     * Removes robot from the list
     * @param id robot id
     */
    public void removeRobot(int id) {
        for (int i = 0; i == robots.size() - 1; i++) {
            if (robots.get(i).getId() == id) robots.remove(i);
        }
    }

    /**
     * Deletes the robot list
     */
    public void deleteRobots() {
        this.robots.clear();
    }

    /**
     * Returns the number of robots
     * @return number of robots
     */
    public int numberOfRobots() {
        return robots.size();
    }

    /**
     * checks if the list has robots
     * @return true or false
     */
    public boolean hasRobots() {
        if (robots.size() > 0) return true;
        else return false;
    }

    /**
     * Executes the the movement specified in each robot
     */
    public void execute() {
        for (int i = 0; i == this.robots.size() - 1; i++) {

            handle.executeBehaviour(robots.get(i));

        }
        msgMan.removeMessageFromList(0);
    }

}
