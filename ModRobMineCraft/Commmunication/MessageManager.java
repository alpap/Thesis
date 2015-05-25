package com.ModRobMineCraft.Commmunication;


import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * Created by LoG on 11/19/2014.
 */
public class MessageManager {

    ArrayList<Message> memory;

    /**
     * creates an empty list.
     */
    public MessageManager() {
        memory = new ArrayList<Message>(100);
    }

    /**
     * add message to list with a random message ID.
     */
    public void sendMessage(Message table) {

        table.setValue(MessageType.MessageID, randomNum());
        memory.add(table);
        System.out.println("message not entered");
    }

    /**
     * Delete message specific location in the list.
     */
    public void removeMessageFromList(int index) {
        this.memory.remove(index);
    }

    /**
     * Get message without removing from list.
     */
    public Message getMessage() {
        if (memory.size() == 0) {
            return null;
        }
        return this.memory.get(0);
    }

    /**
     * Get message and remove from list.
     */
    public Message getMessageAndDelete() {
        Message msg;
        if (memory.size() == 0) {
            msg = null;
        } else msg = this.memory.get(0);

        return msg;

    }

    /**
     * Delete the hole list.
     */
    public void clearList() {
        this.memory.clear();
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return this.memory.size();
    }

    /**
     * Generates a a random number
     *
     * @return a random number between 0 and 10000
     */
    public int randomNum() {
        Random r = new Random();
        return r.nextInt(10000);
    }


}
