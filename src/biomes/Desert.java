package biomes;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import entities.Rat;
import entities.Rock;
import entities.Walker;
import main.SpriteSheetLoader;
import terrain.Tile;

public class Desert extends Biome {

	public Desert() {
		super();
	}

	public Color getSurfaceColor() {
		return new Color(255, 206, 71);
	}

	public Color getGroundColor() {
		return new Color(150, 135, 94);
	}

	public Image getSurfaceTexture() {
		return SpriteSheetLoader.getTexture(1, 0);
	}

	@Override
	protected ArrayList<Entity> getSpawnSet(Player p, Tile t) {
		if (t != null) {
			ArrayList<Entity> spawn = new ArrayList<Entity>();

			// spawn in any entity on tile

			// CHECK - can be out side of tile without causing problems.

			double x = Math.random() * Tile.TILE_SIZE + t.getAbsX();
			double y = Math.random() * Tile.TILE_SIZE + t.getAbsY();

			spawn.add(new Rat(x, y));

			return spawn;
		}
		return null;
	}

	@Override
	public ArrayList<Entity> generateEntitiesForTile(Tile t) {
		ArrayList<Entity> r = new ArrayList<Entity>();

		for (int i = 0; i < 5; i++) {
			double x = Math.random() * (Tile.TILE_SIZE - 70) + t.getAbsX();
			double y = Math.random() * (Tile.TILE_SIZE - 70) + t.getAbsY();
			r.add(new Rock(x, y));
			if (Math.random() < .5)
				r.add(new Rock(x + Math.random() * 70, y + Math.random() * 70));
			if (Math.random() < .2)
				r.add(new Rock(x + Math.random() * 70, y + Math.random() * 70));
		}
		return r;
	}

}
