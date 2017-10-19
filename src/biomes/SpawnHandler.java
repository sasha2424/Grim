package biomes;

import entities.EntityHandler;
import entities.Player;
import terrain.TileHandler;

public class SpawnHandler {

	private int timer = 0; // timer for when mobs are spawned
	private int timerLimitMax = 1000;
	private int timerLimit = (int) (Math.random() * timerLimitMax);

	public SpawnHandler() {

	}

	public void spawnEntities(EntityHandler e, Player p) {
		timer++;
		if (timer > timerLimit) {
			timerLimit = (int) (Math.random() * timerLimitMax);
			timer = 0;
			
			// get spawn for biome at the player tile
			e.addEntities(Biome.getBiome(p.getBoardX(), p.getBoardY()).getSpawnSet(p,TileHandler.getPlayerTile(p)));
		}

	}

}
