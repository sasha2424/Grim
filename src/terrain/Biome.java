package terrain;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import entities.Entity;
import entities.Walker;

public abstract class Biome implements Serializable {

	private Color surface;
	private Color ground;
	
	private double radius; //size of biome
	private double x,y; //center

	private int tickCounter;

	public Biome() {
		surface = new Color(0, 0, 255);
		ground = new Color(0, 0, 255);
		tickCounter = 1000;
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

	public ArrayList<Entity> getSpawn(Tile t) {
		if (tickCounter > 0) {
			tickCounter--;
		} else {
			tickCounter = 10000;
			return this.getSpawnSet(t);
		}
		return null;
	}

	abstract ArrayList<Entity> getSpawnSet(Tile t);

	/*
	 * Holds biome color and other biome info for monster spawning and ect
	 * 
	 * 
	 * 
	 */

}
