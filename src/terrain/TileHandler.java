package terrain;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import entities.Bush;
import entities.Entity;
import entities.Grass;
import entities.Player;
import entities.Tree;
import main.GameWindow;
import processing.core.PApplet;

public class TileHandler {

	public static final int GRID_SIZE = 10;

	private static ArrayList<Tile> tiles;

	private Tile playerTile;

	public TileHandler() {
		tiles = new ArrayList<Tile>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Tile t = new Tile(i, j, 30 * (int) (Math.random() * 4));
				tiles.add(t);
			}
		}
		playerTile = tiles.get(0);

	}

	public double getTerrainValue(double x, double y) { // FIXME add terrain
														// function
		return 10;
	}

	public double getTerrainHeight(double x, double y) { // TODO gets height for
															// entity rendering
		return 10;
	}

	public void updateEntityLocations() {
		for (int t = 0; t < tiles.size(); t++) {
			ArrayList<Entity> remove = new ArrayList<Entity>();
			remove = tiles.get(t).getMisplacedEntities(this);
			for (int i = 0; i < remove.size(); i++) {
				addEntity(remove.get(i));
			}
			tiles.get(t).clearEntities(remove);
		}
	}

	public void renderTerrain(Graphics g, double rotation, Player player) {
		double[][] heights = new double[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				heights[i][j] = this.getTerrainValue(i * GRID_SIZE - 5 * GRID_SIZE, j * GRID_SIZE - 5 * GRID_SIZE);
			}
		}
		
		
	}

	@SuppressWarnings("unchecked")
	public void renderTiles(Graphics g, double rotation, Player player) {
		Collections.sort(tiles);
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).draw(g, this, rotation, player,
					getTileHeight(absToBoard(player.getX()), absToBoard(player.getY())));
		}
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

	public void movePlayer(Player p) {

		if (!playerTile.inBorder(p.getX(), p.getY()) && getTile(p.getX(), p.getY()) != null) {
			playerTile.removePlayer(p);
			playerTile = getTile(p.getX(), p.getY());
			playerTile.addPlayer(p);
		}
	}

	public static double getPlayerHeight(Player p) {
		return getTileHeight(p.getBoardX(), p.getBoardY());
	}

	public static double getTileHeight(int x, int y) {
		for (Tile t : tiles) {
			if (t.getBoardX() == x && t.getBoardY() == y) {
				return t.getH();
			}
		}
		return 0;
	}

	/**
	 * Takes in absX and absY and returns the corresponding tile
	 * 
	 * @param x
	 *            x coordinate (abs)
	 * @param y
	 *            y coordinate (abs)
	 * @return Tile corresponding to the coordinates
	 */
	public Tile getTile(double x, double y) {
		for (Tile t : tiles) {
			if (t.getBoardX() == absToBoard(x) && t.getBoardY() == absToBoard(y)) {
				return t;
			}
		}
		return null;
	}

	public static int absToBoard(double a) {
		return (int) (a / Tile.TILE_SIZE);
	}

	public void addEntity(Entity e) {
		getTile(e.getAbsX(), e.getAbsY()).addEntity(e);
	}

}
