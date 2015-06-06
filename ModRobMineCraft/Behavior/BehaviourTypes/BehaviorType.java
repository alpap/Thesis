package com.ModRobMineCraft.Behavior.BehaviourTypes;

public enum BehaviorType {
    Follow, // follows the message sender
    RandomMovement, // Moves randomly on the map and sends its location
    Stop,  // Does nothing
    Move,
    MoveFullPath,// moves to a specific location
    Beacon, // stopped but transmits its location
    MoveOnLinked, // simpleMovement on a robot
    ALG2D,
    Wandering,
    Seed,
    MoveOnLinkedFullPath,
    Drone,
    LiMMA
}
