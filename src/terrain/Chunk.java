package terrain;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Chunk {
	private ArrayList<Tile> tiles;

	public static int SIZE = 10;

	private int x, y; // chunk corner in board coordinates

	public Chunk(int x, int y) {
		tiles = new ArrayList<Tile>();
		this.x = x;
		this.y = y;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				tiles.add(new Tile(x + i, y + j, 50 + 50 * (int) (Math.random() * 3)));
			}
		}

	}

	public void draw(PApplet p, double rotation) {
		Collections.sort(tiles);
		for (int i = 0; i < tiles.size(); i++) {
			//tiles.get(i).draw(p, rotation);
		}
	}

}
