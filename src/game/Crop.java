package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A Crop that starts riping and grows into a ripe crop to be harvested.
 *
 *
 */

public class Crop extends Ground {

    private int ripe = 0;
    private static final int MAXRIPE = 20;

    /**
     * Default constructor for Crop
     */
    public Crop() {
        super('!'); // This displayChar indicates unripe Crop
    }

    /**
     * Sets a new value to ripe attribute
     *
     * @param ripe int ripe
     */
    public void setRipe(int ripe) {
        this.ripe = ripe;
    }

    /**
     * Returns the ripe value of the Crop
     *
     * @return ripe attribute
     */
    public int getRipe() {
        return ripe;
    }

    /**
     * Crop experiences passage of time. During this passage, Crop ripes by 1 per turn.
     * If there is a Farmer at the Crop's location, Farmer fertilizes it by adding 10 turns to ripe.
     *
     * @param location The location of the Crop
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        ripe++;
        ripe = Math.min(ripe,MAXRIPE);


        if (ripe == 20){
            displayChar = '$'; // new displayChar indicates ripe Crop
        }

    }
}
