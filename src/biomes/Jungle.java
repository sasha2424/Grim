package biomes;

import java.awt.Color;
import java.util.ArrayList;

import entities.Entity;
import entities.Walker;
import terrain.Tile;

public class Jungle extends Biome{
	
	public Jungle(){
		super();
	}
	
	public Color getSurfaceColor(){
		return new Color(30,200,30);
	}
	public Color getGroundColor(){
		return new Color(150,135,94);
	}
	
	
	@Override 
	protected ArrayList<Entity> getSpawnSet(Tile t){
		ArrayList<Entity> spawn = new ArrayList<Entity>();

		// spawn in any entity on tile

		// CHECK - can be out side of tile without causing problems.

		double x = Math.random() * t.getH() + t.getAbsX();
		double y = Math.random() * t.getH() + t.getAbsY();
		spawn.add(new Walker(x, y));

		return spawn;
	}

}
