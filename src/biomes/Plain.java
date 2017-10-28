package biomes;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import entities.Walker;
import main.SpriteSheetLoader;
import terrain.Tile;

public class Plain extends Biome {

	public Plain() {
		super();
	}

	public Color getSurfaceColor() {
		return new Color(133, 244, 133);
	}

	public Color getGroundColor() {
		return new Color(150, 135, 94);
	}
	
	public Image getSurfaceTexture() {
		return SpriteSheetLoader.getTexture(1, 5);
	}

	@Override
	protected ArrayList<Entity> getSpawnSet(Player p, Tile t) {
		if (t != null) {
			ArrayList<Entity> spawn = new ArrayList<Entity>();

			// spawn in any entity on tile

			// CHECK - can be out side of tile without causing problems.

			double x = Math.random() * Tile.TILE_SIZE + t.getAbsX();
			double y = Math.random() * Tile.TILE_SIZE + t.getAbsY();
			spawn.add(new Walker(x, y));

			return spawn;
		}
		return null;
	}

}
