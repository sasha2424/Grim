package entities;

import processing.core.PApplet;
import terrain.Tile;

public abstract class StationaryEntity extends Entity {

	public StationaryEntity(double X, double Y, int x, int y) {
		super(X, Y, x, y);
	}

	public abstract void draw(PApplet p, Tile t, Player player, double rotation, double height);

}
