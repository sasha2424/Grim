package biomes;

import java.awt.Color;
import java.util.ArrayList;

import entities.Entity;
import entities.Walker;
import terrain.Tile;

public class Forrest extends Biome{
	
	public Forrest(){
		super();
	}
	
	public Color getSurfaceColor(){
		return new Color(0,100,16);
	}
	public Color getGroundColor(){
		return new Color(150,135,94);
	}
	
	
	@Override 
	protected ArrayList<Entity> getSpawnSet(Tile t){
		ArrayList<Entity> spawn = new ArrayList<Entity>();

		// spawn in any entity on tile

		// CHECK - can be out side of tile without causing problems.

		double x = Math.random() * Tile.TILE_SIZE + t.getAbsX();
		double y = Math.random() * Tile.TILE_SIZE + t.getAbsY();
		spawn.add(new Walker(x, y));

		return spawn;
	}

}
