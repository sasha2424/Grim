package terrain;

import java.util.ArrayList;

import biomes.Biome;
import entities.Entity;
import entities.EntityHandler;
import entities.Player;

public class SpawnHandler {

	private int timer; // timer for when mobs are spawned
	private int timerLimitMax = 1000;
	private int timerLimit = (int) (Math.random() * timerLimitMax);

	public SpawnHandler() {

	}

	public void getSpawn(EntityHandler e, Player p) {
		timer++;
		if (timer > timerLimit) {
			timerLimit = (int) (Math.random() * timerLimitMax);

			// get spawn for biome at the player tile
			e.addEntities(Biome.getBiome(p.getBoardX(), p.getBoardY()).getSpawn(TileHandler.getPlayerTile(p)));
		}

	}

}
