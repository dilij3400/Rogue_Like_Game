package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * An impenetrable barrier.
 * 
 * @author ram
 *
 */
public class Fence extends Ground {

	public Fence() {
		super(DisplayChar.FENCE.toChar());
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
