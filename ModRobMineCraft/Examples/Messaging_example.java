package com.ModRobMineCraft.Examples;


import com.ModRobMineCraft.Commmunication.Message.Message;
import com.ModRobMineCraft.Commmunication.MessageManager;
import com.ModRobMineCraft.Commmunication.MessageTypes.MessageType;

public class Messaging_example {

    public Messaging_example() {

    }

    public void generate_message() {
        // create a message manager
        MessageManager msgMan = new MessageManager();
        // create a message with preferred parameters
        Message<MessageType, Integer> msg = new Message<MessageType, Integer>();
        // populate the message
        msg.setValue(MessageType.PosX, 1);
        msg.setValue(MessageType.PosY, 2);
        msg.setValue(MessageType.PosZ, 3);
        msg.setValue(MessageType.CommunicationScope, 4);
        //The message id is automatically generated in the MessageManager class
        //msg.setValue(MessageType.MessageID,random);
        msg.setValue(MessageType.ReceiverID, 6);
        msg.setValue(MessageType.MessageCode, 7);
        msg.setValue(MessageType.Speed, 8);
        msg.setValue(MessageType.SenderID, 9);
        // add the message to the manager with preferred delay if necessary
        msgMan.addMessageToList(msg, 0);
        //get the message
        Message<MessageType, Integer> msgs = msgMan.getMessage();
        // get the hashtable
        System.out.println(msgs.getEntireMessage());
        // get the specific value
        System.out.println(msgs.getValue(MessageType.PosX));
        System.out.println(msgs.getValue(MessageType.PosY));
        System.out.println(msgs.getValue(MessageType.PosZ));
        System.out.println(msgs.getValue(MessageType.CommunicationScope));
        System.out.println(msgs.getValue(MessageType.MessageID));
        System.out.println(msgs.getValue(MessageType.ReceiverID));
        System.out.println(msgs.getValue(MessageType.MessageCode));
        System.out.println(msgs.getValue(MessageType.Speed));
        System.out.println(msgs.getValue(MessageType.SenderID));
    }

}
