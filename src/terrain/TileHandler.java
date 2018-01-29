package terrain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import biomes.Biome;
import biomes.Desert;
import biomes.Ocean;
import entities.Bush;
import entities.Entity;
import entities.EntityHandler;
import entities.Grass;
import entities.Player;
import entities.Tree;
import main.GameWindow;
import processing.core.PApplet;
import rendering.RenderQueue;

public class TileHandler {

	// will be referred to as action border
	// area where all tiles are loaded
	public static final int LOAD_SIZE = 2;// 2

	public static final int RENDER_DISTANCE = 1;

	private static ArrayList<Tile> tiles;

	public TileHandler(EntityHandler e) {
		tiles = new ArrayList<Tile>();
	}

	public void renderAll(RenderQueue renderQueue, Player p) {
		Tile playerTile = getPlayerTile(p);
		for (int i = 0; i < tiles.size(); i++) {
			Tile t = tiles.get(i);

			if (Math.abs(t.getBoardX() - playerTile.getBoardX()) <= RENDER_DISTANCE
					|| Math.abs(t.getBoardY() - playerTile.getBoardY()) <= RENDER_DISTANCE) {
				renderQueue.addRenderable(t);
			}
		}
	}

	public static double terrainHeight(int x, int y) {

		if (Biome.getBiome(x, y) instanceof Ocean) {
			return 0;
		} else {
			return 30;
		}
		// Random rand = new Random(GameWindow.SEED + 1398 * x + 1412 * y);
		// return 50 * rand.nextInt(3) + 10;
		// return 0;
	}

	public static double[] getAdjacentTileHeights(int x, int y) {
		double[] r = new double[4];
		for (int i = 0; i < tiles.size(); i++) {
			Tile t = tiles.get(i);
			if (t.getBoardX() == x && t.getBoardY() == y - 1) {
				r[0] = t.getH();
			}
			if (t.getBoardX() == x - 1 && t.getBoardY() == y) {
				r[1] = t.getH();
			}
			if (t.getBoardX() == x && t.getBoardY() == y + 1) {
				r[2] = t.getH();
			}
			if (t.getBoardX() == x + 1 && t.getBoardY() == y) {
				r[3] = t.getH();
			}
		}
		return r;
	}

	public static double getPlayerHeight(Player p) {
		return terrainHeight(p.getBoardX(), p.getBoardY());
	}

	/**
	 * Takes in x and y and returns the corresponding tile
	 * 
	 * @param x
	 *            x coordinate (abs)
	 * @param y
	 *            y coordinate (abs)
	 * @return Tile corresponding to the coordinates
	 */
	public static Tile getTile(int x, int y) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile t = tiles.get(i);
			if (t.getBoardX() == x && t.getBoardY() == y) {
				return t;
			}
		}
		return null;
	}

	public static Tile getPlayerTile(Player p) {
		return getTile(p.getBoardX(), p.getBoardY());
	}

	public static int absToBoard(double a) {
		int r = a < 0 ? (int) (a / Tile.TILE_SIZE - 1) : (int) (a / Tile.TILE_SIZE);
		return r;
	}

	public boolean missingTile(int x, int y) {
		for (int i = 0; i < tiles.size(); i++) {
			if (tiles.get(i).getBoardX() == x && tiles.get(i).getBoardY() == y) {
				return false;
			}
		}
		return true;
	}

	public void addTilefromSave(Tile t) {
		tiles.add(t);
	}

	// send all tiles to Save Handler to be properly saved to files
	public ArrayList<Tile> UnLoadTiles(Player player) {

		ArrayList<Tile> tilesToBeSaved = new ArrayList<Tile>();

		for (int i = 0; i < tiles.size(); i++) {
			if (Math.abs(tiles.get(i).getBoardX() - player.getBoardX()) > LOAD_SIZE
					|| Math.abs(tiles.get(i).getBoardY() - player.getBoardY()) > LOAD_SIZE) {
				tilesToBeSaved.add(tiles.remove(i));
			}
		}
		return tilesToBeSaved;
	}

	public static ArrayList<Entity> generateEntitiesForTile(Tile t) { // TODO
		Biome.getBiome(t.getBoardX(), t.getBoardY());
		return null;
	}

	public ArrayList<Tile> getAllTiles() {
		ArrayList<Tile> r = new ArrayList<Tile>();
		for (int i = 0; i < tiles.size(); i++) {
			r.add(tiles.get(i));
		}
		tiles.clear();
		return r;
	}

}
