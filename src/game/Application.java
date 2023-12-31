package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.shotgun.ShotgunAmmo;
import game.shotgun.Shotgun;
import game.sniper.SniperRifleAmmo;
import game.sniper.SniperRifle;


/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		
		map = Arrays.asList(
		"####################################################################################",
		"#..................................................................................#",
		"#..................................................................................#",
		"#................................................##########################........#",
		"#................................................#........................#........#",
		"#........##########..............................#........................#........#",
		"#........##########..............................#........................#........#",
		"#........##########..............................#.................########........#",
		"#........##########..............................#.................#...............#",
		"#................................................#.................#...............#",
		"#................................................#.................#...............#",
		"#................................................#.................#...............#",
		"#................................................#.................#...............#",
		"#................................................#####.........#####...............#",
		"#..................................................................................#",
		"#..................................................................................#",
		"#.......#############..............................................................#",
		"#.......#...........#..............................................................#",
		"#.......#..........................................................................#",
		"#.......#..........................................................................#",
		"#.......#...........#..............................................................#",
		"#.......#############..............................................................#",
		"#..................................................................................#",
		"####################################################################################");
		GameMap townMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);
		world.addGameMap(townMap);
		
		Actor player = new Player("Player", game.DisplayChar.PLAYER.toChar(), 100);
		world.addPlayer(player, gameMap.at(42, 15));
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));
		}

		// Add a few Farmers
        gameMap.at(39,10).addActor(new Farmer("George"));
        gameMap.at(47,12).addActor(new Farmer("July"));

		// Place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());

		// FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));

		// Place some random humans to town map
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 45.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (!townMap.at(x, y).canActorEnter(player));
			townMap.at(x,  y).addActor(new Human(name));
		}

		// Add Zombies to town map
		townMap.at(30, 10).addActor(new Zombie("Groan"));
		townMap.at(30,  9).addActor(new Zombie("Boo"));
		townMap.at(10,  2).addActor(new Zombie("Uuuurgh"));
		townMap.at(34, 9).addActor(new Zombie("Mortalis"));
		townMap.at(1, 5).addActor(new Zombie("Gaaaah"));
		townMap.at(12, 10).addActor(new Zombie("Aaargh"));

		// Add shotgun and sniper ammo to compound and town maps.
		gameMap.at(37, 3).addItem(new ShotgunAmmo());
		gameMap.at(3, 3).addItem(new ShotgunAmmo());
		gameMap.at(67, 24).addItem(new ShotgunAmmo());
		gameMap.at(3, 20).addItem(new ShotgunAmmo());
		gameMap.at(42, 24).addItem(new SniperRifleAmmo());
		gameMap.at(14, 16).addItem(new SniperRifleAmmo());
		townMap.at(37, 3).addItem(new ShotgunAmmo());
		townMap.at(70, 4).addItem(new ShotgunAmmo());
		townMap.at(67, 20).addItem(new ShotgunAmmo());
		townMap.at(13, 20).addItem(new SniperRifleAmmo());
		townMap.at(52, 5).addItem(new SniperRifleAmmo());


		// TESTING

		// ASSIGNMENT 2
//		//Zombie picking up Weapon Testing
//		for (x = 29; x <= 31; x++){
//			for (y = 19; y <= 21; y++){
//				if (!(x == 30 && y == 20))
//					gameMap.at(x,y).addItem(new Plank());
//			}
//		}
		
//		//Rise from the death testing
//		gameMap.at(38, 20).addItem(new Corpse("Coorrpse"));
		
//		// Fertilise and Harvest Testing
//		gameMap.at(15,22).addActor(new Farmer("George"));
//
//		// Create crops
//		for (x = 12; x <= 18; x++){
//			for (y = 20; y <= 24; y++){
//				gameMap.at(x,y).setGround(new Crop());
//			}
//		}


		// ASSIGNMENT 3
//		// Town level Testing
		gameMap.at(42, 14).addItem(new Vehicle(gameMap, townMap));
		// MamboMarie Testing
//		Mambo mambo = new Mambo(gameMap);

		// Weapons testing
//		player.addItemToInventory(new Shotgun());
//		player.addItemToInventory(new ShotgunAmmo());
		player.addItemToInventory(new SniperRifle());
		player.addItemToInventory(new SniperRifleAmmo());
		world.run();
	}
}
