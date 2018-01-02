package biomes;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import entities.Crocodile;
import entities.Entity;
import entities.Player;
import entities.Tree;
import entities.Walker;
import main.SpriteSheetLoader;
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
	
	public Image getSurfaceTexture() {
		return SpriteSheetLoader.getTexture(1, 2);
	}
	
	
	@Override 
	protected ArrayList<Entity> getSpawnSet(Player p,Tile t){
		ArrayList<Entity> spawn = new ArrayList<Entity>();

		// spawn in any entity on tile

		// CHECK - can be out side of tile without causing problems.

		double x = Math.random() * Tile.TILE_SIZE + t.getAbsX();
		double y = Math.random() * Tile.TILE_SIZE + t.getAbsY();
		spawn.add(new Crocodile(x, y));

		return spawn;
	}

	@Override
	public ArrayList<Entity> generateEntitiesForTile(Tile t) {
		ArrayList<Entity> r = new ArrayList<Entity>();

		for (int i = 0; i < 15; i++) {
			double x = Math.random() * (Tile.TILE_SIZE - 70) + t.getAbsX();
			double y = Math.random() * (Tile.TILE_SIZE - 70) + t.getAbsY();
			r.add(new Tree(x, y));
			if (Math.random() < .5)
				r.add(new Tree(x + Math.random() * 70, y + Math.random() * 70));
			if (Math.random() < .2)
				r.add(new Tree(x + Math.random() * 70, y + Math.random() * 70));
		}
		return r;
	}

}
