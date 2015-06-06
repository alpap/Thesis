package com.ModRobMineCraft.Behavior;

import com.ModRobMineCraft.Behavior.BehaviorHandler.BehaviorHandler;
import com.ModRobMineCraft.Block.MineCraftMobileBlock;
import com.ModRobMineCraft.Block.MobileBlock;
import com.ModRobMineCraft.Commmunication.MessageManager;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;
import com.ModRobMineCraft.Utility.Movement;
import com.ModRobMineCraft.Utility.MovementModular;

import java.io.UncheckedIOException;
import java.util.ArrayList;


public class BehaviorManager<T extends MobileBlock> {
    ArrayList<T> robots;
    BehaviorHandler<T> handle;
    MessageManager msgMan;
    int robotIdCounter;
    int count;

     /**
     * Constructor creates a robots list, a behaviour handler and a message manager
     */
    public BehaviorManager() {
        this.robots = new ArrayList<T>();

        this.msgMan = new MessageManager();
        this.handle = new BehaviorHandler<T>(this,msgMan);
        this.robotIdCounter = 0;
        this.count = robots.size();

    }

    /**
     * Constructor creates a robots list and a behaviour handler
     *
     * @param msgMan pass in a  message manager
     */
    public BehaviorManager(MessageManager msgMan) {
        this.robots = new ArrayList<T>();
        this.handle = new BehaviorHandler<T>(this,msgMan);
        this.msgMan = msgMan;
        this.count = robots.size();

    }

    /**
     * Add a robot to the behaviour manager
     *
     * @param robot robot to be added
     */
    public void addRobot(T robot) {
        robot.setId(robotIdCounter);
        robot.setMessageManager(msgMan);
        this.robots.add(robot);
        robotIdCounter++;
    }

    /**
     * Checks if th e robot with the specified id exists in the list
     *
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
     *
     * @param id robot id
     * @return robot
     */
    public T getRobot(int id) {
        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i).getId() == id) return robots.get(i);
        }
        return null;
    }

    /**
     * Returns the robot list
     *
     * @return robot list
     */
    public ArrayList getRobots() {
        return this.robots;
    }

    /**
     * Removes robot from the list
     *
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
     *
     * @return number of robots
     */
    public int numberOfRobots() {
        return robots.size();
    }

    /**
     * checks if the list has robots
     *
     * @return true or false
     */
    public boolean hasRobots() {
        if (robots.size() > 0) return true;
        else return false;
    }

    /**
     * Executes the behaviour specified in each robot
     */
    @SuppressWarnings("unchecked")
    public void execute() {


        for (int i = robots.size() - 1; i >= 0; i--) {

            handle.executeBehaviour(robots.get(i));

        }
        if (msgMan.size()>0) {
            if ((Integer) msgMan.getMessage().getValue(MessageType.ReceiverID) == 0)
                msgMan.removeMessageFromList(0);
        }
     //   if (msgMan.size()==robots.size()) msgMan.clearList();
    }

    /**
     * Executes sequentially a number of robots
     * @param number number of robots to be executed
     */
    @SuppressWarnings("unchecked")
    public void executeSequentially(int number) {
        if (this.count == 0) this.count = robots.size();
        for (int i = this.count - 1; i >= 0; i--) {

            handle.executeBehaviour(robots.get(i));
            if (i == 0) {

                if ((Integer) msgMan.getMessage().getValue(MessageType.ReceiverID) == 0)
                    msgMan.removeMessageFromList(0);
                this.count -= number;
                break;
            }
            if (i == this.count - number) {
                this.count -= number;
                break;
            }
        }


    }


    public MessageManager getMessageManager() {
        return this.msgMan;
    }

}
