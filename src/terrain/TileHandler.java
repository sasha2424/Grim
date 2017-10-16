package terrain;

import java.awt.Color;
import java.awt.Graphics;
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

import entities.Bush;
import entities.Entity;
import entities.EntityHandler;
import entities.Grass;
import entities.Player;
import entities.Tree;
import main.GameWindow;
import processing.core.PApplet;

public class TileHandler {

	public static final int GRID_SIZE = 50;

	// will be referred to as action border
	// area where all tiles are loaded
	public static final int LOAD_SIZE = 2;

	private static long seed = 0;

	private static ArrayList<Tile> tiles;

	public TileHandler(double seed, EntityHandler e) {
		tiles = new ArrayList<Tile>();

		seed = 100;
	}

	@SuppressWarnings("unchecked")
	public void renderAll(Graphics g, EntityHandler e, double rotation, Player player) {
		Collections.sort(tiles);
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).draw(g, this, rotation, player, getPlayerHeight(player));
			e.renderEntitiesAt(g, tiles.get(i), getPlayerHeight(player), player, rotation);
		}
	}

	public void updateTiles(EntityHandler e, Player player) {

		tiles.clear();

		int x = player.getBoardX();
		int y = player.getBoardY();

		// create new tiles
		for (int i = -LOAD_SIZE + x; i < LOAD_SIZE + x; i++) {
			for (int j = -LOAD_SIZE + y; j < LOAD_SIZE + y; j++) {
				if (getTile(i, j) == null) {
					Tile t = new Tile(i, j, terrainHeight(i, j), getBiome(i, j));
					tiles.add(t);
				}
			}
		}

		// TODO update spawn in the players tile + small range

	}

	public static double terrainHeight(int x, int y) {
		Random rand = new Random(seed);
		int k = 100;// max tile height

		return rand.nextInt(k + 1) * Math.cos(x * 10) + rand.nextInt(k + 1) * Math.sin(y * 10) + rand.nextInt(k + 1);
	}

	public static Biome getBiome(int x, int y) {
		return new Desert();
	}

	public double[] getAdjacentTileHeights(int x, int y) {
		double[] r = new double[4];
		for (Tile t : tiles) {
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

}
