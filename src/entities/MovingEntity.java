package entities;

import java.awt.Graphics;

import main.GameWindow;
import processing.core.PApplet;
import terrain.Tile;

public abstract class MovingEntity extends Entity {

	public MovingEntity(double X, double Y, int x, int y) {
		super(X, Y, x, y);
		canMove = true;
	}

	public abstract void draw(GameWindow w, Graphics g, Tile t, Player player, double rotation, double height);

	protected void move() {
		absX += velX;
		absY += velY;
	}
}
