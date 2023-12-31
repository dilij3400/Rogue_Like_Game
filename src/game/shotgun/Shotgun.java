package game.shotgun;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;
import game.DisplayChar;
import game.ReloadAction;

public class Shotgun extends WeaponItem {

    private static final int DAMAGE = 40;
    private int clipSize = 0;

    /**
     * Default constructor for shotgun
     */
    public Shotgun() {
        super("Shotgun", DisplayChar.SHOTGUN.toChar(), DAMAGE, "SHOT");
    }

    /**
     * Reduces the clip size by 1 every time the shotgun is fired
     */
    public void fire() {
        clipSize -= 1;
        clipSize = Math.max(clipSize, 0);
    }

    /**
     * Reloading adds ammo to the shotgun clip.
     * @param rounds number of ammo added
     */
    @Override
    public void reload(int rounds){
        clipSize += rounds;
    }
    
    public List<Action> getAllowableActions() {
    	if (clipSize == 0) {
    		return Arrays.asList(new ReloadAction(this, DisplayChar.SHOTGUNAMMO.toChar()));
    	}
		return Arrays.asList(new ShotgunShootingAction(this, new ShotgunSubMenu()));
    	
    }
}
