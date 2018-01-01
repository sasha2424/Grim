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
import entities.Bush;
import entities.Entity;
import entities.EntityHandler;
import entities.Grass;
import entities.Player;
import entities.Tree;
import main.GameWindow;
import processing.core.PApplet;

public class TileHandler {

	// will be referred to as action border
	// area where all tiles are loaded
	public static final int LOAD_SIZE = 2;// 2

	private static ArrayList<Tile> tiles;

	public TileHandler(EntityHandler e) {
		tiles = new ArrayList<Tile>();
	}

	@SuppressWarnings("unchecked")
	public void renderAll(GameWindow w, Graphics2D g, EntityHandler e, double rotation, Player player) {
		Collections.sort(tiles);
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).draw(w, g, rotation, player);
			e.renderEntitiesAt(w, g, tiles.get(i), getPlayerHeight(player), player, rotation);
		}
	}

	// public void updateTiles(EntityHandler e, Player player) {
	// // TODO make more effective
	// tiles.clear();
	//
	// int x = player.getBoardX();
	// int y = player.getBoardY();
	//
	// // create new tiles
	// for (int i = -LOAD_SIZE + x; i < LOAD_SIZE + x; i++) {
	// for (int j = -LOAD_SIZE + y; j < LOAD_SIZE + y; j++) {
	// if (getTile(i, j) == null) {
	// Tile t = new Tile(i, j, terrainHeight(i, j), Biome.getBiome(i, j));
	// tiles.add(t);
	// }
	// }
	// }
	//
	// // TODO update spawn in the players tile + small range
	//
	// }

	public static double terrainHeight(int x, int y) {

		Random rand = new Random(GameWindow.SEED + 1398 * x + 1412 * y);
		return 50 * rand.nextInt(3) + 10;
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

	public static ArrayList<Entity> generateEntitiesForTile(Tile t) {
		Biome.getBiome(t.getBoardX(), t.getBoardY());
		return null;
	}

}
