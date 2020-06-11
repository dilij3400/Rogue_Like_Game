package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;

/**
 * This interface provides the ability to add methods to Action, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ActionInterface {
	default void setTarget(Actor target) {} 
}
