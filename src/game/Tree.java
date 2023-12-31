package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A tree that starts as a sapling and grows into a large tree.
 * 
 * @author ram
 *
 */
public class Tree extends Ground {
	private int age = 0;

	public Tree() {
		super(DisplayChar.TREE.toChar());
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = DisplayChar.YOUNGTREE.toChar();
		if (age == 20)
			displayChar = DisplayChar.GROWNTREE.toChar();
	}
}
