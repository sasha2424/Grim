package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;

import main.GameWindow;
import processing.core.PApplet;
import terrain.Tile;

public abstract class MovingEntity extends Entity {

	public MovingEntity(double X, double Y, int[] x, int[] y) {
		super(X, Y, x, y);
		canMove = true;
		
	}

	public abstract void draw(GameWindow w, Graphics2D g, Tile t, Player player, double rotation, double height);

	protected void move() {
		absX += velX;
		absY += velY;
	}

	public void interactPlayer(Player player) {
		this.HP.increment(-1 * player.A.getVal());
	}
}
