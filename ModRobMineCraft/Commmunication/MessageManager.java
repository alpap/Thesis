package com.ModRobMineCraft.Commmunication;


import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * Created by LoG on 11/19/2014.
 */
public class MessageManager {

    ArrayBlockingQueue<Message> memory;

    /**
     * creates an empty list.
     */
    public MessageManager() {
        memory = new ArrayBlockingQueue<Message>(100);
    }

    /**
     * add message to list with a random message ID.
     */
    public void addMessageToList(Message table, long milliseconds) {

        table.setValue(MessageType.MessageID, randomNum());
        if (milliseconds > 0) {
            try {
                memory.offer(table, milliseconds, TimeUnit.MILLISECONDS);
            } catch (InterruptedException m) {
                System.out.println("message not entered");
            }
        } else memory.offer(table);
    }

    /**
     * Delete message specific location in the list.
     */
    public void removeMessageFromList(int itr) {
        if (this.memory.size() > 0) {
            this.memory.remove(itr);
        }
    }

    /**
     * Get message without removing from list.
     */
    public Message getMessage() {
        if (memory.size() == 0) {
            return null;
        }
        return this.memory.peek();
    }

    /**
     * Get message and remove from list.
     */
    public Message getMessageAndDelete() {
        if (memory.size() == 0) {
            return null;
        }
        try {
            return this.memory.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete the hole list.
     */
    public void deleteList() {
        this.memory.clear();
    }

    /**
     * Returns the size of the list.
     */
    public int sizeOfList() {
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
