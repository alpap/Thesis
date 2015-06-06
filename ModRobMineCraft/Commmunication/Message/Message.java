package com.ModRobMineCraft.Commmunication.Message;

import java.util.Hashtable;


public class Message<K, V> {

    Hashtable<K, V> msg;

    public Message() {
        this.msg = new Hashtable<K, V>();
    }
    @SuppressWarnings("unchecked")
    public Message(Message<K, V> msgs) {

        this.msg = new Hashtable<K, V>();
        msg = msgs.getEntireMessage();
    }


    public V getValue(K key) {
        if (msg.containsKey(key)) {
            return this.msg.get(key);
        }else return null;
    }

    @SuppressWarnings("unchecked")
    public Hashtable getEntireMessage() {
        return this.msg;
    }


    public void setValue(K key, V message) {
        this.msg.put(key, message);
    }


    public String toString() {
        return this.msg.toString();
    }
}
