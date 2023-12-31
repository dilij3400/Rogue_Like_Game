package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
/**
 * Class represents a Corpse.
 *
 */
public class Corpse extends PortableItem {
	
	private int turns = 0;
	private static final int REBIRTH_TURN = 5;
	/**
	 * Constructor
	 * @param name name of the corpse
	 */
	public Corpse(String name) {
		super(name, DisplayChar.CORSPE.toChar());
	}

    /**
     * Inform a carried Item of the passage of time.
     * 
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Corpse.
     * @param actor The actor carrying this Corpse.
     */
	public void tick(Location currentLocation, Actor actor) {
		turns += 1;
		if (turns >= REBIRTH_TURN) {
			this.riseFromDeath(currentLocation, actor);
		}
	}
	
    /**
     * Inform an Item on the ground of the passage of time. 
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
	public void tick(Location currentLocation) {
		turns += 1;
		if (turns >= REBIRTH_TURN) {
			this.riseFromDeath(currentLocation);
		}
	}
	/**
	 * Transform the corpse into a zombie.
	 * @param currentLocation The location of the ground on which the corpse lies.
	 */
	private void riseFromDeath(Location currentLocation) {
		try {
			currentLocation.addActor(new Zombie(name));
			currentLocation.removeItem(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * Transform the corpse into a zombie.
	 * @param currentLocation The location of the actor carrying the Corpse
	 * @param actor The actor carrying the Corpse
	 */
	private void riseFromDeath(Location currentLocation, Actor actor) {
		actor.removeItemFromInventory(this);
		for (Exit exit : currentLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	destination.addActor(new Zombie(name));
            	actor.removeItemFromInventory(this);
            	break;
            }
        }
	}
}
