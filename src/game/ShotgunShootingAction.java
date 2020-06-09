package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Special Action for shooting zombies with a shotgun.
 */
public class ShotgunShootingAction extends Action {
    /**
     * weapon      : shotgun
     * location    : location of actor.
     * PROBABILITY : 75% chance of shotgun dealing damage.
     * menu        : Shotgun sub menu for actor to choose the direction to fire.
     */
    private Location location;
    private Shotgun weapon;
    private static final double PROBABILITY = 0.75;
    private ShotgunSubMenu menu = new ShotgunSubMenu();

    /**
     * Default constructor for ShotgunShootingAction class
     * @param location location of the actor (player)
     * @param weapon weapon used
     */
    public ShotgunShootingAction(Location location, Weapon weapon) {
        this.location = location;
        this.weapon = (Shotgun) weapon;
    }

    /**
     * Based on the directional input of the user, appropriate calculations are done to find the x and y coordinates
     * of the attack area of the shotgun. Then appropriate attack method for that direction is implemented to
     * hurt the actors in the area with a 75% chance of the attack being successful. If the actors is not conscious
     * the actor is removed from the map using killTarget() method. For every shot fired, player loses 1 ammo
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int direction = menu.showMenu();
        int x = location.x();
        int y = location.y();

        // every time player fires, clip size reduces by 1
        if (weapon.getClipSize() == 0){
            return "No ammo!";
        }
        weapon.fire();

        // For NORTH direction
        if (direction == 1){
            x -= 4;
            int[] xRange = new int[7];
            int[] yRange = new int[3];

            // calculating x range
            for (int i = 0; i < xRange.length; i++){
                x += 1;
                if (x >= 0 && x < 80) {
                    xRange[i] = x;
                }
            }

            // calculating y range
            for (int j = 0; j < yRange.length; j++){
                y -= 1;
                if (y >= 0 && y < 25) {
                    yRange[j] = y;
                }
            }

            ArrayList<Actor> zombies = fireXYDirection(xRange, yRange, map); // actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for" + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
        }

        // For SOUTH direction
        if (direction == 5){
            x -= 4;
            int[] xRange = new int[7];
            int[] yRange = new int[3];

            // Calculating x range
            for (int i = 0; i < xRange.length; i++){
                x += 1;
                if (x >= 0 && x < 80) {
                    xRange[i] = x;
                }
            }

            // Calculating y range
            for (int i = 0; i < yRange.length; i++){
                y += 1;
                if (y >= 0 && y < 25) {
                    yRange[i] = y;
                }
            }

            fireXYDirection(xRange, yRange, map);

            ArrayList<Actor> zombies = fireXYDirection(xRange, yRange, map); // Actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for" + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
        }

        // For EAST direction
        if (direction == 3){
            int[] xRange = new int[3];
            int[] yRange = new int[7];
            y -= 4;

            // Calculating x range
            for (int i = 0; i < xRange.length; i++){
                x += 1;
                if (x >= 0 && x < 80) {
                    xRange[i] = x;
                }
            }

            // Calculating y range
            for (int i = 0; i < yRange.length; i++){
                y += 1;
                if (y >= 0 && y < 25) {
                    yRange[i] = y;
                }
            }

            fireXYDirection(xRange, yRange, map);

            ArrayList<Actor> zombies = fireXYDirection(xRange, yRange, map); // Actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for" + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
        }

        // For WEST direction
        if (direction == 7){
            int[] xRange = new int[3];
            int[] yRange = new int[7];
            y -= 4;

            // Calculating x range
            for (int i = 0; i < xRange.length; i++){
                x -= 1;
                if (x >= 0 && x < 80) {
                    xRange[i] = x;
                }
            }

            // Calculating y range
            for (int i = 0; i < yRange.length; i++){
                y += 1;
                if (y >= 0 && y < 25) {
                    yRange[i] = y;
                }
            }

            fireXYDirection(xRange, yRange, map);

            ArrayList<Actor> zombies = fireXYDirection(xRange, yRange, map); // Actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for" + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
        }
        return "Player missed";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Fire Shotgun";
    }

    /**
     * Used for north and south direction. Only 3 y values to iterate through.
     * For a 75% chance of success, if there is a actor in the area of damage, damage is dealt.
     * @param x x coordinate
     * @param y y coordinate
     * @param map map where the actor
     */
    public ArrayList<Actor> fireXYDirection(int[] x, int[] y, GameMap map){

        int start = 3;
        int end = 3;
        int raise = 1;
        int pointer;
        int range;
        ArrayList<Actor> hurtActors = new ArrayList<>();

        // checks if area of effect is in X direction or Y direction
        if (x.length == 3){
            range = x.length;
        }
        else {
            range = y.length;
        }

        for (int i = 0; i < range; i++){
            start -= raise;
            end += raise;

            while (start <= end){
                if (x.length == 3){
                    pointer = y[start];
//                    map.at(x[i], pointer).setGround(new Crop()); // Testing
                    if (map.at(x[i],pointer).containsAnActor()){
                        Actor target = map.at(x[i],pointer).getActor();
                        if (Math.random() <= PROBABILITY){
                            target.hurt(weapon.damage());
                            hurtActors.add(map.at(x[i],pointer).getActor());
                        }
                    }
                }
                else {
                    pointer = x[start];
//                    map.at(pointer, y[i]).setGround(new Crop()); // Testing
                    if (map.at(pointer,y[i]).containsAnActor()){
                        Actor target = map.at(pointer,y[i]).getActor();
                        if (Math.random() <= PROBABILITY){
                            target.hurt(weapon.damage());
                            hurtActors.add(map.at(pointer,y[i]).getActor());
                        }
                    }
                }
                start += 1;
            }
            raise += 1;
            start = 3;
            end = 3;
        }

        return hurtActors;
    }

    /**
     * Method to kill a zombie, remove it from its location and drop an items in its inventory.
     * @param target zombie to be killed
     * @param map map the actor is on
     * @return a string output
     */
    public String killTarget(Actor target, GameMap map){

        // Drops inventory items
        Actions dropActions = new Actions();
        for (Item item : target.getInventory())
            dropActions.add(item.getDropAction());
        for (Action drop : dropActions)
            drop.execute(target, map);

        map.removeActor(target);
        return System.lineSeparator() + target + " is killed.";
    }
}
