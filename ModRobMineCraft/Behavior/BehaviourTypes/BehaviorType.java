package com.ModRobMineCraft.Behavior.BehaviourTypes;

public enum BehaviorType {
    Follow, // follows the message sender
    RandomMovement, // Moves randomly on the map and sends its location
    Stop,  // Does nothing
    Move, // moves to a specific location
    Beacon, // stopped but transmits its location
    MoveOnLinked // move on a robot
}
