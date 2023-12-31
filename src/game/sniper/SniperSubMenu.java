package game.sniper;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SniperSubMenu extends Menu {
	public static final int REDIX = 10;

	/**
	 * Player is shown a list of zombies in his/her area of vision (half the map).
	 * Player can decide which zombie to snipe.
	 *
	 * @param zombies list of zombies in player's view
	 */
	private Actor showTargets(List<Actor> zombies) {
		ArrayList<Character> freeChars = new ArrayList<Character>();
		HashMap<Character, Actor> keyToActorMap = new HashMap<Character, Actor>();
		Display display = new Display();
		int counter = 0;

		for (char i = 'a'; i <= 'z'; i++)
			freeChars.add(i);

		for (Actor zombie : zombies) {
			char c = freeChars.get(counter);
			keyToActorMap.put(c, zombie);
			display.println(c + ":" + zombie.toString());
			counter++;
		}

		char key;
		do {
			key = display.readChar();
		} while (!keyToActorMap.containsKey(key));

		return keyToActorMap.get(key);
	}

	/**
	 * Player is shows options to choose what to do with the target. Player can
	 * fire, aim or retreat.
	 *
	 * @param actor   the Actor representing the player
	 * @param actions the Actions that the user can choose from
	 * @param display the I/O object that will display the map
	 */
	public Action showMenu(Actor actor, Actions actions, Display display) {
		display.println("Select an option");

		HashMap<Character, Action> keyToActionMap = new HashMap<Character, Action>();
		for (int i = 0; i < actions.size(); i++) {
			char c = Character.forDigit(i + 1, REDIX);
			if (actor.getZombieTarget() == null) {
				Actor target = showTargets(actions.get(i).getTargets());
				actor.setZombieTarget(target);
			}
			actions.get(i).setTarget(actor.getZombieTarget());
			keyToActionMap.put(c, actions.get(i));
			display.println(c + ": " + actions.get(i).menuDescription(actor));
		}

		char choice;
		do {
			choice = display.readChar();
		} while (!keyToActionMap.containsKey(choice));

		return keyToActionMap.get(choice);
	}
}
