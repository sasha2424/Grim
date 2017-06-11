package terrain;

import java.util.ArrayList;
import java.util.Collections;

import entities.Player;
import entities.Tree;
import processing.core.PApplet;

public class TileHandler {

	private PApplet p;

	public static final int GRID_SIZE = 10;

	private static ArrayList<Tile> tiles;

	private Tile playerTile;

	public TileHandler(PApplet p) {
		this.p = p;
		tiles = new ArrayList<Tile>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Tile t = new Tile(i, j, 30 * (int) (Math.random() * 4));
				t.addEntity(new Tree(i * Tile.TILE_SIZE + Math.random() * Tile.TILE_SIZE,
						j * Tile.TILE_SIZE + Math.random() * Tile.TILE_SIZE, 0, 0));
				tiles.add(t);
			}
		}
		playerTile = tiles.get(0);
	}

	@SuppressWarnings("unchecked")
	public void renderTiles(double rotation, Player player) {
		Collections.sort(tiles);
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).draw(p, this, rotation, player,
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
}
