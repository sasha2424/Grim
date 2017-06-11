package entities;

import java.awt.Graphics;

import processing.core.PApplet;
import terrain.Tile;

public abstract class StationaryEntity extends Entity {

	public StationaryEntity(double X, double Y, int x, int y) {
		super(X, Y, x, y);
	}

	public abstract void draw(Graphics g, Tile t, Player player, double rotation, double height);

}
