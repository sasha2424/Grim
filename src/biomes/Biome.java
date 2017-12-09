package biomes;

import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import entities.Entity;
import entities.Player;
import entities.Walker;
import main.GameWindow;
import main.SpriteSheetLoader;
import terrain.Tile;

public abstract class Biome implements Serializable {

	public static final int TOTAL_BIOMES = 5;

	private Color surface;
	protected Image texture;
	private Color ground;

	private int tickCounter;
	protected int tickCounterMax = 100;

	public Biome() {
		surface = new Color(0, 0, 255);
		ground = new Color(0, 0, 255);
		tickCounter = tickCounterMax;
	}

	public static Biome getBiome(int i) {
		return new Desert();

	}

	public Color getSurfaceColor() {
		return surface;
	}

	public Color getGroundColor() {
		return ground;
	}
	
	public abstract Image getSurfaceTexture();

	abstract ArrayList<Entity> getSpawnSet(Player p,Tile t);

	public static Biome getBiome(int x, int y) {
		int i = (int) (x / .834);
		int j = (int) (y / .981);

		Random rand = new Random(0);

		
		int rangeout = 4;
		int rangein = 2;
		

		// blur of size 4
		int[] count = new int[Biome.TOTAL_BIOMES];
		for (int a = i - rangeout; a < i + rangeout; a++) {
			for (int b = j - rangeout; b < j + rangeout; b++) {

				// blur of size 2
				int[] countin = new int[Biome.TOTAL_BIOMES];
				for (int q = a - rangein; q < a + rangein; q++) {
					for (int w = b - rangein; w < b + rangein; w++) {
						rand.setSeed(GameWindow.SEED + 4713 * q + 7608 * w);
						countin[rand.nextInt(Biome.TOTAL_BIOMES)]++;
						// base random function
					}
				}
				int k = 0;
				for (int q = 0; q < Biome.TOTAL_BIOMES; q++) {
					if (countin[q] > countin[k]) {
						k = q;
					}
				}
				count[k]++;
			}
		}
		int k = 0;
		for (int a = 0; a < Biome.TOTAL_BIOMES; a++) {
			if (count[a] > count[k]) {
				k = a;
			}
		}

		if (k == 0) {
			return new Desert();
		}
		if (k == 1) {
			return new Plain();
		}
		if (k == 2) {
			return new Forrest();
		}
		if (k == 3) {
			return new Jungle();
		}
		if (k == 4) {
			return new Ocean();
		}
		return new Desert();
	}

}
