package com.ModRobMineCraft.Commmunication.Message;

import java.util.Hashtable;


public class Message<K, V> {

    Hashtable<K, V> msg;

    public Message() {
        this.msg = new Hashtable<K, V>();
    }


    public V getValue(K key) {
        return this.msg.get(key);
    }


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
